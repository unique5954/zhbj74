package com.itheima.zhbj74;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
//��ҳ��
public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//ȥ��������,������setContentView֮ǰ����
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
	}
}
