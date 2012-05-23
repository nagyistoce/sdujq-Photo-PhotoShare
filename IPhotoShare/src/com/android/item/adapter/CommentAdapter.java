package com.android.item.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.item.activity.TestData;
import com.android.main.R;

public class CommentAdapter extends BaseAdapter{

	private Context mContext;
	public CommentAdapter(Context c) {
		mContext = c;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return TestData.comments.length;
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

		
		View view = View.inflate(mContext, R.layout.comment_item, null);
		
		
		TextView username = (TextView)view.findViewById(R.id.comment_user);
		username.setText(TestData.name[position]); 
		
		TextView time = (TextView)view.findViewById(R.id.comment_time);
		time.setText(TestData.times[position]);
		
		TextView comment = (TextView)view.findViewById(R.id.comments);
		comment.setText(TestData.comments[position]);
		return view;
	}
	
	
	
	

}
