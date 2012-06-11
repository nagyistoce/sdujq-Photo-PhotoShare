package com.android.item.adapter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.sdu.db.dao.UserDao;
import org.sdu.db.pojo.Argument;
import org.sdu.taskImp.UserAction;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.item.activity.TestData;
import com.android.main.Login_Reg;
import com.android.main.R;

public class CommentAdapter extends BaseAdapter{
	List<Argument> data=new ArrayList<Argument>();
	private Context mContext;
	UserAction uaction;
	public CommentAdapter(Context c,int id) {
		mContext = c;
		uaction=new UserAction(c);
		data=uaction.getArgumentList(id);
	}
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		return data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view;
		if (Login_Reg.theme_num == 1)
			view = View.inflate(mContext, R.layout.comment_item2, null);
		else
		    view = View.inflate(mContext, R.layout.comment_item, null);
		Argument item=data.get(position);
		UserDao udao=new UserDao(mContext);
		TextView username = (TextView)view.findViewById(R.id.comment_user);
		username.setText(udao.get(item.getUserId()).getName()); 
		
		TextView time = (TextView)view.findViewById(R.id.comment_time);
		time.setText(new Timestamp(item.getTime()).toLocaleString());
		
		TextView comment = (TextView)view.findViewById(R.id.comments);
		comment.setText(item.getInfo());
		return view;
	}
	
	
	
	

}
