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
 * �����ǩҳ�Ļ���
 * @author DCK170503
 *
 */
public class BasePager {
	public Activity mActivity;
	
	public TextView tvTitle;
	public ImageButton btnMenu;
	public FrameLayout flContent;//�յ�֡���ֶ���Ҫ��̬��Ӳ���

	public View mRootView;//��ǰҳ��Ĳ��ֶ���
	
	public BasePager(Activity activity){
		mActivity = activity;
		mRootView = initView();
	}
	
	//��ʼ������
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
	 * ���ز����
	 */
	protected void toggle() {
		MainActivity mainUI = (MainActivity) mActivity;
		SlidingMenu slidingMenu = mainUI.getSlidingMenu();
		slidingMenu.toggle();
		
	}
	
	//��ʼ������
	public void initData(){
		
	}
}
