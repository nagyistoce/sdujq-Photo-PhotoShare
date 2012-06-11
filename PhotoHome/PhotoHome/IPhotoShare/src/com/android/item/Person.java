package com.android.item;

import org.sdu.taskImp.UserAction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.item.activity.About;
import com.android.item.activity.Collection;
import com.android.item.activity.MyUpload;
import com.android.item.activity.Set;
import com.android.main.Login_Reg;
import com.android.main.R;

public class Person extends Activity {
	TextView name;
	TextView att;
	TextView fans;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (Login_Reg.theme_num == 1)
			setContentView(R.layout.item_person2);
		else
			setContentView(R.layout.item_person);
		//头像
		UserAction ua=new UserAction(this);
		ImageView head=(ImageView)findViewById(R.id.myphoto);
		int res=ua.getHeadRes();
		head.setImageResource(res);
		Log.e("qq", res+"");
		name=(TextView)findViewById(R.id.myname_lab_2);
		att=(TextView)findViewById(R.id.myatt_lab_2);
		fans=(TextView)findViewById(R.id.myfans_lab_2);
		name.setText(ua.getCurrentUser().getName());
		att.setText(ua.getFriendShipIdList(ua.getCurrentUser()).size()+"");
		fans.setText(""+ua.getMyFollowers().size());
//		head.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				//更改头像
//			}
//		});
		// 我的收藏
		Button collect = (Button) findViewById(R.id.mycollect);
		collect.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(Person.this, Collection.class);
				Person.this.startActivity(intent);
			}
		});

		// 我上传的照片
		Button upload = (Button) findViewById(R.id.myupload);
		upload.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(Person.this, MyUpload.class);
				Person.this.startActivity(intent);
			}
		});
		Button set = (Button) findViewById(R.id.myset);
		set.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(Person.this, Set.class);
				Person.this.startActivity(intent);
				Person.this.finish();
			}
		});
		Button about = (Button) findViewById(R.id.about);
		about.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(Person.this, About.class);
				Person.this.startActivity(intent);
			}
		});
	}

}
