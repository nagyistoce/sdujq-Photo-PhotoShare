package com.android.item.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.android.main.Login_Reg;
import com.android.main.R;

public class About extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		if (Login_Reg.theme_num == 1)
			setContentView(R.layout.about2);
		else
			setContentView(R.layout.about);

		// 返回按钮
		Button back = (Button) findViewById(R.id.about_back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				About.this.finish();
			}
		});

	}
}
