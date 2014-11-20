package com.noworkday.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CarouselViewPage extends ViewPager{
	public CarouselViewPage(Context context) {
		super(context);
	}
	public CarouselViewPage(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		return true;
	}
	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		getParent().requestDisallowInterceptTouchEvent(true);
		return super.onTouchEvent(arg0);
	}
}
