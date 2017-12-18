package com.itheima.zhbj74.base.impl;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.itheima.zhbj74.MainActivity;
import com.itheima.zhbj74.base.BaseMenuDetailPager;
import com.itheima.zhbj74.base.BasePager;
import com.itheima.zhbj74.base.impl.menu.InteractMenuDetailPager;
import com.itheima.zhbj74.base.impl.menu.NewsMenuDetailPager;
import com.itheima.zhbj74.base.impl.menu.PhotosMenuDetailPager;
import com.itheima.zhbj74.base.impl.menu.TopicMenuDetailPager;
import com.itheima.zhbj74.domain.NewsMenu;
import com.itheima.zhbj74.fragment.LeftMenuFragment;
import com.itheima.zhbj74.global.GlobalConstants;
import com.itheima.zhbj74.utils.CacheUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
/**
 * 新闻中心
 * @author DCK170503
 *
 */
public class NewsCenterPager extends BasePager{
	
	//菜单详情页集合
	private ArrayList<BaseMenuDetailPager> mMenuDetailPagers;
	private NewsMenu mNewsData;

	public NewsCenterPager(Activity activity) {
		super(activity);
	}
	
	@Override
	public void initData() {
		//要给帧布局填充布局对象
//		TextView view = new TextView(mActivity);
//		view.setText("新闻中心");
//		view.setTextColor(Color.RED);
//		view.setTextSize(22);
//		view.setGravity(Gravity.CENTER);
//		
//		flContent.addView(view);
		
		//修改页面标题
		tvTitle.setText("新闻中心");
		
		//显示菜单按钮
		btnMenu.setVisibility(View.VISIBLE);
		
		//先判断有没有缓存
		String cache = CacheUtils.getCache(GlobalConstants.CATEGORY_URL, mActivity);
		if(!TextUtils.isEmpty(cache)){
			//有缓存,解析数据
			System.out.println("有缓存,解析数据...");
			processData(cache);
		}
		
		//请求服务器，获取数据
		getDataFromServer();
		
	}

	//从服务器获取数据,开源框架XUtils
	private void getDataFromServer() {
		/*
		 * 需要有以下权限:
		 * <uses-permission android:name="android.permission.INTERNET" />
		 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
		 */
		HttpUtils utils = new HttpUtils();
		utils.send(HttpMethod.GET, GlobalConstants.CATEGORY_URL, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				//请求成功
				String result = responseInfo.result;//获取服务器返回结果
				System.out.println("请求成功,获取服务器返回结果...");
				processData(result);
				//缓存数据
				CacheUtils.setCache(GlobalConstants.CATEGORY_URL, result, mActivity);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				//请求失败
				System.out.println("请求失败...");
				error.printStackTrace();
				Toast.makeText(mActivity, "[请求服务器失败]"+msg, Toast.LENGTH_SHORT).show();
			}
		});
		
	}

	/**
	 * 解析数据
	 */
	protected void processData(String json) {
		Gson gson = new Gson();
		mNewsData = gson.fromJson(json, NewsMenu.class);
		
		//获取侧边栏对象
		MainActivity mainUI = (MainActivity) mActivity;
		LeftMenuFragment fragment = mainUI.getLeftMenuFragment();
		//设置侧边栏数据
		fragment.setMenuData(mNewsData.data);
		
		//初始化4个菜单
		mMenuDetailPagers = new ArrayList<BaseMenuDetailPager>();
		mMenuDetailPagers.add(new NewsMenuDetailPager(mActivity,mNewsData.data.get(0).children));
		mMenuDetailPagers.add(new TopicMenuDetailPager(mActivity));
		mMenuDetailPagers.add(new PhotosMenuDetailPager(mActivity));
		mMenuDetailPagers.add(new InteractMenuDetailPager(mActivity));
		setCurrentDetailPager(0);//设置默认页面
	}
	
	//设置菜单详情页
	public void setCurrentDetailPager(int position){
		//重新给frameLayout添加内容
		BaseMenuDetailPager pager = mMenuDetailPagers.get(position);
		View view = pager.mRootView;
		//给ContentFragment的标签页的FrameLayout(fl_content)的布局,添加布局
		flContent.removeAllViews();//清除之前布局内容
		flContent.addView(view);
		//初始化页面数据
		pager.initData();
		tvTitle.setText(mNewsData.data.get(position).title);
	}

}
