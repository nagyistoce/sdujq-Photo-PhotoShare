package com.android.main;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.android.item.Login;
import com.android.item.Register;

public class Login_Reg extends Activity {
	public static ViewPager viewPager;
	public static int theme_num = 1;
	private Button title;
	private LocalActivityManager manager = null;
	private List<View> listViews;
	private Button exit;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		if (Login_Reg.theme_num == 1)
			setContentView(R.layout.login_reg2);
		else
			setContentView(R.layout.login_reg);

		manager = new LocalActivityManager(this, true);
		manager.dispatchCreate(savedInstanceState);

		listViews = new ArrayList<View>();
		Intent intent = new Intent(this, Login.class);
		listViews.add(getView("Login", intent));
		Intent intent2 = new Intent(this, Register.class);
		listViews.add(getView("Attention", intent2));

		viewPager = (ViewPager) findViewById(R.id.guidePages);
		title = (Button) findViewById(R.id.title);

		viewPager.setAdapter(new GuidePageAdapter());
		viewPager.setOnPageChangeListener(new GuidePageChangeListener());

		// exit = (Button) findViewById(R.id.exit);
		// exit.setEnabled(false);
		// exit.setVisibility(View.GONE);
		// exit.setOnClickListener(new OnClickListener() {
		//			
		// @Override
		// public void onClick(View v) {
		// Login_Reg.this.finish();
		// }
		// });

		// //实现屏幕滑动的速率控制
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
				if (Login_Reg.theme_num == 0) {
					title.setBackgroundResource(R.drawable.title_login);
					title.setEnabled(false);
					exit.setVisibility(View.GONE);
					// exit.setBackgroundResource(R.drawable.exit);
					// exit.setEnabled(true);
				}

			} else if (arg0 == 1) {
				if (Login_Reg.theme_num == 0) {
					title.setBackgroundResource(R.drawable.back);
					title.setEnabled(true);
					title.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							viewPager.setCurrentItem(0);
						}
					});
					exit.setVisibility(View.VISIBLE);
					exit.setBackgroundResource(R.drawable.title_reg);
				}
			}
		}
	}

	class ButtonListener implements OnClickListener {
		int item = 0;

		public ButtonListener(int item) {
			this.item = item;
		}

		@Override
		public void onClick(View v) {
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

}
