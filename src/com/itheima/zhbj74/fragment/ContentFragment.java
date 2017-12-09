package com.itheima.zhbj74.fragment;

import java.util.ArrayList;
import com.itheima.zhbj74.R;
import com.itheima.zhbj74.base.BasePager;
import com.itheima.zhbj74.base.impl.GovAffairsPager;
import com.itheima.zhbj74.base.impl.HomePager;
import com.itheima.zhbj74.base.impl.NewsCenterPager;
import com.itheima.zhbj74.base.impl.SettingPager;
import com.itheima.zhbj74.base.impl.SmartServicePager;
import com.itheima.zhbj74.view.NoScrollViewPager;
import android.support.v4.view.PagerAdapter;
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
			pager.initData();
			container.addView(view);
			return view;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
		
	}
	
}
