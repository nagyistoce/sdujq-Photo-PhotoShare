package com.android.item;

import org.sdu.task.IReg;
import org.sdu.taskImp.RegAction;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.main.Login_Reg;
import com.android.main.R;

public class Register extends Activity {
	EditText name;
	EditText pwd;
	EditText pwd2;
	EditText mail;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_reg);
		init();
	}

	public void init() {
		name = (EditText) findViewById(R.id.username);
		pwd = (EditText) findViewById(R.id.password);
		pwd2 = (EditText) findViewById(R.id.conf_password);
		mail = (EditText) findViewById(R.id.email);
		Button reg = (Button) findViewById(R.id.configure);
		reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				IReg raction = new RegAction(Register.this);
				if(!pwd.getText().toString().equals(pwd2.getText().toString())){
					Toast.makeText(Register.this, "两次密码输入不一致", 1).show();
					return;
				}
				boolean res=raction.reg(name.getText().toString(), mail.getText()
						.toString(), pwd.getText().toString());
				Toast.makeText(Register.this, raction.getRegInfo(), 1).show();
				if(res){
					Login_Reg.viewPager.setCurrentItem(0);
				}
			}
		});
	}

}
