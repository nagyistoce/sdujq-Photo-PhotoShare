package com.android.item.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.item.activity.TestData;
import com.android.main.R;

public class CollectionAdapter extends BaseAdapter {
	private Context mContext;

	public CollectionAdapter(Context c) {
		mContext = c;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return TestData.images.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) { 
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		View view = View.inflate(mContext, R.layout.my_collect_item, null);
		ImageView imageview = (ImageView) view.findViewById(R.id.albumimg);
		imageview.setBackgroundResource(TestData.images[position]);
		
		TextView title = (TextView)view.findViewById(R.id.albumtitle);
		title.setText(TestData.title[position]); 
		 
		
		return view;
	}

	

}
