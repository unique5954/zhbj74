package com.itheima.zhbj74.base.impl.menu;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.itheima.zhbj74.base.BaseMenuDetailPager;

/**
 * ��ͼ�˵�����ҳ
 * @author DCK170503
 *
 */
public class PhotosMenuDetailPager extends BaseMenuDetailPager{

	public PhotosMenuDetailPager(Activity activity) {
		super(activity);
	}

	@Override
	public View initView() {
		TextView view = new TextView(mActivity);
		view.setText("��ͼ�˵�����ҳ");
		view.setTextColor(Color.RED);
		view.setTextSize(22);
		view.setGravity(Gravity.CENTER);
		return view;
	}
	
}
