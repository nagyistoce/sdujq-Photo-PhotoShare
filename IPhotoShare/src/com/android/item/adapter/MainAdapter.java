package com.android.item.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.item.activity.TestData;
import com.android.main.R;

public class MainAdapter extends BaseAdapter {
	private Context mContext;

	public MainAdapter(Context c) {
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
		    

		
		ImageView imageview;
		
		View view = View.inflate(mContext, R.layout.mainhome_item, null);
		imageview = (ImageView) view.findViewById(R.id.grid_image);
		//imageview.setLayoutParams(new GridView.LayoutParams(150, 150));
		//imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
		//imageview.setPadding(10, 10, 10, 10);
		TextView love = (TextView)view.findViewById(R.id.grid_text1);
		love.setText(TestData.text[position]); 
		TextView com = (TextView)view.findViewById(R.id.grid_text2);
		com.setText(TestData.text[position]); 
		imageview.setBackgroundResource(TestData.images[position]);
		return view;
	}

	

}
