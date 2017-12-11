package com.itheima.zhbj74.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.itheima.zhbj74.base.BasePager;
/**
 * ��ҳ
 * @author DCK170503
 *
 */
public class HomePager extends BasePager{

	public HomePager(Activity activity) {
		super(activity);
	}
	
	@Override
	public void initData() {
		//Ҫ��֡������䲼�ֶ���
		TextView view = new TextView(mActivity);
		view.setText("��ҳ");
		view.setTextColor(Color.RED);
		view.setTextSize(22);
		view.setGravity(Gravity.CENTER);
		
		flContent.addView(view);
		
		//�޸�ҳ�����
		tvTitle.setText("��ҳ");
		
		//���ز˵���ť
		btnMenu.setVisibility(View.GONE);
	}

}
