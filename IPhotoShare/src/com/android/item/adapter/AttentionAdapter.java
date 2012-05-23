package com.android.item.adapter;

import com.android.item.activity.TestData;
import com.android.main.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AttentionAdapter extends BaseAdapter{

	private Context mContext;
	public AttentionAdapter(Context c) {
		mContext = c;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return TestData.images.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		
		View view = View.inflate(mContext, R.layout.attention_list_item, null);
		ImageView user_head = (ImageView) view.findViewById(R.id.list_user);
		ImageView img = (ImageView) view.findViewById(R.id.list_image);
		user_head.setImageResource(TestData.user_head_img[position]);
		img.setImageResource(TestData.images[position]);
		
		TextView username = (TextView)view.findViewById(R.id.list_username);
		username.setText(TestData.name[position]); 
		TextView title = (TextView)view.findViewById(R.id.list_title);
		title.setText(TestData.title[position]); 
		TextView time = (TextView)view.findViewById(R.id.list_time);
		time.setText(TestData.times[position]);
		TextView love = (TextView)view.findViewById(R.id.list_lovet);
		love.setText(TestData.text[position]); 
		TextView com = (TextView)view.findViewById(R.id.list_comt);
		com.setText(TestData.text[position]); 
		
		return view;
	}
	

}
