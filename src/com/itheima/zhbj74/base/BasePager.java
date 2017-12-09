package com.itheima.zhbj74.base;

import com.itheima.zhbj74.R;

import android.app.Activity;
import android.view.View;
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
		return view;
	} 
	//��ʼ������
	public void initData(){
		
	}
}
