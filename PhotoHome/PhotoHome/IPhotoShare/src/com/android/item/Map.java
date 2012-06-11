package com.android.item;


import android.app.Activity;
import android.os.Bundle;

import com.android.main.R;
import com.google.android.maps.MapActivity;

public class Map extends MapActivity{
//key F1:29:5C:83:E8:3C:98:49:39:13:52:1D:C4:36:40:D7
//	F1:29:5C:83:E8:3C:98:49:39:13:52:1D:C4:36:40:D7
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_collect);
		MapShowView mapview = new MapShowView(this);
		setContentView(mapview.view);
	}

	protected boolean isRouteDisplayed() {
		return false;
	}

}
