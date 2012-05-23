package com.android.item;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.android.item.activity.About;
import com.android.item.activity.Collection;
import com.android.item.activity.SetSns;
import com.android.item.activity.MyUpload;
import com.android.main.R;

public class Person extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_person);
		//我的收藏
		Button collect=(Button)findViewById(R.id.mycollect);
		collect.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent =new Intent();
				intent.setClass(Person.this, Collection.class);
				Person.this.startActivity(intent);
			}
		});
		
		//我上传的照片
		Button upload=(Button)findViewById(R.id.myupload);
		upload.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent =new Intent();
				intent.setClass(Person.this, MyUpload.class);
				Person.this.startActivity(intent);
			}
		});
		//社区绑定
		Button sns=(Button)findViewById(R.id.mysns);
		sns.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent =new Intent();
				intent.setClass(Person.this, SetSns.class);
				Person.this.startActivity(intent);
			}
		});
		//社区绑定
		Button about=(Button)findViewById(R.id.about);
		about.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent =new Intent();
				intent.setClass(Person.this, About.class);
				Person.this.startActivity(intent);
			}
		});
	}

}
