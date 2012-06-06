package org.sdu.taskImp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.sdu.bmputil.BitmapTool;
import org.sdu.db.dao.ArgumentDao;
import org.sdu.db.dao.CollectionDao;
import org.sdu.db.dao.DynamicDao;
import org.sdu.db.dao.FriendDao;
import org.sdu.db.dao.PhotoDao;
import org.sdu.db.dao.UserDao;
import org.sdu.db.pojo.Argument;
import org.sdu.db.pojo.Collection;
import org.sdu.db.pojo.Dynamic;
import org.sdu.db.pojo.Friend;
import org.sdu.db.pojo.Photo;
import org.sdu.db.pojo.User;
import org.sdu.task.IUserAction;

import android.content.Context;
import android.graphics.Bitmap;

public class UserAction implements IUserAction {
	public static final int NEWPHOTO = 0;
	public static final int SHARE = 1;
	public static final int ARGUMENT = 2;

	// public static final int MAKEFRIEND=3;
	private Context context;

	public UserAction(Context context) {
		this.context = context;
	}

	public User getCurrentUser() {
		return LoginAction.currentUser;
	}

	public boolean changeCurrentUserInfo(User u) {
		User curr = getCurrentUser();
		if (u.getEmail() != null) {
			curr.setEmail(u.getEmail());
		}
		if (u.getHead() != null) {
			curr.setHead(u.getHead());
		}
		if (u.getPwd() != null) {
			curr.setPwd(u.getPwd());
		}
		UserDao uDao = new UserDao(context);
		uDao.update(curr);
		return false;
	}

	public List<Friend> getFriendShipIdList(User u) {
		FriendDao fdao = new FriendDao(context);
		List<Friend> flst = fdao.find(new String[] { "user_1", "user_2" },
				"user_1=?", new String[] { u.getId() + "" }, null, null, null,
				null);
		return flst;
	}

	public List<Integer> getFriendIdList(User u) {
		List<Friend> flst = getFriendShipIdList(u);
		List<Integer> ulst = new ArrayList<Integer>();
		for (Friend f : flst) {
			ulst.add(f.getUser_2());
		}
		return ulst;
	}

	public List<User> getFriendList(User u) {
		List<Friend> flst = getFriendShipIdList(u);
		List<User> ulst = new ArrayList<User>();
		UserDao udao = new UserDao(context);
		for (Friend f : flst) {
			ulst.add(udao.get(f.getUser_2()));
		}
		return ulst;
	}

	public List<Integer> getDynamicIdList(User u) {
		DynamicDao ddao = new DynamicDao(context);
		List<Dynamic> data = ddao.find(new String[] { "id", "userId" }, null,
				null, null, null, "time", null);
		List<Integer> friend = getFriendIdList(u);
		List<Integer> res = new ArrayList<Integer>();
		for (int i = data.size() - 1; i > -1; i--) {
			Dynamic curr = data.get(i);
			if (friend.contains(curr.getUserId())||u.getId()==curr.getUserId()) {
				res.add(curr.getId());
			}
		}
		return res;
	}

	public List<Integer> getHotPhotoIdList(int num) {
		PhotoDao pdao = new PhotoDao(context);
		List<Map<String, String>> data = pdao.query2MapList(
				"select id from t_photo order by viewNum desc limit ?",
				new String[] { "" + num });
		List<Integer> res = new ArrayList<Integer>();
		for (Map<String, String> m : data) {
			res.add(Integer.parseInt(m.get("id")));
		}
		return res;
	}

	public void addDynamic(Dynamic dynamic) {
		new DynamicDao(context).insert(dynamic);
	}

	public void savePhoto(Bitmap bmp, String title,String detail) {
		Photo p = new Photo();
		p.setData(BitmapTool.Bitmap2Bytes(bmp));
		// TODO loaction
		long time=new Date().getTime();
		p.setTime(time);
		p.setTitle(title);
		p.setUserId(getCurrentUser().getId());
		p.setViewNum(0);
		p.setTag(detail);
		Long pid = new PhotoDao(context).insert(p);
		Dynamic dynamic = new Dynamic();
		dynamic.setPhotoId(pid.intValue());
		dynamic.setTime(time);
		dynamic.setTitle(title);
		dynamic.setTypeId(NEWPHOTO);
		dynamic.setUserId(getCurrentUser().getId());
		addDynamic(dynamic);
	}

	public void makeFriendWith(int uid) {
		Friend f = new Friend();
		f.setUser_1(getCurrentUser().getId());
		f.setUser_2(uid);
		new FriendDao(context).insert(f);
	}

	public void collect(int photoId) {
		Collection c = new Collection();
		c.setPhotoId(photoId);
		c.setUserId(getCurrentUser().getId());
		CollectionDao cdao = new CollectionDao(context);
		cdao.insert(c);
		PhotoDao pdao=new PhotoDao(context);
		Photo p=pdao.get(photoId);
		int num=(p.getViewNum()==null?0:p.getViewNum())+1;
		p.setViewNum(num);
		pdao.update(p);
		long time = new Date().getTime();

		Dynamic dynamic = new Dynamic();
		dynamic.setPhotoId(photoId);
		dynamic.setTime(time);
		dynamic.setTypeId(SHARE);
		dynamic.setUserId(getCurrentUser().getId());
		addDynamic(dynamic);

	}

	public void makeArgument(int pid, String words) {
		Argument arg = new Argument();
		arg.setInfo(words);
		arg.setPhotoId(pid);
		arg.setTime(new Date().getTime());
		arg.setUserId(getCurrentUser().getId());
		new ArgumentDao(context).insert(arg);
	}

	public Photo getPhotoById(int id) {
		return new PhotoDao(context).get(id);
	}

	public Bitmap getBitmap(Photo p) {
		if (p.getData() != null) {
			return BitmapTool.Bytes2Bimap(p.getData());
		}
		p = new PhotoDao(context).get(p.getId());
		return BitmapTool.Bytes2Bimap(p.getData());
	}

	public List<Argument> getArgumentList(int photoid) {
		ArgumentDao adao = new ArgumentDao(context);
		return adao.find(new String[] { "id", "userId", "time", "photoId",
				"info" }, "photoId=?", new String[] { photoid + "" }, null, null, null,
				null);
	}

	public List<Collection> getCollectionList() {
		CollectionDao cdao = new CollectionDao(context);
		return cdao.rawQuery("select * from t_collection where userId=?",
				new String[] { getCurrentUser().getId() + "" });
	}
	
	public List<Photo> getAllPhoto(User u){
		PhotoDao pdao=new PhotoDao(context);
		return pdao.rawQuery("select * from t_photo where userId=?", new String[] { u.getId() + "" });
	}

}
