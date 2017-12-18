package com.itheima.zhbj74.base.impl.menu;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.itheima.zhbj74.base.BaseMenuDetailPager;
import com.itheima.zhbj74.domain.NewsMenu.NewsTabData;

/**
 * 页签页面对象
 * @author DCK170503
 *
 */
public class TabDetailPager extends BaseMenuDetailPager{

	private NewsTabData mTabData;//单个页签
	private TextView view;
	
	public TabDetailPager(Activity activity, NewsTabData newsTabData) {
		super(activity);
		mTabData = newsTabData;
	}

	@Override
	public View initView() {
		view = new TextView(mActivity);
		//view.setText(mTabData.title);
		view.setTextColor(Color.RED);
		view.setTextSize(22);
		view.setGravity(Gravity.CENTER);
		return view;
	}
	
	@Override
	public void initData() {
		view.setText(mTabData.title);
	}

}
