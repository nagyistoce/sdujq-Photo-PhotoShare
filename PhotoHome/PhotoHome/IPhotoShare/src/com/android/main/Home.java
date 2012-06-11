package com.android.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.item.Attention;
import com.android.item.Main_item;
import com.android.item.Map;
import com.android.item.Person;
import com.android.item.Photo;
import com.google.android.maps.MapActivity;

/**
 * 进入应用主界面
 * 
 * @author ls
 * 
 */
public class Home extends MapActivity {
	private Button attention;
	private Button home;
	private Button collect;
	private Button photo;
	private Button person;
	private ViewPager viewPager;
	private ImageView cycle;
	private Button title;
	// private Button exit;
	private LocalActivityManager manager = null;
	private List<View> listViews;
	/** 圆环平移动画对象 */
	private Animation animation;
	/** 记录当前圆环所处的x坐标 */
	private int positon_x = 0;
	/** 圆环的宽度 */
	private int c_width;
	private long exitTime = 0;

	public static Context home_con;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		if (Login_Reg.theme_num == 1)
			setContentView(R.layout.main2);
		else
			setContentView(R.layout.main);

		home_con = Home.this;

		manager = new LocalActivityManager(this, true);
		manager.dispatchCreate(savedInstanceState);

		listViews = new ArrayList<View>();
		Intent intent = new Intent(this, Main_item.class);
		listViews.add(getView("Home", intent));
		Intent intent2 = new Intent(this, Attention.class);
		listViews.add(getView("Attention", intent2));
		Intent intent3 = new Intent(this, Map.class);
		listViews.add(getView("Map", intent3));
		Intent intent4 = new Intent(this, Photo.class);
		listViews.add(getView("Photo", intent4));
		Intent intent5 = new Intent(this, Person.class);
		listViews.add(getView("Person", intent5));

		viewPager = (ViewPager) findViewById(R.id.guidePages);
		viewPager.setAdapter(new GuidePageAdapter());
		viewPager.setOnPageChangeListener(new GuidePageChangeListener());

