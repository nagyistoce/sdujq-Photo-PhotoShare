package com.android.tool;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * 设置view滑动速率（待定）
 * @author ls
 *
 */
public class FixedSpeedScroller extends Scroller {

	private int mDuration = 3000;

	public FixedSpeedScroller(Context context) {
		super(context);
	}

	public FixedSpeedScroller(Context context, Interpolator interpolator) {
		super(context, interpolator);
	}

	public void setDuration(int duration) {
		this.mDuration = duration;
	}

	// public FixedSpeedScroller(Context context, Interpolator interpolator,
	// boolean flywheel) {
	// super(context);
	// }

	@Override
	public void startScroll(int startX, int startY, int dx, int dy, int duration) {
		// Ignore received duration, use fixed one instead
		super.startScroll(startX, startY, dx, dy, mDuration);
	}

	@Override
	public void startScroll(int startX, int startY, int dx, int dy) {
		// Ignore received duration, use fixed one instead
		super.startScroll(startX, startY, dx, dy, mDuration);
	}
}