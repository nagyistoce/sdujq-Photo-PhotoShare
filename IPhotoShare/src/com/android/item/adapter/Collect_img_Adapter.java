package com.android.item.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.item.activity.TestData;
import com.android.main.R;

/**
 * 供Gallery调用
 * 
 * @author ETHAN
 * 
 */
public class Collect_img_Adapter extends BaseAdapter {

	private Context mContext;

	public Collect_img_Adapter(Context c) {
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
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		View view = View.inflate(mContext, R.layout.my_collect_img_item, null);
		ImageView imageview = (ImageView) view.findViewById(R.id.co_img);
		imageview.setBackgroundResource(TestData.images[position]);

		TextView title = (TextView) view.findViewById(R.id.co_title);
		title.setText(TestData.text[position]);

		return view;
	}

	

}
