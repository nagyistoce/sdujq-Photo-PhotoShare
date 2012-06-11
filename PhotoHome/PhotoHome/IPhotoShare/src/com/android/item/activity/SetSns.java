package com.android.item.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.android.main.Login_Reg;
import com.android.main.R;

public class SetSns extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		if (Login_Reg.theme_num == 1)
			setContentView(R.layout.sns2);
		else
			setContentView(R.layout.sns);

		// 确定按钮，判断是否绑定成功并给出提示
		Button okbtn = (Button) findViewById(R.id.sns_configure);
		okbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(SetSns.this, "提示绑定是否成功", Toast.LENGTH_SHORT)
						.show();
			}
		});

		// 返回按钮
		Button back = (Button) findViewById(R.id.sns_back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SetSns.this.finish();
			}
		});

		CheckBox renren = (CheckBox) findViewById(R.id.renren_check);
		CheckBox sina = (CheckBox) findViewById(R.id.xinlang_check);
		CheckBox tengxun = (CheckBox) findViewById(R.id.tengxun_check);
		// 给CheckBox设置事件监听
		renren
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {

						} else {

						}
					}

				});
		sina
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {

						} else {

						}
					}

				});
		tengxun
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {

						} else {

						}
					}

				});
	}
}
