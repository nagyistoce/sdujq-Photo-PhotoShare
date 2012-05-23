package com.android.item;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.android.main.Home;
import com.android.main.Login_Reg;
import com.android.main.R;

public class Login extends Activity{
	private Button configure;
	private Button reg;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_login);
		
		configure = (Button)findViewById(R.id.b_configure);
		reg = (Button)findViewById(R.id.reg);
		configure.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent =new Intent();
				intent.setClass(Login.this, Home.class);
				Login.this.startActivity(intent);
				Login.this.finish();
			}
		});
		reg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Login_Reg.viewPager.setCurrentItem(1);
			}
		});
	}

}
