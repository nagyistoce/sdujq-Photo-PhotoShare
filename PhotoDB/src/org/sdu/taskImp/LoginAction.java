package org.sdu.taskImp;

import java.util.List;

import org.sdu.db.dao.UserDao;
import org.sdu.db.pojo.User;
import org.sdu.task.Logininterface;

import android.content.Context;

public class LoginAction implements Logininterface{
	static User currentUser;
	private String info="����";
	private Context context;
	public LoginAction(Context context){
		this.context=context;
	}
	public boolean login(int id, String pwd) {
		UserDao uDao=new UserDao(context);
		User u=uDao.get(id);
		if(u==null){
			info="�û�������";
			return false;
		}else if(!pwd.equals(u.getPwd())){
			info="�������";
			return false;
		}else{
			info="��½�ɹ�";
			currentUser=u;
		}
		return true;
	}
	public boolean login(String name, String pwd) {
		UserDao uDao=new UserDao(context);
		List<User> u=uDao.find(new String[]{"id","pwd","name"},"name=?",new String[]{name},null,null,null,null);
		if(u==null||u.size()==0){
			info="�û�������";
			return false;
		}
		return login(u.get(0).getId(),pwd);
	}
	public String getLogInfo() {
		return info;
	}
	

}
