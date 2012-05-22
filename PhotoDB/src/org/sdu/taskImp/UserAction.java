package org.sdu.taskImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.sdu.db.dao.DynamicDao;
import org.sdu.db.dao.FriendDao;
import org.sdu.db.dao.PhotoDao;
import org.sdu.db.dao.UserDao;
import org.sdu.db.pojo.Dynamic;
import org.sdu.db.pojo.Friend;
import org.sdu.db.pojo.User;
import org.sdu.task.UserActionInterface;

import android.content.Context;

public class UserAction implements UserActionInterface {
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
			if (friend.contains(curr.getUserId())) {
				res.add(curr.getId());
			}
		}
		return res;
	}

	public List<Integer> getHotPhotoIdList(int num) {
		PhotoDao pdao = new PhotoDao(context);
		List<Map<String, String>> data = pdao.query2MapList(
				"select id from Photo order by viewNum desc limit ?",
				new String[] { "" + num });
		List<Integer> res = new ArrayList<Integer>();
		for (Map<String, String> m : data) {
			res.add(Integer.parseInt(m.get("id")));
		}
		return res;
	}

}
