package com.android.main;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Login_Reg extends Activity{
	private ViewPager viewPager;
	private ArrayList<View> pageViews;
	private Button title;
	//private ViewGroup login;
	//private ViewGroup main;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login_reg);
		
		LayoutInflater inflater = getLayoutInflater();
		pageViews = new ArrayList<View>();
		pageViews.add(inflater.inflate(R.layout.item_login, null));
		pageViews.add(inflater.inflate(R.layout.item_reg, null));
		
	//	login = (ViewGroup)inflater.inflate(R.layout.item_login, null);  
	//	main = (ViewGroup)inflater.inflate(R.layout.login_reg, null);  

		viewPager = (ViewPager) findViewById(R.id.guidePages);
		title = (Button)findViewById(R.id.title);

		
		viewPager.setAdapter(new GuidePageAdapter());
		viewPager.setOnPageChangeListener(new GuidePageChangeListener());   
		
		Button login_b = (Button)findViewById(R.id.title);
		Button exit = (Button)findViewById(R.id.exit);
		
//		EditText login_e = (EditText)login.findViewById(R.id.username);
//		EditText password = (EditText)login.findViewById(R.id.password);
		//login_e.clearFocus();
		//password.clearFocus();
		login_b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent =new Intent();
				intent.setClass(Login_Reg.this, Home.class);
				Login_Reg.this.startActivity(intent);
				Login_Reg.this.finish();
			}
		});
		
		exit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Login_Reg.this.finish();
			}
		});
		
	}

	class GuidePageAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return pageViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public int getItemPosition(Object object) {
			return super.getItemPosition(object);
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(pageViews.get(arg1));
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(pageViews.get(arg1));
			return pageViews.get(arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {

		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {

		}

		@Override
		public void finishUpdate(View arg0) {

		}
	}

	class GuidePageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageSelected(int arg0) {
			if (arg0 == 0) {
				title.setBackgroundResource(R.drawable.title_login);
				
			} else if(arg0==1){
				title.setBackgroundResource(R.drawable.title_reg);
			}
		}
	}
	
	class ButtonListener implements OnClickListener{
		int item = 0;

		public ButtonListener(int item){
			this.item = item;
		}
		@Override
		public void onClick(View v) {
			viewPager.setCurrentItem(this.item);
		}
		
	}


}
