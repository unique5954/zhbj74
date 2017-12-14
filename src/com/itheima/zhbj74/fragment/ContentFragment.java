package com.itheima.zhbj74.fragment;

import java.util.ArrayList;

import com.itheima.zhbj74.MainActivity;
import com.itheima.zhbj74.R;
import com.itheima.zhbj74.base.BasePager;
import com.itheima.zhbj74.base.impl.GovAffairsPager;
import com.itheima.zhbj74.base.impl.HomePager;
import com.itheima.zhbj74.base.impl.NewsCenterPager;
import com.itheima.zhbj74.base.impl.SettingPager;
import com.itheima.zhbj74.base.impl.SmartServicePager;
import com.itheima.zhbj74.view.NoScrollViewPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class ContentFragment extends BaseFragment{

	private NoScrollViewPager mViewPager;
	private RadioGroup rgGroup;
	
	private ArrayList<BasePager> mPagers;//�����ǩҳ�ļ���

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.fragment_content, null);
		mViewPager = (NoScrollViewPager) view.findViewById(R.id.vp_content);
		rgGroup = (RadioGroup) view.findViewById(R.id.rg_group);
		return view;
	}

	@Override
	public void initData() {
		mPagers = new ArrayList<BasePager>();
		//��������ǩҳ
		mPagers.add(new HomePager(mActivity));
		mPagers.add(new NewsCenterPager(mActivity));
		mPagers.add(new SmartServicePager(mActivity));
		mPagers.add(new GovAffairsPager(mActivity));
		mPagers.add(new SettingPager(mActivity));
		
		mViewPager.setAdapter(new ContentAdapter());
		
		//������ǩ�л�����
		rgGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rb_home:
					//��ҳ
					mViewPager.setCurrentItem(0,false);//false:��Ҫ��������
					break;
				case R.id.rb_news:
					//��������
					mViewPager.setCurrentItem(1,false);
					break;
				case R.id.rb_smart:
					//�ǻ۷���
					mViewPager.setCurrentItem(2,false);
					break;
				case R.id.rb_gov:
					//����
					mViewPager.setCurrentItem(3,false);
					break;
				case R.id.rb_setting:
					//����
					mViewPager.setCurrentItem(4,false);
					break;

				default:
					break;
				}
			}
		});
		
		//����ҳ���л�
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				BasePager pager = mPagers.get(position);
				pager.initData();//��ʼ��ҳ������
				
				if(position ==0 || position == mPagers.size()-1){
					//��ҳ������ҳ���ò����
					setSlidingMenuEnable(false);
				}else{
					//����ҳ���������
					setSlidingMenuEnable(true);
				}
			}
			

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
			}
			@Override
			public void onPageScrollStateChanged(int state) {
			}
		});
		//��һ�ν��룬������ҳ
		mPagers.get(0).initData();
		//���ò����
		setSlidingMenuEnable(false);
	}

	/**
	 * ��������ò����
	 * @param enable
	 */
	protected void setSlidingMenuEnable(boolean enable) {
		//��ȡ���������
		MainActivity  mainUI = (MainActivity) mActivity; 
		SlidingMenu slidingMenu = mainUI.getSlidingMenu();
		if(enable){
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		}else{
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		}
	}

	class ContentAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return mPagers.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view==object;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			BasePager pager = mPagers.get(position);
			View view = pager.mRootView;//��ȡ��ǰҳ�����Ĳ���
//			//��ʼ������,viewpager��Ĭ�ϼ�����һ������,Ϊ�˽�ʡ���ܺ����������ڴ˴δ�����
//			pager.initData();
			container.addView(view);
			return view;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
		
	}
	
	//��ȡ��������ҳ��
	public NewsCenterPager getNewsCenterPager(){
		NewsCenterPager pager = (NewsCenterPager) mPagers.get(1);
		return pager;
	}
	
}
