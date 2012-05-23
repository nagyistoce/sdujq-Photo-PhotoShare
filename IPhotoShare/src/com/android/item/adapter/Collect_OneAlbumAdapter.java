package com.android.item.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.item.activity.TestData;
import com.android.main.R;

public class Collect_OneAlbumAdapter extends BaseAdapter{
	private Context mContext;

	public Collect_OneAlbumAdapter(Context c) {
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
		
		View view = View.inflate(mContext, R.layout.my_collect_onealbum_item, null);
		ImageView imageview = (ImageView) view.findViewById(R.id.al_img);
		//imageview.setImageResource(grid_images[position]);
		imageview.setBackgroundResource(TestData.images[position]);

		TextView title = (TextView)view.findViewById(R.id.al_imgtitle);
		title.setText(TestData.title[position]);
		
		TextView user = (TextView)view.findViewById(R.id.al_username);
		user.setText(TestData.name[position]);
		 
		
		return view;
	}

	
}
