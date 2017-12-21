package com.itheima.zhbj74.base.impl.menu;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.itheima.zhbj74.R;
import com.itheima.zhbj74.base.BaseMenuDetailPager;
import com.itheima.zhbj74.domain.NewsMenu.NewsTabData;
import com.itheima.zhbj74.domain.NewsTabBean;
import com.itheima.zhbj74.domain.NewsTabBean.TopNews;
import com.itheima.zhbj74.global.GlobalConstants;
import com.itheima.zhbj74.utils.CacheUtils;
import com.itheima.zhbj74.view.TopNewsViewPager;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * 页签页面对象
 * @author DCK170503
 *
 */
public class TabDetailPager extends BaseMenuDetailPager{

	private NewsTabData mTabData;//单个页签
	private TextView view;
	private TopNewsViewPager mViewPager;//头条新闻
	private String mUrl;
	private ArrayList<TopNews> mTopNews;
	
	
	public TabDetailPager(Activity activity, NewsTabData newsTabData) {
		super(activity);
		mTabData = newsTabData;
		mUrl = GlobalConstants.SERVER_URL + mTabData.url;
	}

	@Override
	public View initView() {
//		view = new TextView(mActivity);
//		//view.setText(mTabData.title);
//		view.setTextColor(Color.RED);
//		view.setTextSize(22);
//		view.setGravity(Gravity.CENTER);
//		return view;
		
		View view = View.inflate(mActivity, R.layout.pager_tab_detail, null);
		mViewPager = (TopNewsViewPager) view.findViewById(R.id.vp_top_news);
		return view;
	}
	
	@Override
	public void initData() {
		//view.setText(mTabData.title);
		//先加载缓存
		String cache = CacheUtils.getCache(mUrl, mActivity);
		if(!TextUtils.isEmpty(cache)){
			System.out.println("TabDetailPager:解析缓存...");
			processData(cache);
		}
		
		//请求网络数据
		System.out.println("TabDetailPager:请求网络数据...");
		getDataFromServer();
	}
	
	private void getDataFromServer() {
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, mUrl, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				processData(result);
				
				//设置缓存
				CacheUtils.setCache(mUrl, result, mActivity);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				error.printStackTrace();
				Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
			}
		});
		
	}

	protected void processData(String result) {
		Gson gson = new Gson();
		NewsTabBean newsTabBean = gson.fromJson(result,NewsTabBean.class);
		//头条新闻
		mTopNews = newsTabBean.data.topnews;
		if(mTopNews!=null){
			mViewPager.setAdapter(new TopNewsAdapter());
		}
		
	}

	//头条新闻数据适配器
	class TopNewsAdapter extends PagerAdapter{

		private BitmapUtils mBitmapUtils;

		public TopNewsAdapter(){
			mBitmapUtils = new BitmapUtils(mActivity);
		}
		
		@Override
		public int getCount() {
			return mTopNews.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view==object;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView view = new ImageView(mActivity);
			view.setImageResource(R.drawable.topnews_item_default);
			//设置图片缩放方式,宽高填充父控件
			view.setScaleType(ScaleType.FIT_XY);
			//下载图片->将图片设置给imageview->避免内存溢出->缓存
			//xutils:BitmapUtils->cache->xBitmapCache
			String imageUrl = mTopNews.get(position).topimage;
			mBitmapUtils.display(view, imageUrl);
			container.addView(view);
			return view;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
		
	}

}
