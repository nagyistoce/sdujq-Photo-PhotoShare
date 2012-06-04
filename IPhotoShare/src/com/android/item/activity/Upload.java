package com.android.item.activity;

import org.sdu.taskImp.UserAction;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.main.Home;
import com.android.main.R;

public class Upload  extends Activity{
	ImageView img;
	TextView title;
	TextView info;
	CheckBox torenren;
	CheckBox tosina;
	CheckBox toqq;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.upload);
		img=(ImageView) findViewById(R.id.up_img);
		title=(TextView) findViewById(R.id.up_title);
		info=(TextView) findViewById(R.id.up_descript);
		torenren=(CheckBox) findViewById(R.id.renren_check);
		tosina=(CheckBox) findViewById(R.id.xinlang_check);
		toqq=(CheckBox) findViewById(R.id.tengxun_check);
		final Bitmap bmp=BitmapFactory.decodeFile("/mnt/sdcard/temp100.jpg");
		img.setImageBitmap(bmp);
		//返回按钮
		Button back=(Button)findViewById(R.id.up_back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent =new Intent();
				intent.setClass(Upload.this, Home.class);
				Upload.this.startActivity(intent); 
				Upload.this.finish();
			}
		});
		
		//确定按钮，判断是否上传成功并给出提示
		Button okbtn = (Button)findViewById(R.id.sns_configure);
		okbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				UserAction uation=new UserAction(Upload.this);
				uation.savePhoto(BitmapFactory.decodeFile("/mnt/sdcard/temp100.jpg"), title.getText().toString(),info.getText().toString());
				Toast.makeText(Upload.this, "上传成功",Toast.LENGTH_SHORT).show();
				Intent intent =new Intent();
				intent.setClass(Upload.this, Home.class);
				Upload.this.startActivity(intent); 
				Upload.this.finish();
			}
		});
	
	}

}
