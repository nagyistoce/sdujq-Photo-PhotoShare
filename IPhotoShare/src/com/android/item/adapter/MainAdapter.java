package com.android.item.adapter;

import java.util.ArrayList;
import java.util.List;

import org.sdu.bmputil.BitmapTool;
import org.sdu.db.pojo.Photo;
import org.sdu.taskImp.UserAction;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.item.activity.TestData;
import com.android.main.R;

public class MainAdapter extends BaseAdapter {
	private Context mContext;
	private List<Photo> plist;
	UserAction uaction;
	public MainAdapter(Context c) {
		mContext = c;
		uaction=new UserAction(c);
		List<Integer> ids=uaction.getHotPhotoIdList(50);
		plist=new ArrayList<Photo>();
		for(int i:ids){
			plist.add(uaction.getPhotoById(i));
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return plist.size();
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
		love.setText(""+plist.get(position).getViewNum()); 
		TextView com = (TextView)view.findViewById(R.id.grid_text2);
		com.setText(TestData.text[position]); 
		Bitmap bmp=uaction.getBitmap(plist.get(position));
		imageview.setImageBitmap(bmp);
		view.setTag(plist.get(position).getId());
		return view;
	}

	

}
