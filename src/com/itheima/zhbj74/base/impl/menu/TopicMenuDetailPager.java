package com.itheima.zhbj74.base.impl.menu;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.itheima.zhbj74.base.BaseMenuDetailPager;

/**
 * 专题菜单详情页
 * @author DCK170503
 *
 */
public class TopicMenuDetailPager extends BaseMenuDetailPager{

	public TopicMenuDetailPager(Activity activity) {
		super(activity);
	}

	@Override
	public View initView() {
		TextView view = new TextView(mActivity);
		view.setText("专题详情页");
		view.setTextColor(Color.RED);
		view.setTextSize(22);
		view.setGravity(Gravity.CENTER);
		return view;
	}
	
}
