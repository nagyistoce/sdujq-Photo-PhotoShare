package com.android.item.adapter;

import java.sql.Timestamp;
import java.util.List;

import org.sdu.bmputil.BitmapTool;
import org.sdu.db.dao.DynamicDao;
import org.sdu.db.dao.PhotoDao;
import org.sdu.db.dao.UserDao;
import org.sdu.db.pojo.Dynamic;
import org.sdu.db.pojo.Photo;
import org.sdu.taskImp.UserAction;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.item.activity.TestData;
import com.android.main.R;

public class AttentionAdapter extends BaseAdapter{

	private Context mContext;
	public List<Integer> data;
	UserAction ua;
	public AttentionAdapter(Context c) {
		mContext = c;
		ua=new UserAction(c);
		data=ua.getDynamicIdList(ua.getCurrentUser());
	}
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		
		View view = View.inflate(mContext, R.layout.attention_list_item, null);
		Dynamic item=new DynamicDao(mContext).get(data.get(position));
		Photo p=new PhotoDao(mContext).get(item.getPhotoId());
		ImageView user_head = (ImageView) view.findViewById(R.id.list_user);
		ImageView img = (ImageView) view.findViewById(R.id.list_image);
		user_head.setImageResource(TestData.user_head_img[(int)(TestData.user_head_img.length*Math.random())]);
		UserAction ua=new UserAction(mContext);
		img.setImageBitmap(ua.getBitmap(p));
		
		TextView username = (TextView)view.findViewById(R.id.list_username);
		username.setText(new UserDao(mContext).get(item.getUserId()).getName()); 
		TextView title = (TextView)view.findViewById(R.id.list_title);
		title.setText(item.getTitle()); 
		TextView time = (TextView)view.findViewById(R.id.list_time);
		time.setText(new Timestamp(item.getTime()).toLocaleString());
		TextView love = (TextView)view.findViewById(R.id.list_lovet);
		love.setText(p.getViewNum()+""); 
		TextView com = (TextView)view.findViewById(R.id.list_comt);
		com.setText(ua.getArgumentList(p.getId()).size()+""); 
		view.setTag(p.getId());

		return view;
	}
	

}
