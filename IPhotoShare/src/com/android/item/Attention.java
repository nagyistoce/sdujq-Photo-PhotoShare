package com.android.item;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.android.item.activity.Picture;
import com.android.item.adapter.AttentionAdapter;
import com.android.main.R;

public class Attention extends Activity{


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_attention);
		ListView list = (ListView) findViewById(R.id.attention_list);  
		list.setAdapter(new AttentionAdapter(this));//调用ImageAdapter.java   
		list.setOnItemClickListener(new OnItemClickListener(){//监听事件    
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)   
			{       
				Intent intent =new Intent();
				intent.setClass(Attention.this, Picture.class);
				Bundle bundle = new Bundle();
				bundle.putInt("select", position);
				bundle.putInt("id", ((Integer)view.getTag()).intValue());
				intent.putExtras(bundle);
				Attention.this.startActivity(intent);      
			}
		}); 
	}

}
