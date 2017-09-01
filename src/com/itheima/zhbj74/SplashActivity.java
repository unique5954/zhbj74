package com.itheima.zhbj74;

import com.itheima.zhbj74.utils.PrefUtils;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;
//�����������ҳ��
public class SplashActivity extends Activity {

	private RelativeLayout rlRoot;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        rlRoot = (RelativeLayout) findViewById(R.id.rl_root);
        
        //��ת����
        RotateAnimation animRotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animRotate.setDuration(1000);
        animRotate.setFillAfter(true);
        
        //���Ŷ���
        ScaleAnimation animScale = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animScale.setDuration(1000);
        animScale.setFillAfter(true);
        
        //���䶯��
        AlphaAnimation animAlpha = new AlphaAnimation(0, 1);
        animAlpha.setDuration(1000);
        animAlpha.setFillAfter(true);
        
        //��������
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(animRotate);
        set.addAnimation(animScale);
        set.addAnimation(animAlpha);
        
        //��������
        rlRoot.startAnimation(set);
        
        //����������
        set.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				//������������תҳ��
				//����ǵ�һ�ν��룬����������
				//������ת��ҳ
				boolean isFirstEnter = PrefUtils.getBoolean(SplashActivity.this,"is_first_enter", true);//��һ�ν��룬Ĭ��ֵtrue
				Intent intent;
				if(isFirstEnter){
					//��������
					intent = new Intent(getApplicationContext(), GuideActivity.class);
				}else{
					//��ҳ
					intent = new Intent(getApplicationContext(), MainActivity.class);
				}
				startActivity(intent);
				finish();//������ǰҳ
			}
		});
        
    }

    

    
}
