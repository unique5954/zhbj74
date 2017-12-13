package com.itheima.zhbj74;

import com.itheima.zhbj74.fragment.ContentFragment;
import com.itheima.zhbj74.fragment.LeftMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.view.WindowManager;
//主页面
public class MainActivity extends SlidingFragmentActivity {
	private static final String TAG_LEFT_MENU = "TAG_LEFT_MENU";
	private static final String TAG_CONTENT = "TAG_CONTENT";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//去掉标题栏,必须在setContentView之前调用
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		//设置侧边栏
        setBehindContentView(R.layout.left_menu);
        SlidingMenu slidingMenu = getSlidingMenu();
        //设置全屏触摸显示侧边栏
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置屏幕预留宽度
        WindowManager wm = this.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        slidingMenu.setBehindOffset(width*3/5);
        
        initFragment();
	}
	
	//初始化fragment(将侧边栏和主页面用XxxFragment替换)
	private void initFragment(){
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		//用LeftMenuFragment替换SlidingMenu侧边栏的fl_left_menu
		transaction.replace(R.id.fl_left_menu, new LeftMenuFragment(),TAG_LEFT_MENU);
		transaction.replace(R.id.fl_main, new ContentFragment(),TAG_CONTENT);
		transaction.commit();
	}
	
	//获取侧边栏fragment
	public LeftMenuFragment getLeftMenuFragment(){
		FragmentManager fm = getSupportFragmentManager();
		LeftMenuFragment fragment = (LeftMenuFragment) fm.findFragmentByTag(TAG_LEFT_MENU);
		return fragment;
	}
	
}

