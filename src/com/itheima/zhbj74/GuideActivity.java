package com.itheima.zhbj74;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
//��������ҳ��
public class GuideActivity extends Activity {

	private ViewPager mViewPager;
	private LinearLayout llContainer;
	
	//����ҳͼƬid����
	private int[] mImageIds = new int[]{R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};
	private ArrayList<ImageView> mImageViewList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//ȥ��������,������setContentView֮ǰ����
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guide);
		
		mViewPager = (ViewPager) findViewById(R.id.vp_guide);
		llContainer = (LinearLayout) findViewById(R.id.ll_container);
		
		initData();//��ʼ��ImageView����
		mViewPager.setAdapter(new GuideAdapter());
	}
	
	//��ʼ������
	private void initData(){
		mImageViewList = new ArrayList<ImageView>();
		for(int i=0;i<mImageIds.length;i++){
			ImageView view = new ImageView(this);
			// ͨ�����ñ����������ÿ����䲼��
			view.setBackgroundResource(mImageIds[i]);
			mImageViewList.add(view);
			
			//��ʼ��СԲ��
			ImageView point = new ImageView(this);
			//����ͼƬ(shape��״)��ͨ�����ñ�����䲼�֣�ֻ����ʵ�ʴ�С��ʾ
			point.setImageResource(R.drawable.shape_point_gray);
			//�����Բ����������Բ��
			llContainer.addView(point);
		}
	}
	
	class GuideAdapter extends PagerAdapter{
		//item����
		@Override
		public int getCount() {
			return mImageViewList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			//�ж�arg1�Ƿ�ΪView����
			return arg0==arg1;
		}
		
		//��ʼ��item����
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView view = mImageViewList.get(position);
			container.addView(view);
			return view;
		}
		
		//����item����
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}
	
}
