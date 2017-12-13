package com.itheima.zhbj74.fragment;

import java.util.ArrayList;

import com.itheima.zhbj74.R;
import com.itheima.zhbj74.domain.NewsMenu.NewsMenuData;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
//侧边栏
public class LeftMenuFragment extends BaseFragment{

	private ListView lvList;
	//侧边栏网络数据
	private ArrayList<NewsMenuData> mNewsMenuData;
	
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
		//更新页面
		mNewsMenuData = data;
		
		lvList.setAdapter(new LeftMenuAdapter());
		System.out.println("是否为空："+lvList.getAdapter().toString());
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
//			TextView tvMenu = (TextView) view.findViewById(R.id.tv_menu);
//			NewsMenuData item = getItem(position);
//			tvMenu.setText(item.title);
			return view;
		}
		
	}
}
