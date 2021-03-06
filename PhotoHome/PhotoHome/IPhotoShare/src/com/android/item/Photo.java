package com.android.item;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.android.main.Home;
import com.android.main.Login_Reg;
import com.android.main.R;

public class Photo extends Activity {
	private Button take_photo;
	private Button local_photo;
	public static boolean isFromCam;
	public final static int SELECT_PHOTO_REQUEST_CODE = 1;
	public final static int TAKE_PHOTO_REQUEST_CODE = 2;
	public final static int CUT_PHOTO_REQUEST_CODE = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (Login_Reg.theme_num == 1)
			setContentView(R.layout.item_photo2);
		else
			setContentView(R.layout.item_photo);

		take_photo = (Button) findViewById(R.id.take_photo);
		local_photo = (Button) findViewById(R.id.local_photo);
		take_photo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				isFromCam = true;
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				File out = new File("/mnt/sdcard/temp99.jpg");

				Uri uri = Uri.fromFile(out);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
				((Activity) Home.home_con).startActivityForResult(intent,
						Photo.TAKE_PHOTO_REQUEST_CODE);

			}
		});
		local_photo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				isFromCam = false;
				Intent intent = new Intent();
				intent.setType("image/*");
				intent.setAction(Intent.ACTION_GET_CONTENT);
				// intent.putExtra("crop", "true");
				// intent.putExtra("aspectX", 1);
				// intent.putExtra("aspectY", 1);
				// intent.putExtra("outputX", 500);
				// intent.putExtra("outputY", 500);
				intent.putExtra("output", Uri.fromFile(new File(
						"/mnt/sdcard/temp99.jpg")));
				intent.putExtra("outputFormat", "JPEG");
				((Activity) Home.home_con).startActivityForResult(intent,
						Photo.SELECT_PHOTO_REQUEST_CODE);
			}
		});
	}
}
