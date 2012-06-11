package com.android.item;

import java.util.ArrayList;
import java.util.List;

import org.sdu.db.pojo.Photo;
import org.sdu.taskImp.UserAction;

import android.graphics.drawable.Drawable;
import android.view.View;

import com.android.main.R;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class MapShowView {
	private MapView mMapView;
	private MapController mMapController;
	private GeoPoint mGeoPoint;
	public static boolean findWay = false;
	View view;
	MapPointItem oit;
	View popView;
	List<OverlayItem> olist;
	List<Integer> idlist;
	Map activity;

	public MapShowView(Map context) {
		this.activity = context;
		view = View.inflate(context, R.layout.map, null);
		loadMap();
		loadMarker();
	}

	public void loadMap() {
		mMapView = (MapView) view.findViewById(R.id.map);
		mMapView.setTraffic(false);
		mMapView.setStreetView(false);
		mMapView.setSatellite(true);
		mMapController = (MapController) mMapView.getController();
		mMapView.setEnabled(true);
		mMapView.setClickable(true);
		mMapView.setBuiltInZoomControls(true);
		mGeoPoint = new GeoPoint((int) (36.666759 * 1000000),
				(int) (117.132937 * 1000000));
		mMapController.animateTo(mGeoPoint);
		mMapController.setZoom(18);
	}

	public void loadMarker() {
		popView = activity.getLayoutInflater().inflate(R.layout.popview, null);
		mMapView.addView(popView, new MapView.LayoutParams(
				MapView.LayoutParams.WRAP_CONTENT,
				MapView.LayoutParams.WRAP_CONTENT, null,
				MapView.LayoutParams.BOTTOM_CENTER));
		popView.setVisibility(View.GONE);
		Drawable marker = activity.getResources().getDrawable(R.drawable.point);
		marker.setBounds(0, 0, 2, 2);
		loadPoint();
		oit = new MapPointItem(marker, mMapView, popView, olist);
		mMapView.getOverlays().add(oit);

	}

	private void loadPoint() {
		List<OverlayItem> lst = new ArrayList<OverlayItem>();
		UserAction u = new UserAction(activity);
		List<Photo> plst = u.getPhotoByLocation(0L, 0L);
		for (Photo p : plst) {
			OverlayItem o = new OverlayItem(
					new GeoPoint(
							Integer.parseInt(p.getLocationX()), 
							Integer.parseInt(p.getLocationY())), 
					p.getTitle(),
					p.getId()+":"+p.getTitle());
			lst.add(o);
		}
		this.olist = lst;
	}

}
