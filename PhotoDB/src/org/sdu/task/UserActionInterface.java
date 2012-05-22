package org.sdu.task;

import java.util.List;

import org.sdu.db.pojo.Friend;
import org.sdu.db.pojo.User;

/**
 * 用户信息先关操作
 * @author shadow
 *
 */
public interface UserActionInterface {
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
}
