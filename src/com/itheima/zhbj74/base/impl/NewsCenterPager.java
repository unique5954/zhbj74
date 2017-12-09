package com.itheima.zhbj74.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.itheima.zhbj74.base.BasePager;
/**
 * 新闻中心
 * @author DCK170503
 *
 */
public class NewsCenterPager extends BasePager{

	public NewsCenterPager(Activity activity) {
		super(activity);
	}
	
	@Override
	public void initData() {
		//要给帧布局填充布局对象
		TextView view = new TextView(mActivity);
		view.setText("新闻中心");
		view.setTextColor(Color.RED);
		view.setTextSize(22);
		view.setGravity(Gravity.CENTER);
		
		flContent.addView(view);
		
		//修改页面标题
		tvTitle.setText("新闻中心");
	}

}
