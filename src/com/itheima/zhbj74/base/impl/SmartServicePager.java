package com.itheima.zhbj74.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.itheima.zhbj74.base.BasePager;
/**
 * �ǻ۷���
 * @author DCK170503
 *
 */
public class SmartServicePager extends BasePager{

	public SmartServicePager(Activity activity) {
		super(activity);
	}
	
	@Override
	public void initData() {
		//Ҫ��֡������䲼�ֶ���
		TextView view = new TextView(mActivity);
		view.setText("�ǻ۷���");
		view.setTextColor(Color.RED);
		view.setTextSize(22);
		view.setGravity(Gravity.CENTER);
		
		flContent.addView(view);
		
		//�޸�ҳ�����
		tvTitle.setText("�ǻ۷���");
	}

}
