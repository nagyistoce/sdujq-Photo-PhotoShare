package com.android.item.activity;

import java.sql.Timestamp;
import java.util.List;

import org.sdu.bmputil.BitmapTool;
import org.sdu.db.dao.UserDao;
import org.sdu.db.pojo.Friend;
import org.sdu.db.pojo.Photo;
import org.sdu.db.pojo.User;
import org.sdu.taskImp.UserAction;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.item.adapter.CommentAdapter;
import com.android.main.Login_Reg;
import com.android.main.R;

public class Picture extends Activity {

	Photo p;
	UserAction uaciton;
	ListView list;
	private int count_att = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		if (Login_Reg.theme_num == 1)
			setContentView(R.layout.picture2);
		else
			setContentView(R.layout.picture);
		list = (ListView) findViewById(R.id.comment_list);
		TextView username = (TextView) findViewById(R.id.pic_username);
		TextView title = (TextView) findViewById(R.id.pic_titles);
		TextView time = (TextView) findViewById(R.id.pic_time);
		final TextView bt_add = (TextView) findViewById(R.id.pic_add);
		final TextView bt_collect = (TextView) findViewById(R.id.pic_collect);
		final TextView bt_comment = (TextView) findViewById(R.id.pic_comment);
		ImageView user = (ImageView) findViewById(R.id.pic_user);
		ImageView pic = (ImageView) findViewById(R.id.pic);

		// 获取当前选择的是哪张照片
		Bundle bundle = this.getIntent().getExtras();
		// int selected = bundle.getInt("select");
		int id = bundle.getInt("id");
		uaciton = new UserAction(this);
		p = uaciton.getPhotoById(id);
		int uid = p.getUserId();
		UserDao udao = new UserDao(this);
		final User u = udao.get(uid);
		// TODO setImg
		user.setBackgroundResource(TestData.user_head_img[0]);
		pic.setImageBitmap(BitmapTool.Bytes2Bimap(p.getData()));
		username.setText("昵称：" + u.getName());
		title.setText("标题：" + p.getTitle());
		Log.e("qq", "title is " + p.getTitle());
		time.setText("时间：" + new Timestamp(p.getTime()).toLocaleString());
		// 设置焦点，保证打开后显示顶端的内容
		username.setFocusable(true);
		username.setFocusableInTouchMode(true);
		username.requestFocus();

		// 返回按钮
		Button back = (Button) findViewById(R.id.pic_back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Picture.this.finish();
			}
		});
		List<Friend> frend = uaciton.getFriendShipIdList(uaciton
				.getCurrentUser());
		for (Friend f : frend) {
			if (f.getUser_2() == u.getId()) {
				bt_add.setEnabled(false);
				bt_add.setBackgroundResource(R.drawable.del_att);
				break;
			}
		}
		// 添加/取消关注
		bt_add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (count_att % 2 == 0) {
					uaciton.makeFriendWith(u.getId());
					bt_add.setEnabled(false);
					Toast.makeText(Picture.this, "已加关注", 0).show();
					bt_add.setBackgroundResource(R.drawable.del_att);
				} else {
					uaciton.makeFriendWith(u.getId());
					bt_add.setEnabled(false);
					Toast.makeText(Picture.this, "已取消关注", 0).show();
					bt_add.setBackgroundResource(R.drawable.add_att);
				}
			}
		});
		bt_collect.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				uaciton.collect(p.getId());
				Toast.makeText(Picture.this, "收藏成功", 0).show();
			}
		});
		bt_comment.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				View view = View.inflate(Picture.this, R.layout.simple_input,
						null);
				final EditText data = (EditText) view.findViewById(R.id.data);
				Button bt = (Button) view.findViewById(R.id.ok);
				final AlertDialog dio = new AlertDialog.Builder(Picture.this)
						.create();
				dio.setView(view);
				bt.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						uaciton.makeArgument(p.getId(), data.getText()
								.toString());
						list.setAdapter(new CommentAdapter(Picture.this, p
								.getId()));// 调用ImageAdapter.java
						dio.dismiss();
					}
				});
				dio.show();
			}
		});
		list.setAdapter(new CommentAdapter(this, p.getId()));// 调用ImageAdapter.java

	}
}
