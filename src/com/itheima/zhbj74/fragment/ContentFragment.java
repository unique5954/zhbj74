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
	
	private ArrayList<BasePager> mPagers;//五个标签页的集合

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
		//添加五个标签页
		mPagers.add(new HomePager(mActivity));
		mPagers.add(new NewsCenterPager(mActivity));
		mPagers.add(new SmartServicePager(mActivity));
		mPagers.add(new GovAffairsPager(mActivity));
		mPagers.add(new SettingPager(mActivity));
		
		mViewPager.setAdapter(new ContentAdapter());
		
		//底栏标签切换监听
		rgGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rb_home:
					//首页
					mViewPager.setCurrentItem(0,false);//false:不要滑动动画
					break;
				case R.id.rb_news:
					//新闻中心
					mViewPager.setCurrentItem(1,false);
					break;
				case R.id.rb_smart:
					//智慧服务
					mViewPager.setCurrentItem(2,false);
					break;
				case R.id.rb_gov:
					//政务
					mViewPager.setCurrentItem(3,false);
					break;
				case R.id.rb_setting:
					//设置
					mViewPager.setCurrentItem(4,false);
					break;

				default:
					break;
				}
			}
		});
		
		//监听页面切换
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				BasePager pager = mPagers.get(position);
				pager.initData();//初始化页面数据
				
				if(position ==0 || position == mPagers.size()-1){
					//首页、设置页禁用侧边栏
					setSlidingMenuEnable(false);
				}else{
					//其他页开启侧边栏
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
		//第一次进入，加载首页
		mPagers.get(0).initData();
		//禁用侧边栏
		setSlidingMenuEnable(false);
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
			View view = pager.mRootView;//获取当前页面对象的布局
//			//初始化数据,viewpager会默认加载下一个界面,为了节省性能和流量，不在此次处调用
//			pager.initData();
			container.addView(view);
			return view;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
		
	}
	
	//获取新闻中心页面
	public NewsCenterPager getNewsCenterPager(){
		NewsCenterPager pager = (NewsCenterPager) mPagers.get(1);
		return pager;
	}
	
}
