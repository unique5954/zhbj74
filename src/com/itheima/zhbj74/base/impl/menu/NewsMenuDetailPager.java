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
 * 新闻菜单详情页
 * @author DCK170503
 *
 */
public class NewsMenuDetailPager extends BaseMenuDetailPager implements OnPageChangeListener{

	private ViewPager mViewPager;
	private TabPageIndicator mIndicator;//引入TabPageIndicator  Library
	private ImageButton mBtnNext;
	private ArrayList<NewsTabData> mTabData;//页签数据
	private ArrayList<TabDetailPager> mPagers;//页签页面
	
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
		//初始化页签
		mPagers = new ArrayList<TabDetailPager>();
		for(int i = 0; i<mTabData.size(); i++){
			TabDetailPager pager = new TabDetailPager(mActivity,mTabData.get(i));
			mPagers.add(pager);
		}
		mViewPager.setAdapter(new NewsMenuDetailAdapter());
		//将mViewPager和(TabPageIndicator)指示器绑定在一起
		//必须在mViewPager设置完数据后再绑定
		mIndicator.setViewPager(mViewPager);
		//设置页面滑动监听
		//必须给mIndicator设置页面滑动监听,不是mViewPager
		//mViewPager.setOnPageChangeListener(this);
		mIndicator.setOnPageChangeListener(this);
		//下个页面
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
		//(TabPageIndicator)指示器的标题
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
			//开启侧边栏
			setSlidingMenuEnable(true);
		}else{
			//禁用侧边栏
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
	 * 开启或禁用侧边栏
	 * @param enable
	 */
	protected void setSlidingMenuEnable(boolean enable) {
		//获取侧边栏对象
		MainActivity  mainUI = (MainActivity) mActivity; 
		SlidingMenu slidingMenu = mainUI.getSlidingMenu();
		if(enable){
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		}else{
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		}
	}

	
	
}
