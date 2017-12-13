package com.itheima.zhbj74.fragment;

import java.util.ArrayList;

import com.itheima.zhbj74.MainActivity;
import com.itheima.zhbj74.R;
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
//�����
public class LeftMenuFragment extends BaseFragment{

	private ListView lvList;
	//�������������
	private ArrayList<NewsMenuData> mNewsMenuData;
	private int mCurrentPos;//��ǰ��ѡ�еĲ˵�λ��
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

	//���ò��������
	public void setMenuData(ArrayList<NewsMenuData> data){
		//����ҳ��
		mNewsMenuData = data;
		
		mAdapter = new LeftMenuAdapter();
		lvList.setAdapter(mAdapter);
		
		lvList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				//���µ�ǰ��ѡ�е�λ��
				mCurrentPos = position;
				//ˢ��listview
				mAdapter.notifyDataSetChanged();
				//��������
				toggle();
				
			}
		});
	}
	
	/**
	 * ���ز����
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
			//����ѡ�в˵���ɫ
			if(position==mCurrentPos){
				//��ѡ��
				tvMenu.setEnabled(true);
			}else{
				tvMenu.setEnabled(false);
			}
			
			return view;
		}
		
	}
}
