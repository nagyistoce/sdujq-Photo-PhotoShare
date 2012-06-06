package com.android.item.activity;

import java.util.List;

import org.sdu.db.pojo.Photo;
import org.sdu.taskImp.UserAction;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

import com.android.item.adapter.Collect_img_Adapter;
import com.android.main.R;

public class Collect_img extends Activity implements ViewFactory,
		OnItemSelectedListener {
	// 选中的索引
	private int selected = 0;
	// 手势向上向下 落点 x 坐标
	private int upX, downX;

	private ImageSwitcher is;
	private Gallery g;
	UserAction ua;
	List<Photo> data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.my_collect_img);
		g = (Gallery) findViewById(R.id.co_gallery);
		ua=new UserAction(this);
		data=ua.getAllPhoto(ua.getCurrentUser());
		g.setAdapter(new Collect_img_Adapter(this,data));
		g.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(Collect_img.this, "当前第" + (position + 1) + "张",
						Toast.LENGTH_SHORT).show();
				//设置ImageSwitcher和Gallery显示的图片
				is.setImageDrawable(new BitmapDrawable(ua.getBitmap(data.get(position))));
				g.setSelection(position-1, true);
			}
		});

		//得到ImageSwitcher对象
		is = (ImageSwitcher) findViewById(R.id.co_imageSwitcher);
		// 实现并设置工厂内部接口的makeView方法，用来显示视图。
		is.setFactory(Collect_img.this);
		is.setOnTouchListener(touchListener);

		// 设置切入动画
		is.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(),
				android.R.anim.fade_in));
		// 设置切出动画
		is.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(),
				android.R.anim.fade_out));
		
		//获取当前选择的是哪张照片
		Bundle bundle = this.getIntent().getExtras();
		selected = bundle.getInt("select");
		//设置ImageSwitcher和Gallery显示的图片
		is.setImageDrawable(new BitmapDrawable(ua.getBitmap(data.get(selected))));
		g.setSelection(selected, true);
		
		//返回按钮
		Button back=(Button)findViewById(R.id.co_img_back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Collect_img.this.finish();
			}
		});

	}

	OnTouchListener touchListener = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {

			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				// 取得 按下时的 x坐标
				downX = (int) event.getX();
				// True if the listener has consumed the event, false otherwise.
				// 我的理解是：如果event销毁了，结束了一个时间，就返回true；下一个event is new
				return true;
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				// 取得 松开时的 x坐标
				upX = (int) event.getX();
				// 手指 向右划， 看前一张
				if (upX - downX > 100) {
					// 如果是第一张，则给出 最后一张照片（前一张）
					if (g.getSelectedItemPosition() == 0) {
						selected = g.getCount() - 1;
					} else {
						selected = g.getSelectedItemPosition() - 1; // 上一张
					}
				} else if (downX - upX > 100) {
					// 手指 向左划， 看下一张

					if (g.getSelectedItemPosition() == g.getCount() - 1) {
						selected = 0;
					} else {
						selected = g.getSelectedItemPosition() + 1;// 下一张
					}
				}

				// int position, boolean animate
				g.setSelection(selected, true);
				is.setImageDrawable(new BitmapDrawable(ua.getBitmap(data.get(selected)))); 
				Toast.makeText(Collect_img.this, "当前第" + (selected + 1) + "张",
						Toast.LENGTH_SHORT).show();
				return true;
			}
			return false;
		}
	};

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		 is.setImageResource(TestData.images[position]);
		 selected = position;
		 
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {

	}

	@Override
	public View makeView() {
		ImageView i = new ImageView(this);
		i.setBackgroundColor(0xFFFFFF);
		i.setScaleType(ImageView.ScaleType.FIT_CENTER);
		i.setLayoutParams(new ImageSwitcher.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		
		return i;
	}

}
