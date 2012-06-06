package com.android.item;

import java.util.List;

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
	//MapPointItem oit;
	View popView;
	List<OverlayItem> olist;
	List<Integer> idlist;

	public MapShowView(Map context) {
		view=View.inflate(context, R.layout.map, null);
		loadMap();
		//loadMarker();
		// mMapView.getOverlays().add(new RoadOverLay(1, 14));
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
		mGeoPoint = new GeoPoint((int) (36.668172 * 1000000),
				(int) (117.138873 * 1000000));
		mMapController.animateTo(mGeoPoint);
		mMapController.setZoom(18);
	}


	
}
