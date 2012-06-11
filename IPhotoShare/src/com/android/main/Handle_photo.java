package com.android.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.sdu.bmputil.BitmapTool;
import org.sdu.bmputil.PhotoDrawerTemp;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.item.activity.Upload;

public class Handle_photo extends Activity {

	private ImageView imageView;
//	private TextView textView;
	Bitmap bitmap;
	LinearLayout sub_filter;
	Button filters;
	Button zoom;
	Button spin;
	Button cut;
	Button scrawl;
	Button ok;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fliter);

		filters = (Button) findViewById(R.id.filters);
		zoom = (Button) findViewById(R.id.zoom);
		spin = (Button) findViewById(R.id.spin);
		cut = (Button) findViewById(R.id.cut);
		scrawl = (Button) findViewById(R.id.scrawl);
		//确定按钮
		ok = (Button) findViewById(R.id.ok);
		ok.setOnClickListener(new buttonListener());
		
		sub_filter = (LinearLayout) findViewById(R.id.sub_filter);

		imageView = (ImageView) findViewById(R.id.imgfilter);
//		textView = (TextView) findViewById(R.id.runtime);

		ContentResolver cr = getContentResolver();
		try {
			InputStream in = cr.openInputStream(Uri.fromFile(new File(
					"/mnt/sdcard/temp100.jpg")));
			bitmap = BitmapFactory.decodeStream(in);
			imageView.setImageBitmap(bitmap);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		filters.setOnClickListener(new buttonListener());
		zoom.setOnClickListener(new buttonListener());
		spin.setOnClickListener(new buttonListener());
		cut.setOnClickListener(new buttonListener());
		scrawl.setOnClickListener(new buttonListener());
//		LoadImageFilter();
	}


	class buttonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			if (v.getId() == filters.getId()) {
				sub_filter.setVisibility(View.VISIBLE);
			} else if (v.getId() == zoom.getId()) {
				sub_filter.setVisibility(View.GONE);
			} else if (v.getId() == spin.getId()) {
				sub_filter.setVisibility(View.GONE);
			} else if (v.getId() == cut.getId()) {
				sub_filter.setVisibility(View.GONE);

			} else if (v.getId() == scrawl.getId()) {
				sub_filter.setVisibility(View.GONE);
				PhotoDrawerTemp.shareBMP=bitmap;
				Intent intent =new Intent();
				intent.setClass(Handle_photo.this, PhotoDrawerTemp.class);
				startActivityForResult(intent, 123);
			}
			else if (v.getId() == ok.getId()) {
				Intent intent =new Intent();
				intent.setClass(Handle_photo.this, Upload.class);
				
				/*Bundle bundle = new Bundle();
				bundle.putByteArray("data", BitmapTool.Bitmap2Bytes(bitmap));
				intent.putExtras(bundle);*/
				Handle_photo.this.startActivity(intent); 
				Handle_photo.this.finish();
				
			}
		}
	}

	public void onActivityResult(int requestCode, int resultCode, Intent it){
		
			imageView.setImageBitmap(PhotoDrawerTemp.shareBMP);
		
	}
}
