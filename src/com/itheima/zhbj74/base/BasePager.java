package com.itheima.zhbj74.base;

import com.itheima.zhbj74.MainActivity;
import com.itheima.zhbj74.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * 五个标签页的基类
 * @author DCK170503
 *
 */
public class BasePager {
	public Activity mActivity;
	
	public TextView tvTitle;
	public ImageButton btnMenu;
	public FrameLayout flContent;//空的帧布局对象，要动态添加布局

	public View mRootView;//当前页面的布局对象
	
	public BasePager(Activity activity){
		mActivity = activity;
		mRootView = initView();
	}
	
	//初始化布局
	public View initView(){
		View view = View.inflate(mActivity, R.layout.base_pager, null);
		tvTitle = (TextView) view.findViewById(R.id.tv_title);
		btnMenu = (ImageButton) view.findViewById(R.id.btn_menu);
		flContent = (FrameLayout) view.findViewById(R.id.fl_content);
		btnMenu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				toggle();
			}
		});
		
		return view;
	} 
	
	/**
	 * 开关侧边栏
	 */
	protected void toggle() {
		MainActivity mainUI = (MainActivity) mActivity;
		SlidingMenu slidingMenu = mainUI.getSlidingMenu();
		slidingMenu.toggle();
		
	}
	
	//初始化数据
	public void initData(){
		
	}
}
