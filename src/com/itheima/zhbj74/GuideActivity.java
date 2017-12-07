package com.itheima.zhbj74;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
//��������ҳ��
public class GuideActivity extends Activity {

	private ViewPager mViewPager;
	private LinearLayout llContainer;
	private ImageView ivRedPoint;//С���
	private int mPointDis;//С����ƶ�����
	
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
		ivRedPoint = (ImageView) findViewById(R.id.iv_red_point);
		
		initData();//��ʼ��ImageView����
		mViewPager.setAdapter(new GuideAdapter());
		
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				//ĳ��ҳ�汻ѡ�лص�
			}
			
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				//��ҳ�滬�������еĻص�
				//����С���ľ���
			}
			
			@Override
			public void onPageScrollStateChanged(int state) {
				//ҳ��״̬�����仯�ص�
			}
		});
		
		//��������Բ�����
		//С����ƶ�����=�ڶ���Բ��leftֵ-��һ��Բ��leftֵ
        //mPointDis = llContainer.getChildAt(1).getLeft() - llContainer.getChildAt(0).getLeft();
		//����layout�����������¼�,λ��ȷ����֮�󣬲���ȡԲ�����
		//��ͼ��
		ivRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			
			@Override
			public void onGlobalLayout() {
				//�����ε��ã�ֱ��remove��
				//ivRedPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);//�汾��������
				ivRedPoint.getViewTreeObserver().removeGlobalOnLayoutListener(this);
				//layout ����ִ�н����Ļص�
				mPointDis = llContainer.getChildAt(1).getLeft() - llContainer.getChildAt(0).getLeft();
			}
		});
		
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
			
			//�ӵڶ���СԲ�㿪ʼ������߾�
			//��ʼ�����ֲ�������߰�������
			LinearLayout.LayoutParams params = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			if(i>0){
				//����СԲ����߾�
				params.leftMargin=10;
			}
			
			//���ò��ֲ���
			point.setLayoutParams(params);
			
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
