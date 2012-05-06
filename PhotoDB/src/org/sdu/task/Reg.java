package org.sdu.task;
/**
 * 注册相关
 * @author shadow
 *
 */
public interface Reg {
	/**
	 * 注册新用户
	 * @param name 用户名
	 * @param email 邮箱
	 * @param pwd 密码
	 * @return 是否登陆成功
	 */
	boolean reg(String name,String email,String pwd);
	/**
	 * 得到注册提示信息
	 * @return 注册提示信息
	 */
	String getRegInfo();
}
