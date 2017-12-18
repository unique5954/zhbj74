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
 * ��������
 * @author DCK170503
 *
 */
public class NewsCenterPager extends BasePager{
	
	//�˵�����ҳ����
	private ArrayList<BaseMenuDetailPager> mMenuDetailPagers;
	private NewsMenu mNewsData;

	public NewsCenterPager(Activity activity) {
		super(activity);
	}
	
	@Override
	public void initData() {
		//Ҫ��֡������䲼�ֶ���
//		TextView view = new TextView(mActivity);
//		view.setText("��������");
//		view.setTextColor(Color.RED);
//		view.setTextSize(22);
//		view.setGravity(Gravity.CENTER);
//		
//		flContent.addView(view);
		
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
				System.out.println("����ɹ�,��ȡ���������ؽ��...");
				processData(result);
				//��������
				CacheUtils.setCache(GlobalConstants.CATEGORY_URL, result, mActivity);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				//����ʧ��
				System.out.println("����ʧ��...");
				error.printStackTrace();
				Toast.makeText(mActivity, "[���������ʧ��]"+msg, Toast.LENGTH_SHORT).show();
			}
		});
		
	}

	/**
	 * ��������
	 */
	protected void processData(String json) {
		Gson gson = new Gson();
		mNewsData = gson.fromJson(json, NewsMenu.class);
		
		//��ȡ���������
		MainActivity mainUI = (MainActivity) mActivity;
		LeftMenuFragment fragment = mainUI.getLeftMenuFragment();
		//���ò��������
		fragment.setMenuData(mNewsData.data);
		
		//��ʼ��4���˵�
		mMenuDetailPagers = new ArrayList<BaseMenuDetailPager>();
		mMenuDetailPagers.add(new NewsMenuDetailPager(mActivity,mNewsData.data.get(0).children));
		mMenuDetailPagers.add(new TopicMenuDetailPager(mActivity));
		mMenuDetailPagers.add(new PhotosMenuDetailPager(mActivity));
		mMenuDetailPagers.add(new InteractMenuDetailPager(mActivity));
		setCurrentDetailPager(0);//����Ĭ��ҳ��
	}
	
	//���ò˵�����ҳ
	public void setCurrentDetailPager(int position){
		//���¸�frameLayout�������
		BaseMenuDetailPager pager = mMenuDetailPagers.get(position);
		View view = pager.mRootView;
		//��ContentFragment�ı�ǩҳ��FrameLayout(fl_content)�Ĳ���,��Ӳ���
		flContent.removeAllViews();//���֮ǰ��������
		flContent.addView(view);
		//��ʼ��ҳ������
		pager.initData();
		tvTitle.setText(mNewsData.data.get(position).title);
	}

}
