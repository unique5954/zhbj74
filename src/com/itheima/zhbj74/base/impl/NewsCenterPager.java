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
 * ��������
 * @author DCK170503
 *
 */
public class NewsCenterPager extends BasePager{

	public NewsCenterPager(Activity activity) {
		super(activity);
	}
	
	@Override
	public void initData() {
		//Ҫ��֡������䲼�ֶ���
		TextView view = new TextView(mActivity);
		view.setText("��������");
		view.setTextColor(Color.RED);
		view.setTextSize(22);
		view.setGravity(Gravity.CENTER);
		
		flContent.addView(view);
		
		//�޸�ҳ�����
		tvTitle.setText("��������");
		
		//��ʾ�˵���ť
		btnMenu.setVisibility(View.VISIBLE);
		
		//���ж���û�л���
		String cache = CacheUtils.getCache(GlobalConstants.CATEGORY_URL, mActivity);
		if(!TextUtils.isEmpty(cache)){
			//�л���,��������
			System.out.println("�л���,��������...");
			processData(cache);
		}
		
		//�������������ȡ����
		getDataFromServer();
		
	}

	//�ӷ�������ȡ����,��Դ���XUtils
	private void getDataFromServer() {
		/*
		 * ��Ҫ������Ȩ��:
		 * <uses-permission android:name="android.permission.INTERNET" />
		 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
		 */
		HttpUtils utils = new HttpUtils();
		utils.send(HttpMethod.GET, GlobalConstants.CATEGORY_URL, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				//����ɹ�
				String result = responseInfo.result;//��ȡ���������ؽ��
				System.out.println("��ȡ���������ؽ��...");
				processData(result);
				//��������
				CacheUtils.setCache(GlobalConstants.CATEGORY_URL, result, mActivity);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				//����ʧ��
				error.printStackTrace();
				Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
			}
		});
		
	}

	/**
	 * ��������
	 */
	protected void processData(String json) {
		Gson gson = new Gson();
		NewsMenu data = gson.fromJson(json, NewsMenu.class);
		
		//��ȡ���������
		MainActivity mainUI = (MainActivity) mActivity;
		LeftMenuFragment fragment = mainUI.getLeftMenuFragment();
		//���ò��������
		fragment.setMenuData(data.data);
	}

}
