package com.android.item.activity;

import java.io.File;

import org.sdu.taskImp.UserAction;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.main.Home;
import com.android.main.R;
import com.maxtech.common.gps.AddressTask;
import com.maxtech.common.gps.GpsTask.GpsData;
import com.maxtech.common.gps.IAddressTask.MLocation;
import com.renren.api.connect.android.Renren;
import com.tencent.weibo.api.TAPI;
import com.tencent.weibo.api.UserAPI;
import com.tencent.weibo.constants.OAuthConstants;
import com.tencent.weibo.oauthv2.OAuthV2;
import com.tencent.weibo.oauthv2.OAuthV2Client;
import com.tencent.weibo.webview.OAuthV2AuthorizeWebView;
import com.weibo.net.AccessToken;
import com.weibo.net.DialogError;
import com.weibo.net.Weibo;
import com.weibo.net.WeiboDialogListener;
import com.weibo.net.WeiboException;

public class Upload  extends Activity{
	ImageView img;
	EditText title;
	EditText info;
	CheckBox torenren;
	CheckBox tosina;
	CheckBox toqq;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.upload);
		img=(ImageView) findViewById(R.id.up_img);
		title=(EditText) findViewById(R.id.up_title);
		info=(EditText) findViewById(R.id.up_descript);
		torenren=(CheckBox) findViewById(R.id.renren_check);
		tosina=(CheckBox) findViewById(R.id.xinlang_check);
		toqq=(CheckBox) findViewById(R.id.tengxun_check);
		final Bitmap bmp=BitmapFactory.decodeFile("/mnt/sdcard/temp100.jpg");
		img.setImageBitmap(bmp);
		//返回按钮
		Button back=(Button)findViewById(R.id.up_back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent =new Intent();
				intent.setClass(Upload.this, Home.class);
				Upload.this.startActivity(intent); 
				Upload.this.finish();
			}
		});
		
		//确定按钮，判断是否上传成功并给出提示
		Button okbtn = (Button)findViewById(R.id.sns_configure);
		okbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				UserAction uation=new UserAction(Upload.this);
				uation.savePhoto(BitmapFactory.decodeFile("/mnt/sdcard/temp100.jpg"), title.getText().toString(),info.getText().toString());
				Toast.makeText(Upload.this, "上传成功",Toast.LENGTH_SHORT).show();
				Intent intent =new Intent();
				intent.setClass(Upload.this, Home.class);
				Upload.this.startActivity(intent); 
				Upload.this.finish();
			}
		});
		final File f=new File("/mnt/sdcard/temp100.jpg");
		torenren.setOnCheckedChangeListener(new OnCheckedChangeListener() {	
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					toRenren(f);
				}
			}
		});
		toqq.setOnCheckedChangeListener(new OnCheckedChangeListener() {	
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					toTencent(f);
				}
			}
		});
		tosina.setOnCheckedChangeListener(new OnCheckedChangeListener() {	
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					toSina(f);
				}
			}
		});
	}

	AlertDialog dialog;
	private void do_apn() {
		new AsyncTask<Void, Void, String>() {

			@Override
			protected String doInBackground(Void... params) {
				MLocation location = null;
				try {
					location = new AddressTask(Upload.this,
							AddressTask.DO_APN).doApnPost();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(location == null)
					return null;
				return location.toString();
			}

			@Override
			protected void onPreExecute() {
				dialog.show();
				super.onPreExecute();
			}

			@Override
			protected void onPostExecute(String result) {
				if(result == null){
					//TODO gps_tip.setText("基站定位失败了...");					
				}else {
					//TODO gps_tip.setText(result);
				}
				dialog.dismiss();
				super.onPostExecute(result);
			}
			
		}.execute();
	}

	private void do_gps(final GpsData gpsdata) {
		new AsyncTask<Void, Void, String>() {

			@Override
			protected String doInBackground(Void... params) {
				MLocation location = null;
				try {
					Log.i("do_gpspost", "经纬度：" + gpsdata.getLatitude() + "----" + gpsdata.getLongitude());
					location = new AddressTask(Upload.this,
							AddressTask.DO_GPS).doGpsPost(gpsdata.getLatitude(),
									gpsdata.getLongitude());
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(location == null)
					return "GPS信息获取错误";
				return location.toString();
			}

			@Override
			protected void onPreExecute() {
				dialog.show();
				super.onPreExecute();
			}

			@Override
			protected void onPostExecute(String result) {
				//TODO gps_tip.setText(result);
				dialog.dismiss();
				super.onPostExecute(result);
			}
			
		}.execute();
	}

	private void do_wifi() {
		new AsyncTask<Void, Void, String>() {

			@Override
			protected String doInBackground(Void... params) {
				MLocation location = null;
				try {
					location = new AddressTask(Upload.this,
							AddressTask.DO_WIFI).doWifiPost();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(location == null)
					return null;
				return location.toString();
			}

			@Override
			protected void onPreExecute() {
				dialog.show();
				super.onPreExecute();
			}

			@Override
			protected void onPostExecute(String result) {
				if(result != null){
					//TODO	gps_tip.setText(result);
				}else {
					//TODO	gps_tip.setText("WIFI定位失败了...");
				}
				
				dialog.dismiss();
				super.onPostExecute(result);
			}
			
		}.execute();
	}
	
//===============================以下为认证代码==========================================//	
	

	Weibo sina;
	public void toSina(File f){
		sina=Weibo.getInstance();
	
		try {
			sina.setRedirectUrl("http://sdujq.iteye.com/");
			if(!sina.isSessionValid()){
				sina.authorize(Upload.this,new AuthDialogListener());
			}else{
				doUptoSina();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void doUptoSina(){
		try {
			sina.share2weibo(Upload.this, sina.getAccessToken().getToken(), sina.getAccessToken().getSecret(), "来自TimeCapture", "/mnt/sdcard/temp100.jpg");
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}
	public void toRenren(File f){
		Renren renren=new Renren("a8b3c9a32d1349f193eb67d39146f1c0", "dbead8eeefa747e180a00e288ca6fa1e", "197692", Upload.this);
		renren.publishPhoto(Upload.this, f, "来自TimeCapture");
	}
	//TODO oauth
	private OAuthV2 oAuth;
	public void toTencent(File f){
		oAuth=new OAuthV2("http://sdujq.iteye.com");
        oAuth.setClientId("801163559");
        oAuth.setClientSecret("be1dd1410434a9f7d5a2586bab7a6829");
        oAuth.setRedirectUri("http://sdujq.iteye.com");
        OAuthV2Client.getQHttpClient().shutdownConnection();
        Intent intent = new Intent(Upload.this, OAuthV2AuthorizeWebView.class);
        intent.putExtra("oauth", oAuth);
        startActivityForResult(intent,13578);  
	}
	
	
	  protected void onActivityResult(int requestCode, int resultCode, Intent data)   {
	        if (requestCode==13578) {
	            if (resultCode==OAuthV2AuthorizeWebView.RESULT_CODE)    {
	                oAuth=(OAuthV2) data.getExtras().getSerializable("oauth");
	                UserAPI userAPI=new UserAPI(OAuthConstants.OAUTH_VERSION_2_A);
	                try {
	                	TAPI t=new TAPI(OAuthConstants.OAUTH_VERSION_2_A);
	                	t.addPic(oAuth, "xml", "来自TimeCapture", "211.87.227.56", "/mnt/sdcard/temp100.jpg");
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	                userAPI.shutdownConnection();
	                
	            }
	        }else{
	            sina.authorizeCallBack(requestCode, resultCode, data);

	        }
	    }
	  
	  class AuthDialogListener implements WeiboDialogListener {

			@Override
			public void onComplete(Bundle values) {
				String token = values.getString("access_token");
				String expires_in = values.getString("expires_in");
				AccessToken accessToken = new AccessToken(token, "2434619522");
				accessToken.setExpiresTime(expires_in);
				Weibo.getInstance().setAccessToken(accessToken);
				try {
					sina.share2weibo(Upload.this, sina.getAccessToken().getToken(), sina.getAccessToken().getSecret(), "来自TimeCapture", "/mnt/sdcard/temp100.jpg");
				} catch (WeiboException e) {
					e.printStackTrace();
				}

			}

			@Override
			public void onError(DialogError e) {
				Toast.makeText(getApplicationContext(),
						"Auth error : " + e.getMessage(), Toast.LENGTH_LONG).show();
			}

			@Override
			public void onCancel() {
				Toast.makeText(getApplicationContext(), "Auth cancel",
						Toast.LENGTH_LONG).show();
			}

			@Override
			public void onWeiboException(WeiboException e) {
				Toast.makeText(getApplicationContext(),
						"Auth exception : " + e.getMessage(), Toast.LENGTH_LONG)
						.show();
			}

		}

}
