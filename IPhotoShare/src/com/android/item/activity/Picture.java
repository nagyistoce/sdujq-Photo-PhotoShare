package com.android.item.activity;

import org.sdu.bmputil.BitmapTool;
import org.sdu.db.pojo.Photo;
import org.sdu.taskImp.UserAction;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.item.adapter.CommentAdapter;
import com.android.main.R;

public class Picture extends Activity{
	
	Photo p;
	UserAction uaciton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.picture);
		ListView list = (ListView) findViewById(R.id.comment_list);  
		list.setAdapter(new CommentAdapter(this));//调用ImageAdapter.java   
		TextView username=(TextView)findViewById(R.id.pic_username);
		TextView title=(TextView)findViewById(R.id.pic_titles);
		TextView time=(TextView)findViewById(R.id.pic_time);
		
		ImageView user=(ImageView)findViewById(R.id.pic_user);
		ImageView pic=(ImageView)findViewById(R.id.pic);
		
		//获取当前选择的是哪张照片
		Bundle bundle = this.getIntent().getExtras();
		int selected = bundle.getInt("select");
		int id=bundle.getInt("id");
		uaciton=new UserAction(this);
		p=uaciton.getPhotoById(id);
		user.setBackgroundResource(TestData.user_head_img[selected]);
		pic.setImageBitmap(BitmapTool.Bytes2Bimap(p.getData()));
		username.setText("昵称："+TestData.name[selected]);
		title.setText("标题："+TestData.title[selected]);
		time.setText("时间："+TestData.times[selected]);
		//设置焦点，保证打开后显示顶端的内容
		username.setFocusable(true);
		username.setFocusableInTouchMode(true);
		username.requestFocus();
		
		//返回按钮
		Button back=(Button)findViewById(R.id.pic_back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Picture.this.finish();
			}
		});
	}
}
