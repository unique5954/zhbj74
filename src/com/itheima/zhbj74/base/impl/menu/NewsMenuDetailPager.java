package com.itheima.zhbj74.base.impl.menu;

import java.util.ArrayList;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.itheima.zhbj74.R;
import com.itheima.zhbj74.base.BaseMenuDetailPager;
import com.itheima.zhbj74.domain.NewsMenu.NewsTabData;
import com.viewpagerindicator.TabPageIndicator;

/**
 * 新闻菜单详情页
 * @author DCK170503
 *
 */
public class NewsMenuDetailPager extends BaseMenuDetailPager{

	private ViewPager mViewPager;
	private TabPageIndicator mIndicator;
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
		//将mViewPager和指示器绑定在一起
		//必须在mViewPager设置完数据后再绑定
		mIndicator.setViewPager(mViewPager);
	}
	
	class NewsMenuDetailAdapter extends PagerAdapter{
		//指示器的标题
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
}
