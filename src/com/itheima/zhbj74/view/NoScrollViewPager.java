package com.itheima.zhbj74.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
/**
 * 不允许滑动的ViewPager
 * @author DCK170503
 *
 */
public class NoScrollViewPager extends ViewPager{

	public NoScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NoScrollViewPager(Context context) {
		super(context);
	}
	
	//事件拦截
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return false;//不拦截子控件的事件
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		//重写此方法，触摸时，什么都不作,从而实现禁用滑动
		return true;
	}

}
