package com.android.main;

import java.util.ArrayList;

import android.app.Activity;
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
import android.widget.ImageView;

/**
 * 进入应用主界面
 * 
 * @author ls
 * 
 */
public class Home extends Activity {
	private Button attention;
	private Button home;
	private Button collect;
	private Button photo;
	private Button person;
	private ViewPager viewPager;
	private ArrayList<View> pageViews;
	private ImageView cycle_1;
	private ImageView cycle_2;
	private ImageView cycle_3;
	private ImageView cycle_4;
	private ImageView cycle_5;
	private ImageView cycle;
	private Button title;
	private Button exit;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);

		cycle_1 = (ImageView) findViewById(R.id.cycle_1);
		cycle_2 = (ImageView) findViewById(R.id.cycle_2);
		cycle_3 = (ImageView) findViewById(R.id.cycle_3);
		cycle_4 = (ImageView) findViewById(R.id.cycle_4);
		cycle_5 = (ImageView) findViewById(R.id.cycle_5);
		title = (Button)findViewById(R.id.title);
		cycle = cycle_1;
		
		LayoutInflater inflater = getLayoutInflater();
		pageViews = new ArrayList<View>();
		pageViews.add(inflater.inflate(R.layout.item_home, null));
		pageViews.add(inflater.inflate(R.layout.item_attention, null));
		pageViews.add(inflater.inflate(R.layout.item_collect, null));
		pageViews.add(inflater.inflate(R.layout.item_photo, null));
		pageViews.add(inflater.inflate(R.layout.item_person, null));

		viewPager = (ViewPager) findViewById(R.id.guidePages);

		viewPager.setAdapter(new GuidePageAdapter());
		viewPager.setOnPageChangeListener(new GuidePageChangeListener());
		
		
//		try {
//            Field mScroller;
//            mScroller = ViewPager.class.getDeclaredField("mScroller");
//            mScroller.setAccessible(true); 
//            FixedSpeedScroller scroller = new FixedSpeedScroller(viewPager.getContext());
//            scroller.setDuration(1000);
//            mScroller.set(viewPager, scroller);
//        } catch (NoSuchFieldException e) {
//        } catch (IllegalArgumentException e) {
//        } catch (IllegalAccessException e) {
//        }
        
        

		attention = (Button) findViewById(R.id.attention);
		home = (Button) findViewById(R.id.home);
		collect = (Button) findViewById(R.id.collect);
		photo = (Button) findViewById(R.id.photo);
		person = (Button) findViewById(R.id.person);
		
		home.setOnClickListener(new ButtonListener(0));
		attention.setOnClickListener(new ButtonListener(1));
		collect.setOnClickListener(new ButtonListener(2));
		photo.setOnClickListener(new ButtonListener(3));
		person.setOnClickListener(new ButtonListener(4));
		
		
		exit = (Button)findViewById(R.id.exit);
		exit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Home.this.finish();
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
			cycle.setVisibility(View.INVISIBLE);
			if (arg0 == 0) {
				cycle_1.setVisibility(View.VISIBLE);
				title.setBackgroundResource(R.drawable.title_home);
				cycle = cycle_1;
			} else if(arg0==1){
				cycle_2.setVisibility(View.VISIBLE);
				title.setBackgroundResource(R.drawable.title_attention);
				cycle = cycle_2;
			}else if(arg0==2){
				cycle_3.setVisibility(View.VISIBLE);
				title.setBackgroundResource(R.drawable.title_collect);
				cycle = cycle_3;
			}else if(arg0==3){
				cycle_4.setVisibility(View.VISIBLE);
				title.setBackgroundResource(R.drawable.title_photo);
				cycle = cycle_4;
			}else if(arg0==4){
				cycle_5.setVisibility(View.VISIBLE);
				title.setBackgroundResource(R.drawable.title_person);
				cycle = cycle_5;
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