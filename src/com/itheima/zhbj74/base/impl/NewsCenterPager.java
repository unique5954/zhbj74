package com.itheima.zhbj74.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.itheima.zhbj74.MainActivity;
import com.itheima.zhbj74.base.BasePager;
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

	public NewsCenterPager(Activity activity) {
		super(activity);
	}
	
	@Override
	public void initData() {
		//要给帧布局填充布局对象
		TextView view = new TextView(mActivity);
		view.setText("新闻中心");
		view.setTextColor(Color.RED);
		view.setTextSize(22);
		view.setGravity(Gravity.CENTER);
		
		flContent.addView(view);
		
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
				System.out.println("获取服务器返回结果...");
				processData(result);
				//缓存数据
				CacheUtils.setCache(GlobalConstants.CATEGORY_URL, result, mActivity);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				//请求失败
				error.printStackTrace();
				Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
			}
		});
		
	}

	/**
	 * 解析数据
	 */
	protected void processData(String json) {
		Gson gson = new Gson();
		NewsMenu data = gson.fromJson(json, NewsMenu.class);
		
		//获取侧边栏对象
		MainActivity mainUI = (MainActivity) mActivity;
		LeftMenuFragment fragment = mainUI.getLeftMenuFragment();
		//设置侧边栏数据
		fragment.setMenuData(data.data);
	}

}
