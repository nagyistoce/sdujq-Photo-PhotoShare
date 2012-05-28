package org.sdu.taskImp;

import java.util.List;

import org.sdu.db.dao.UserDao;
import org.sdu.db.pojo.User;
import org.sdu.task.IReg;

import android.content.Context;

public class RegAction implements IReg{
	private String info="错误";
	private Context context;
	public RegAction(Context context){
		this.context=context;
	}
	public boolean reg(String name, String email, String pwd) {
		UserDao uDao=new UserDao(context);
		List<User> u=uDao.find(new String[]{"id","pwd","name"},"name=?",new String[]{name},null,null,null,null);
		if(u!=null&&u.size()!=0){
			info="用户名已经存在";
			return false;
		}
		u=uDao.find(new String[]{"id","pwd","name"},"email=?",new String[]{email},null,null,null,null);
		if(u!=null&&u.size()!=0){
			info="用户名邮箱已存在";
			return false;
		}else if(name.equals("")||pwd.equals("")||email.equals("")){
			info="请填满用户信息";
			return false;
		}
		User user=new User();
		user.setEmail(email);
		user.setName(name);
		user.setPwd(pwd);
		uDao.insert(user);
		info="注册成功";
		return true;
	}

	public String getRegInfo() {
		return info;
	}

}
