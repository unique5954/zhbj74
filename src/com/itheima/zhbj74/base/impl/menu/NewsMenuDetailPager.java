package com.itheima.zhbj74.base.impl.menu;

import java.util.ArrayList;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.itheima.zhbj74.MainActivity;
import com.itheima.zhbj74.R;
import com.itheima.zhbj74.base.BaseMenuDetailPager;
import com.itheima.zhbj74.domain.NewsMenu.NewsTabData;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.viewpagerindicator.TabPageIndicator;

/**
 * ���Ų˵�����ҳ
 * @author DCK170503
 *
 */
public class NewsMenuDetailPager extends BaseMenuDetailPager implements OnPageChangeListener{

	private ViewPager mViewPager;
	private TabPageIndicator mIndicator;//����TabPageIndicator  Library
	private ImageButton mBtnNext;
	private ArrayList<NewsTabData> mTabData;//ҳǩ����
	private ArrayList<TabDetailPager> mPagers;//ҳǩҳ��
	
	public NewsMenuDetailPager(Activity activity, ArrayList<NewsTabData> children) {
		super(activity);
		mTabData = children;
	}

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.pager_news_menu_detail, null);
		mViewPager = (ViewPager) view.findViewById(R.id.vp_news_menu_detail);
		mIndicator = (TabPageIndicator) view.findViewById(R.id.indicator);
		mBtnNext = (ImageButton) view.findViewById(R.id.btn_next);
		return view;
	}
	
	@Override
	public void initData() {
		//��ʼ��ҳǩ
		mPagers = new ArrayList<TabDetailPager>();
		for(int i = 0; i<mTabData.size(); i++){
			TabDetailPager pager = new TabDetailPager(mActivity,mTabData.get(i));
			mPagers.add(pager);
		}
		mViewPager.setAdapter(new NewsMenuDetailAdapter());
		//��mViewPager��(TabPageIndicator)ָʾ������һ��
		//������mViewPager���������ݺ��ٰ�
		mIndicator.setViewPager(mViewPager);
		//����ҳ�滬������
		//�����mIndicator����ҳ�滬������,����mViewPager
		//mViewPager.setOnPageChangeListener(this);
		mIndicator.setOnPageChangeListener(this);
		//�¸�ҳ��
		mBtnNext.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int currentItem = mViewPager.getCurrentItem();
				currentItem++;
				mViewPager.setCurrentItem(currentItem);
			}
		});
	}
	
	class NewsMenuDetailAdapter extends PagerAdapter{
		//(TabPageIndicator)ָʾ���ı���
		@Override
		public CharSequence getPageTitle(int position) {
			NewsTabData data = mTabData.get(position);
			return data.title;
		}

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
			TabDetailPager pager = mPagers.get(position);
			View view = pager.mRootView;
			container.addView(view);
			pager.initData();
			return view;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}

	
	@Override
	public void onPageSelected(int position) {
		if(position==0){
			//���������
			setSlidingMenuEnable(true);
		}else{
			//���ò����
			setSlidingMenuEnable(false);
		}
	}
	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
	}
	@Override
	public void onPageScrollStateChanged(int state) {
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

	
	
}
