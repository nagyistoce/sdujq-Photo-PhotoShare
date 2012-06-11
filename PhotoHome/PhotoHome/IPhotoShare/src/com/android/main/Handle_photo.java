package com.android.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.item.activity.Upload;
import com.android.tool.Handlers;

public class Handle_photo extends Activity {

	private ImageView imageView;
	public static Context h_context;
	Bitmap bitmap_temp;
	public static Bitmap bitmap;
	Bitmap bitmap_first;
	// Gallery galleryFilter;
	Button filters;
	Button frame;
	Button spin;
	Button scrawl;
	Button ok;
	Button reset;
	RelativeLayout rel;
	private Handler mHandler = null;
	private ProgressDialog mProgress;
	private Animation myAnimation_Alpha;

	Button hd1, hd2, hd3, hd4, hd5, hd6;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		if (Login_Reg.theme_num == 1)
			setContentView(R.layout.filter2);
		else
			setContentView(R.layout.filter);

		h_context = this;
		
		mHandler = new Handler() {
			public void handleMessage(Message msg) {
				closeProgress();
			}
		};

		myAnimation_Alpha = new AlphaAnimation(0.1f, 1.0f);
		myAnimation_Alpha.setDuration(500);

		ok = (Button) findViewById(R.id.ok);
		filters = (Button) findViewById(R.id.filters);
		frame = (Button) findViewById(R.id.frame);
		spin = (Button) findViewById(R.id.spin);
		scrawl = (Button) findViewById(R.id.round);
		reset = (Button)findViewById(R.id.reset);
		// galleryFilter = (Gallery) findViewById(R.id.galleryFilter);
		imageView = (ImageView) findViewById(R.id.imgfilter);
		rel = (RelativeLayout) findViewById(R.id.sub_filter);
		hd1 = (Button) findViewById(R.id.button1);
		hd2 = (Button) findViewById(R.id.button2);
		hd3 = (Button) findViewById(R.id.button3);
		hd4 = (Button) findViewById(R.id.button4);
		hd5 = (Button) findViewById(R.id.button5);
		hd6 = (Button) findViewById(R.id.button6);

		// galleryFilter.setAdapter(new ImageAdapter(Handle_photo.this));

		ContentResolver cr = getContentResolver();
		try {
			InputStream in = cr.openInputStream(Uri.fromFile(new File(
					"/mnt/sdcard/temp99.jpg")));
			bitmap_temp = BitmapFactory.decodeStream(in);
			imageView.setImageBitmap(bitmap_temp);
			bitmap = bitmap_temp;
			bitmap_first = bitmap_temp;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		filters.setOnClickListener(new buttonListener());
		frame.setOnClickListener(new buttonListener());
		spin.setOnClickListener(new buttonListener());
		scrawl.setOnClickListener(new buttonListener());
		ok.setOnClickListener(new buttonListener());
		reset.setOnClickListener(new buttonListener());

		// galleryFilter.setOnItemClickListener(new OnItemClickListener() {
		//
		// @Override
		// public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
		// long arg3) {
		// switch (arg2) {
		// case 0:
		// bitmap = Handlers.oldRemeber(bitmap_temp);
		// break;
		// case 1:
		// bitmap = Handlers.blurImageAmeliorate(bitmap_temp);
		// break;
		// case 2:
		// bitmap = Handlers.sharpenImageAmeliorate(bitmap_temp);
		// break;
		// case 3:
		// bitmap = Handlers.film(bitmap_temp);
		// break;
		// case 4:
		// bitmap = Handlers.emboss(bitmap_temp);
		// break;
		// case 5:
		// bitmap = Handlers.overlay(bitmap_temp, Handle_photo.this,
		// R.drawable.filter2);
		// // case 7:
		// // bitmap = Handlers.rotate(bitmap_temp,90);
		// // case 9:
		// // bitmap = Handlers.film(bitmap);
		// }
		// imageView.setImageBitmap(bitmap);
		// }
		// });

	}

	// class ImageAdapter extends BaseAdapter {
	// private Context mContext;
	// private String[] str = { "怀旧", "柔化", "锐化", "底片", "浮雕", "层叠" };
	//
	// private Integer[] mImage = {
	// R.drawable.hd1,
	// R.drawable.hd2,
	// R.drawable.hd3,
	// R.drawable.hd4,
	// R.drawable.hd5,
	// R.drawable.hd6,
	// };
	// public ImageAdapter(Context c) {
	// mContext = c;
	// }
	//
	// @Override
	// public int getCount() {
	// return str.length;
	// }
	//
	// @Override
	// public Object getItem(int position) {
	// return position;
	// }
	//
	// @Override
	// public long getItemId(int position) {
	// return position;
	// }
	//
	// @Override
	// public View getView(int position, View convertView, ViewGroup parent) {
	// ImageView i = new ImageView(mContext);
	// i.setBackgroundResource( mImage[position]);
	// // i.setLayoutParams(new Gallery.LayoutParams(100, 100));
	// i.setScaleType(ImageView.ScaleType.FIT_XY);
	// return i;
	// }
	// };

