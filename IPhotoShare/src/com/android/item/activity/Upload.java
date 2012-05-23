package com.android.item.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.android.main.Home;
import com.android.main.R;

public class Upload  extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.upload);
		
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
				Toast.makeText(Upload.this, "提示上传是否成功",Toast.LENGTH_SHORT).show();
			}
		});
	
	}

}