		initial_view();
		
	}

	class GuidePageAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return listViews.size();
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
			((ViewPager) arg0).removeView(listViews.get(arg1));
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(listViews.get(arg1));
			return listViews.get(arg1);
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

	/**
	 * 实现OnPageChangeListener
	 * 
	 * @author yyt&&ls
	 * 
	 */
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
				animation = new TranslateAnimation(positon_x, 0, 0, 0);
				positon_x = 0;
				if (Login_Reg.theme_num == 0) {
					title.setBackgroundResource(R.drawable.title_home);
					cycle.setBackgroundResource(R.drawable.cycle_1);
				} else {
					title.setBackgroundResource(R.drawable.title_home_2);
				}
			} else if (arg0 == 1) {
				animation = new TranslateAnimation(positon_x, c_width, 0, 0);

				positon_x = c_width;
				if (Login_Reg.theme_num == 0) {
					title.setBackgroundResource(R.drawable.title_attention);
					cycle.setBackgroundResource(R.drawable.cycle_2);
				} else {
					title.setBackgroundResource(R.drawable.title_attention_2);
				}
			} else if (arg0 == 2) {
				animation = new TranslateAnimation(positon_x, c_width * 2, 0, 0);

				positon_x = c_width * 2;
				if (Login_Reg.theme_num == 0) {
					title.setBackgroundResource(R.drawable.title_map);
					cycle.setBackgroundResource(R.drawable.cycle_3);
				} else {
					title.setBackgroundResource(R.drawable.title_map_2);
				}
			} else if (arg0 == 3) {
				animation = new TranslateAnimation(positon_x, c_width * 3, 0, 0);

				if (Login_Reg.theme_num == 0) {
					title.setBackgroundResource(R.drawable.title_photo);
					cycle.setBackgroundResource(R.drawable.cycle_4);
				} else {
					title.setBackgroundResource(R.drawable.title_photo_2);
				}
				positon_x = c_width * 3;
			} else if (arg0 == 4) {
				animation = new TranslateAnimation(positon_x, c_width * 4, 0, 0);

				if (Login_Reg.theme_num == 0) {
					title.setBackgroundResource(R.drawable.title_person);
					cycle.setBackgroundResource(R.drawable.cycle_1);
				} else {
					title.setBackgroundResource(R.drawable.title_person_2);
				}
				positon_x = c_width * 4;
			}
			animation.setFillAfter(true);
			animation.setDuration(200);
			cycle.startAnimation(animation);
		}
	}

	/**
	 * 屏幕下方五个按钮的内部监听类
	 * 
	 * @author yyt&&ls
	 * 
	 */
	class ButtonListener implements OnClickListener {
		int item = 0;

		public ButtonListener(int item) {
			this.item = item;
		}

		@Override
		public void onClick(View v) {
			viewPager.setAdapter(new GuidePageAdapter());
			viewPager.setOnPageChangeListener(new GuidePageChangeListener());
			viewPager.setCurrentItem(this.item);
		}

	}

	/***
	 * 获取相应页面的activity
	 * 
	 * @param id
	 *            对应Id号
	 * @param intent
	 *            Intent对象
	 * @return View
	 */
	private View getView(String id, Intent intent) {
		return manager.startActivity(id, intent).getDecorView();
	}

	/**
	 * 从初始化界面控件
	 */
	private void initial_view() {
		cycle = (ImageView) findViewById(R.id.cycle);
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;// 获取分辨率宽度
		c_width = screenW / 5;

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

		title = (Button) findViewById(R.id.title);
		// exit = (Button) findViewById(R.id.exit);
		// exit.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// Home.this.finish();
		// }
		// });

		// 实现屏幕滑动的速率控制
		// try {
		// Field mScroller;
		// mScroller = ViewPager.class.getDeclaredField("mScroller");
		// mScroller.setAccessible(true);
		// FixedSpeedScroller scroller = new FixedSpeedScroller(viewPager
		// .getContext());
		// mScroller.set(viewPager, scroller);
		// } catch (NoSuchFieldException e) {
		// } catch (IllegalArgumentException e) {
		// } catch (IllegalAccessException e) {
		// }
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == Photo.TAKE_PHOTO_REQUEST_CODE
				|| requestCode == Photo.SELECT_PHOTO_REQUEST_CODE) {

			if (resultCode == RESULT_OK) {
				Uri uri = null;

				if (requestCode == Photo.SELECT_PHOTO_REQUEST_CODE) {
					uri = data.getData();
				} else if (requestCode == Photo.TAKE_PHOTO_REQUEST_CODE) {
					uri = Uri.fromFile(new File("/mnt/sdcard/temp99.jpg"));
				}

				if (uri != null) {
					final Intent intent1 = new Intent(
							"com.android.camera.action.CROP");
					intent1.setDataAndType(uri, "image/*");
					intent1.putExtra("crop", "true");
					intent1.putExtra("aspectX", 1);
					intent1.putExtra("aspectY", 1);
					intent1.putExtra("outputX", 300);
					intent1.putExtra("outputY", 300);
					intent1.putExtra("return-data", true);
					intent1.putExtra("output", Uri.fromFile(new File(
							"/mnt/sdcard/temp99.jpg")));
					intent1.putExtra("outputFormat", "JPEG");
					startActivityForResult(intent1,
							Photo.CUT_PHOTO_REQUEST_CODE);

				}
			}
		} else if (requestCode == Photo.CUT_PHOTO_REQUEST_CODE) {
			if (resultCode == RESULT_OK && data != null) {
				Intent intent = new Intent();
				intent.setClass(Home.this, Handle_photo.class);
				Home.this.startActivity(intent);
			}
		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	public void ExitApp() {
		if ((System.currentTimeMillis() - exitTime) > 2000) {
			Toast.makeText(Home.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
			exitTime = System.currentTimeMillis();
		} else {
			Home.this.finish();
		}

	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
        	ExitApp();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

//	public boolean onKeyUp(int keyCode, KeyEvent event) {
//		if (keyCode == KeyEvent.KEYCODE_BACK && event.isTracking()
//				&& !event.isCanceled()) {
//			ExitApp();
//			return true;
//		}
//		return super.onKeyUp(keyCode, event);
//	}

}