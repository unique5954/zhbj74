package com.itheima.zhbj74.view;

import java.util.Currency;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.text.method.MovementMethod;
import android.util.AttributeSet;
import android.view.MotionEvent;
/**
 * 头条新闻自定义ViewPager
 * @author DCK170503
 *
 */
public class TopNewsViewPager extends ViewPager{

	private int startX;
	private int startY;
	private int endX;
	private int endY;

	public TopNewsViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TopNewsViewPager(Context context) {
		super(context);
	}
	
	/**
	 * 1、上下滑动要拦截
	 * 2、向右滑动并且当前是第一个页面，要拦截
	 * 3、向左滑动并且当前是最后一个页面，要拦截
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		//请求所有父控件及祖宗控件不要拦截事件
		getParent().requestDisallowInterceptTouchEvent(true);
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startX = (int) ev.getX();
			startY = (int) ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			endX = (int) ev.getX();
			endY = (int) ev.getY();
			
			int dx = endX - startX;
			int dy = endY - startY;
			
			if(Math.abs(dy) < Math.abs(dx)){
				int currentItem = getCurrentItem();
				//左右滑动
				if(dx>0){
					//向右
					System.out.println("向右");
					if(currentItem==0){
						//第一个页面,要拦截
						System.out.println("向右,第一个页面,要拦截");
						getParent().requestDisallowInterceptTouchEvent(false);
					}
				}else{
					//向左
					System.out.println("向左");
					int count = getAdapter().getCount();//item总数
					if(currentItem==count-1){
						//最后一个页面,要拦截
						System.out.println("向左,最后一个页面,要拦截");
						getParent().requestDisallowInterceptTouchEvent(false);
					}
				}
			}else{
				//上下滑动
				System.out.println("上下滑动,要拦截");
				getParent().requestDisallowInterceptTouchEvent(false);
			}
			
			break;

		default:
			break;
		}
		return super.dispatchTouchEvent(ev);
	}
	

}
