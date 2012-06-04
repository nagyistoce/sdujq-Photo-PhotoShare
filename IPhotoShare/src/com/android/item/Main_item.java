package com.android.item;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.android.item.activity.Picture;
import com.android.item.adapter.MainAdapter;
import com.android.main.R;

public class Main_item extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_home);
		GridView gridview=(GridView)findViewById(R.id.gridview);//找到main.xml中定义gridview 的id      
		gridview.setAdapter(new MainAdapter(this));//调用ImageAdapter.java   
		gridview.setOnItemClickListener(new OnItemClickListener(){//监听事件    
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)   
			{       
				//Toast.makeText(Main_item.this, ""+position,Toast.LENGTH_SHORT).show();//显示信息;     
				Intent intent =new Intent();
				intent.setClass(Main_item.this, Picture.class);
				Bundle bundle = new Bundle();
				bundle.putInt("select", position);
				bundle.putInt("id", ((Integer)view.getTag()).intValue());
				intent.putExtras(bundle);
				Main_item.this.startActivity(intent);          
			}
		});  
		
	}

}
