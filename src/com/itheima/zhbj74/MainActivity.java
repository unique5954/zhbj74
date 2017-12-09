package com.itheima.zhbj74;

import com.itheima.zhbj74.fragment.ContentFragment;
import com.itheima.zhbj74.fragment.LeftMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
//��ҳ��
public class MainActivity extends SlidingFragmentActivity {
	private static final String TAG_LEFT_MENU = "TAG_LEFT_MENU";
	private static final String TAG_CONTENT = "TAG_CONTENT";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//ȥ��������,������setContentView֮ǰ����
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		//���ò����
        setBehindContentView(R.layout.left_menu);
        SlidingMenu slidingMenu = getSlidingMenu();
        //����ȫ��������ʾ�����
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //������ĻԤ��200px���
        slidingMenu.setBehindOffset(200);
        
        initFragment();
	}
	
	//��ʼ��fragment(�����������ҳ����XxxFragment�滻)
	private void initFragment(){
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		//��LeftMenuFragment�滻SlidingMenu�������fl_left_menu
		transaction.replace(R.id.fl_left_menu, new LeftMenuFragment(),TAG_LEFT_MENU);
		transaction.replace(R.id.fl_main, new ContentFragment(),TAG_CONTENT);
		transaction.commit();
	}
}

