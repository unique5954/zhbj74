package com.itheima.zhbj74.fragment;

import java.util.ArrayList;

import com.itheima.zhbj74.MainActivity;
import com.itheima.zhbj74.R;
import com.itheima.zhbj74.base.impl.NewsCenterPager;
import com.itheima.zhbj74.domain.NewsMenu.NewsMenuData;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
//侧边栏
public class LeftMenuFragment extends BaseFragment{

	private ListView lvList;
	//侧边栏网络数据
	private ArrayList<NewsMenuData> mNewsMenuData;
	private int mCurrentPos;//当前被选中的菜单位置
	private LeftMenuAdapter mAdapter;
	
	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.fragment_left_menu, null);
		lvList = (ListView) view.findViewById(R.id.lv_list);
		return view;
	}

	@Override
	public void initData() {
		
	}

	//设置侧边栏数据
	public void setMenuData(ArrayList<NewsMenuData> data){
		mCurrentPos=0;//当前选中位置归零,防止不同tab切换后显示内容与侧边栏选中不同
		//更新页面
		mNewsMenuData = data;
		
		mAdapter = new LeftMenuAdapter();
		lvList.setAdapter(mAdapter);
		
		lvList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				//更新当前被选中的位置
				mCurrentPos = position;
				//刷新listview
				mAdapter.notifyDataSetChanged();
				//收起侧边栏
				toggle();
				
				//侧边栏点击后，给ContentFragment的标签页的FrameLayout(fl_content)的布局,添加布局
				setCurrentDetailPager(position);
			}
		});
	}
	/**
	 * 设置当前详情页
	 */
	protected void setCurrentDetailPager(int position) {
		// 获取新闻中心的对象
		MainActivity mainUI = (MainActivity) mActivity;
		//获取ContentFragment
		ContentFragment fragment = mainUI.getContentFragment();
		//获取NewsCenterPager
		NewsCenterPager newsCenterPager = fragment.getNewsCenterPager();
		//修改NewsCenterPager的FrameLayout的布局
		newsCenterPager.setCurrentDetailPager(position);
	}

	/**
	 * 开关侧边栏
	 */
	protected void toggle() {
		MainActivity mainUI = (MainActivity) mActivity;
		SlidingMenu slidingMenu = mainUI.getSlidingMenu();
		slidingMenu.toggle();
		
	}

	class LeftMenuAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mNewsMenuData.size();
		}

		@Override
		public NewsMenuData getItem(int position) {
			return mNewsMenuData.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(mActivity, R.layout.list_item_left_menu,null);
			TextView tvMenu = (TextView) view.findViewById(R.id.tv_menu);
			NewsMenuData item = getItem(position);
			tvMenu.setText(item.title);
			//处理选中菜单变色
			if(position==mCurrentPos){
				//被选中
				tvMenu.setEnabled(true);
			}else{
				tvMenu.setEnabled(false);
			}
			
			return view;
		}
		
	}
}
