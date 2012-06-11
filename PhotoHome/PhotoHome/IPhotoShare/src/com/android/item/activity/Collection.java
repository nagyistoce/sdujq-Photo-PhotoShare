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
import android.widget.GridView;

import com.android.item.adapter.CollectionAdapter;
import com.android.main.Login_Reg;
import com.android.main.R;

public class Collection extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		if (Login_Reg.theme_num == 1)
			setContentView(R.layout.my_collect2);
		else
			setContentView(R.layout.my_collect);
		GridView gridview = (GridView) findViewById(R.id.collect_gridview);// 找到main.xml中定义gridview
																			// 的id
		gridview.setAdapter(new CollectionAdapter(this));// 调用ImageAdapter.java
		gridview.setOnItemClickListener(new OnItemClickListener() {// 监听事件
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// Toast.makeText(Collection.this,
						// ""+position,Toast.LENGTH_SHORT).show();//显示信息;
						Intent intent = new Intent();
						intent
								.setClass(Collection.this,
										Collect_OneAlbum.class);
						Collection.this.startActivity(intent);
					}
				});

		// 返回按钮
		Button back = (Button) findViewById(R.id.collect_back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Collection.this.finish();
			}
		});

	}
}
