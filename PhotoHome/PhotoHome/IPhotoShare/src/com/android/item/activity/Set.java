package com.android.item.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.android.main.Home;
import com.android.main.Login_Reg;
import com.android.main.R;

public class Set extends Activity {
	private boolean isLocation = false;
	private Button location;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		if (Login_Reg.theme_num == 1)
			setContentView(R.layout.set2);
		else
			setContentView(R.layout.set);

		Button wood = (Button) findViewById(R.id.wood);
		wood.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (Login_Reg.theme_num != 1) {
					Login_Reg.theme_num = 1;
					Intent intent = new Intent();
					intent.setClass(Set.this, Set.class);
					Set.this.startActivity(intent);
					Set.this.finish();
				}
			}
		});

		Button new_skin = (Button) findViewById(R.id.new_skin);
		new_skin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (Login_Reg.theme_num != 0) {
					Login_Reg.theme_num = 0;
					Intent intent = new Intent();
					intent.setClass(Set.this, Set.class);
					Set.this.startActivity(intent);
					Set.this.finish();
				}

			}
		});

		// 返回按钮
		Button back = (Button) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(Set.this, Home.class);
				Set.this.startActivity(intent);
				Set.this.finish();
			}
		});

		location = (Button) findViewById(R.id.location_b);
		location.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (Login_Reg.theme_num == 1) {
					if (!isLocation) {
						location
								.setBackgroundResource(R.drawable.autologin_btn2_2);
						isLocation = true;
					} else {
						location
								.setBackgroundResource(R.drawable.autologin_btn_2);
						isLocation = false;
					}
				} else {
					if (!isLocation) {
						isLocation = true;
					} else {
						isLocation = false;
					}
				}
			}
		});

	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
        	Intent intent = new Intent();
			intent.setClass(Set.this, Home.class);
			Set.this.startActivity(intent);
			Set.this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
