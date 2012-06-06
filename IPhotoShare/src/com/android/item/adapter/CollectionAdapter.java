package com.android.item.adapter;

import java.util.List;

import org.sdu.bmputil.BitmapTool;
import org.sdu.db.dao.PhotoDao;
import org.sdu.db.pojo.Collection;
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

public class CollectionAdapter extends BaseAdapter {
	private Context mContext;
	public List<Collection> data;
	public CollectionAdapter(Context c) {
		mContext = c;
		UserAction ua=new UserAction(mContext);
		data=ua.getCollectionList();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
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
		Photo p=new PhotoDao(mContext).get(data.get(position).getPhotoId());
		View view = View.inflate(mContext, R.layout.my_collect_item, null);
		ImageView imageview = (ImageView) view.findViewById(R.id.albumimg);
		imageview.setImageBitmap(BitmapTool.Bytes2Bimap(p.getData()));		
		TextView title = (TextView)view.findViewById(R.id.albumtitle);
		title.setText(p.getTitle()); 
		 
		
		return view;
	}

	

}