	class buttonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			if (v.getId() == filters.getId()) {
				initial_frame(1);
				rel.setVisibility(View.VISIBLE);
				rel.setAnimation(myAnimation_Alpha);
				myAnimation_Alpha.start();
			} else if (v.getId() == frame.getId()) {
				bitmap_temp = bitmap;
				initial_frame(2);
				rel.setVisibility(View.VISIBLE);
				rel.setAnimation(myAnimation_Alpha);
				myAnimation_Alpha.start();
				// rel.setVisibility(View.GONE);
			} else if (v.getId() == spin.getId()) {
				rel.setVisibility(View.GONE);
				bitmap_temp = Handlers.rotate(bitmap, 90);
				bitmap = bitmap_temp;
				imageView.setImageBitmap(bitmap_temp);
			} else if (v.getId() == scrawl.getId()) {
				rel.setVisibility(View.GONE);
				bitmap_temp = Handlers.getRoundedCornerBitmap(bitmap, 10);
				bitmap = bitmap_temp;
				imageView.setImageBitmap(bitmap_temp);
			} else if (v.getId() == ok.getId()) {
				saveToLocal(bitmap);
				Intent intent = new Intent();
				intent.setClass(Handle_photo.this, Upload.class);
				Handle_photo.this.startActivity(intent);
				Handle_photo.this.finish();
			}else if (v.getId() == reset.getId()){
				bitmap = bitmap_first;
				bitmap_temp = bitmap_first;
				imageView.setImageBitmap(bitmap_temp);
			}
		}
	}

	class filterListener implements OnClickListener {
		private int num;
		private int count;

		public filterListener(int num, int count) {
			this.num = num;
			this.count = count;
		}

		@Override
		public void onClick(View arg0) {
			switch (num) {
			case 1:
				if (count == 2) {
					bitmap = Handlers.combinateFrame(bitmap_temp,1);
				} else
					bitmap = Handlers.oldRemeber(bitmap_temp);
				break;
			case 2:
				if (count == 2) {
					bitmap = Handlers.combinateFrame(bitmap_temp,2);
				} else
					bitmap = Handlers.blurImageAmeliorate(bitmap_temp);
				break;
			case 3:
				if (count == 2) {
					bitmap = Handlers.addBigFrame(bitmap_temp,3);
				} else
					bitmap = Handlers.sharpenImageAmeliorate(bitmap_temp);
				break;
			case 4: 
				if (count == 2) {
					bitmap = Handlers.addBigFrame(bitmap_temp,4);
				} else
					bitmap = Handlers.film(bitmap_temp);
				break;
			case 5:
				if (count == 2) {
					bitmap = Handlers.addBigFrame(bitmap_temp,5);
				} else
					bitmap = Handlers.emboss(bitmap_temp);
				break;
			case 6:
				if (count == 2) {
					bitmap = Handlers.addBigFrame(bitmap_temp,6);
				} else
					bitmap = Handlers.overlay(bitmap_temp, Handle_photo.this,
							R.drawable.filter2);
			}
			imageView.setImageBitmap(bitmap);
		}
	}

	/**
	 * 显示进度条
	 */
	private void showProgress() {
		Context context = this;
		mProgress = ProgressDialog.show(context, null, context.getResources()
				.getString(R.string.handling));
		mProgress.show();
	}

	/**
	 * 关闭进度条
	 */
	private void closeProgress() {
		if (null != mProgress) {
			mProgress.dismiss();
			mProgress = null;
		}
	}

	public String saveToLocal(Bitmap bm) {
		String path = "/mnt/sdcard/temp100.jpg";
		try {
			FileOutputStream fos = new FileOutputStream(path);
			// BufferedOutputStream bos = new BufferedOutputStream(
			// fos);
			bm.compress(Bitmap.CompressFormat.JPEG, 100, fos);
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return path;
	}

	public void initial_frame(int num) {
		if (num == 2) {
			hd1.setBackgroundResource(R.drawable.fra1);
			hd2.setBackgroundResource(R.drawable.fra2);
			hd3.setBackgroundResource(R.drawable.fra3);
			hd4.setBackgroundResource(R.drawable.fra4);
			hd5.setBackgroundResource(R.drawable.fra5);
			hd6.setBackgroundResource(R.drawable.fra6);

			hd1.setOnClickListener(new filterListener(1, 2));
			hd2.setOnClickListener(new filterListener(2, 2));
			hd3.setOnClickListener(new filterListener(3, 2));
			hd4.setOnClickListener(new filterListener(4, 2));
			hd5.setOnClickListener(new filterListener(5, 2));
			hd6.setOnClickListener(new filterListener(6, 2));
		} else {
			hd1.setBackgroundResource(R.drawable.hd1);
			hd2.setBackgroundResource(R.drawable.hd2);
			hd3.setBackgroundResource(R.drawable.hd3);
			hd4.setBackgroundResource(R.drawable.hd4);
			hd5.setBackgroundResource(R.drawable.hd5);
			hd6.setBackgroundResource(R.drawable.hd6);

			hd1.setOnClickListener(new filterListener(1, 1));
			hd2.setOnClickListener(new filterListener(2, 1));
			hd3.setOnClickListener(new filterListener(3, 1));
			hd4.setOnClickListener(new filterListener(4, 1));
			hd5.setOnClickListener(new filterListener(5, 1));
			hd6.setOnClickListener(new filterListener(6, 1));
		}
	}

}
