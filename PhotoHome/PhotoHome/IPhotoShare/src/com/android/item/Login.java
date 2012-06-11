package com.android.item;

import org.sdu.db.DBHelper;
import org.sdu.task.ILogin;
import org.sdu.taskImp.LoginAction;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.main.Home;
import com.android.main.Login_Reg;
import com.android.main.R;

public class Login extends Activity {
	private Button configure;
	private Button reg;
	private EditText uname;
	private EditText upwd;
	public SQLiteDatabase db;
	private Button autologin;
	private boolean isAuto = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (Login_Reg.theme_num == 1) {
			setContentView(R.layout.item_login2);
			autologin = (Button) findViewById(R.id.autologinbtn);
			autologin.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (!isAuto) {
						autologin
								.setBackgroundResource(R.drawable.autologin_btn2_2);
						isAuto = true;
					} else {
						autologin
								.setBackgroundResource(R.drawable.autologin_btn_2);
						isAuto= false;
					}
				}
			});
		} else
			setContentView(R.layout.item_login);
		DBHelper dbh = new DBHelper(this);
		db = dbh.getWritableDatabase();
		configure = (Button) findViewById(R.id.b_configure);
		reg = (Button) findViewById(R.id.reg);
		uname = (EditText) findViewById(R.id.username);
		upwd = (EditText) findViewById(R.id.password);
		configure.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ILogin loginaction = new LoginAction(Login.this);
				boolean success = loginaction.login(uname.getText().toString(),
						upwd.getText().toString());
				if (success) {
					Intent intent = new Intent();
					intent.setClass(Login.this, Home.class);
					Login.this.startActivity(intent);
					Login.this.finish();
				} else {
					Toast.makeText(Login.this, loginaction.getLogInfo(), 1)
							.show();
				}
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
