package org.sdu.task;

import java.util.List;

import org.sdu.db.pojo.Argument;
import org.sdu.db.pojo.Collection;
import org.sdu.db.pojo.Dynamic;
import org.sdu.db.pojo.Friend;
import org.sdu.db.pojo.Photo;
import org.sdu.db.pojo.User;

import android.graphics.Bitmap;

/**
 * 用户信息先关操作
 * @author shadow
 *
 */
public interface IUserAction {
	/**
	 * 得到当前登陆用户
	 * @return 当前登陆User对象
	 */
	User getCurrentUser();
	/**
	 * 得到用户u的好友关系列表
	 * @param u 至少包含id 
	 * @return 好友关系列表 Friend.user_2为好友id
	 */
	List<Friend> getFriendShipIdList(User u);
	/**
	 * 得到用户u的好友列表
	 * @param u 至少包含id
	 * @return list<好友对象>
	 */
	List<User> getFriendList(User u);
	/**
	 * 用u对象为模板更新用户信息，用于更改密码、头像等。
	 * @param u 注意：1、id无法更改此处自动忽略id。2、值为null的属性此处自动忽略
	 * @return 是否更新成功
	 */
	boolean changeCurrentUserInfo(User u);
	/**
	 * 得到某用户的新鲜事列表
	 * @param u 用户
	 * @return 最近100条新鲜事的id
	 */
	List<Integer> getDynamicIdList(User u);
	/**
	 * 得到top num的最热图片的列表
	 * @param num 数量
	 * @return 图片id列表
	 */
	List<Integer> getHotPhotoIdList(int num);
	/**
	 * 增加动态
	 * @param dynamic 动态对象
	 */
	void addDynamic(Dynamic dynamic);
	/**
	 * 保存图片
	 * @param bmp 要保存的Bitmap对象
	 */
	void savePhoto(Bitmap bmp,String title,String detail);
	/**
	 * 加关注
	 * @param uid 目标关注人
	 */
	void makeFriendWith(int uid);
	/**
	 * 分享某张图片
	 * @param photoId 图片id
	 */
	void collect(int photoId);
	/**
	 * 对某图片发表评论
	 * @param pid 图片id
	 * @param words 评论内容
	 */
	void makeArgument(int pid,String words);
	
	Photo getPhotoById(int id);
	
	Bitmap getBitmap(Photo p);
	
	List<Argument> getArgumentList(int id);
	
	List<Collection> getCollectionList();
}
