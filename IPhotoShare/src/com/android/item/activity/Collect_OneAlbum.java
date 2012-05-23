package com.android.item.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.android.item.adapter.Collect_OneAlbumAdapter;
import com.android.main.R;

public class Collect_OneAlbum extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.my_collect_onealbum);
		ListView list = (ListView) findViewById(R.id.al_list);  
		list.setAdapter(new Collect_OneAlbumAdapter(this));//调用ImageAdapter.java   
		list.setOnItemClickListener(new OnItemClickListener(){//监听事件    
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)   
			{       
				//Toast.makeText(Collect_OneAlbum.this, ""+position,Toast.LENGTH_SHORT).show();//显示信息;     
				Intent intent =new Intent();
				intent.setClass(Collect_OneAlbum.this, Collect_img.class);
				Bundle bundle = new Bundle();
				bundle.putInt("select", position);
				intent.putExtras(bundle);
				Collect_OneAlbum.this.startActivity(intent);
				
			}
		});
		//返回按钮
		Button back=(Button)findViewById(R.id.al_back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Collect_OneAlbum.this.finish();
			}
		});
	}
}
