package com.android.item;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.android.item.activity.Picture;
import com.android.main.R;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.Projection;

public class MapPointItem extends ItemizedOverlay<OverlayItem> implements
		OnClickListener {
	private List<OverlayItem> GeoList = new ArrayList<OverlayItem>();
	private View pv;
	private MapView mv;
	ListView mList;

	public MapPointItem(Drawable defaultMarker, MapView mv, View popView,
			List<OverlayItem> lst) {
		super(defaultMarker);
		this.mv = mv;
		this.pv = popView;
		this.GeoList = lst;
		populate();
	}

	@Override
	protected OverlayItem createItem(int i) {
		return GeoList.get(i);
	}

	@Override
	public int size() {
		return GeoList.size();
	}

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {

		Projection projection = mapView.getProjection();
		for (int index = 0; index < GeoList.size(); index++) {
			OverlayItem overLayItem = GeoList.get(index);
			String title = overLayItem.getTitle();
			Point point = projection.toPixels(overLayItem.getPoint(), null);

			Paint paintCircle = new Paint();
			paintCircle.setColor(Color.RED);
			Bitmap bmp = BitmapFactory.decodeResource(mapView.getContext()
					.getResources(), R.drawable.point);
			canvas.drawBitmap(bmp, point.x - bmp.getWidth() / 2,
					point.y - bmp.getHeight() / 2, paintCircle);
			Paint paintText = new Paint();
			paintText.setColor(Color.BLACK);
			paintText.setTextSize(15);
			canvas.drawText(title, point.x, point.y - 25, paintText);

		}

		super.draw(canvas, mapView, shadow);
	}

	@Override
	protected boolean onTap(int i) {
		setFocus(GeoList.get(i));
		MapView.LayoutParams geoLP = (MapView.LayoutParams) pv
				.getLayoutParams();
		geoLP.point = GeoList.get(i).getPoint();
		mv.updateViewLayout(pv, geoLP);
		pv.setVisibility(View.VISIBLE);
		TextView tv = (TextView) mv.findViewById(R.id.tv);
		tv.setText(GeoList.get(i).getSnippet());
		tv.setOnClickListener(this);
		// SchoolActivity.playMusic(mv.getContext(), R.drawable.click);
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent e, MapView mapView) {

//		if (e.getAction() == MotionEvent.ACTION_UP) {
//			Log.e("-----------", "ok1");
//			int x = (int) e.getX();
//			int y = (int) e.getY();
//			GeoPoint g = mapView.getProjection().fromPixels(x, y);
//			Toast.makeText(mapView.getContext(),
//					g.getLatitudeE6() + "," + g.getLongitudeE6(), 1).show();
//			Log.e("loc", g.getLatitudeE6() + "," + g.getLongitudeE6());
//		}

		return false;
	}

	@Override
	public void onClick(View v) {
		showItem(((TextView) v).getText().toString());
	}

	public void showItem(final String str) {
		Intent intent =new Intent();
		intent.setClass(mv.getContext(), Picture.class);
		Bundle bundle = new Bundle();
		bundle.putInt("select", 0);
		bundle.putInt("id", Integer.parseInt(str.split(":")[0]));
		intent.putExtras(bundle);
		mv.getContext().startActivity(intent);   
	}

	public void showDialog(View mView) {
		// SchoolActivity.playMusic(mv.getContext(), R.drawable.pop);
		final AlertDialog dio = new AlertDialog.Builder(mv.getContext())
				.create();
		dio.setView(mView);
		dio.show();
	}

	public View getView(int layout) {
		View mView = null;
		mView = View.inflate(mv.getContext(), layout, null);
		return mView;
	}

}
