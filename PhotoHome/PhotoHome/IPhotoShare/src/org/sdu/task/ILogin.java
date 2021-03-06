package org.sdu.task;
/**
 * 登陆
 * @author shadow
 *
 */
public interface ILogin {

	/**
	 * 登陆
	 * @param id 账号
	 * @param pwd 密码
	 * @return 是否登陆成功
	 */
	boolean login(int id,String pwd);
	/**
	 * 登陆
	 * @param name 用户名
	 * @param pwd 密码
	 * @return 是否登陆成功
	 */
	boolean login(String name,String pwd);
	/**
	 * 得到此次登陆的提示信息
	 * @return 登陆提示
	 */
	String getLogInfo();
}
