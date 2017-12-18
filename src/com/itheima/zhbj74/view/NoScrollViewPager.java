package com.itheima.zhbj74.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
/**
 * ����������ViewPager
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
	
	//�¼�����
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return false;//�������ӿؼ����¼�
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		//��д�˷���������ʱ��ʲô������,�Ӷ�ʵ�ֽ��û���
		return true;
	}

}
