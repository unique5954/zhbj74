package com.itheima.zhbj74;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
//新手引导页面
public class GuideActivity extends Activity {

	private ViewPager mViewPager;
	private LinearLayout llContainer;
	private ImageView ivRedPoint;//小红点
	private int mPointDis;//小红点移动距离
	
	//引导页图片id数组
	private int[] mImageIds = new int[]{R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};
	private ArrayList<ImageView> mImageViewList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//去掉标题栏,必须在setContentView之前调用
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guide);
		
		mViewPager = (ViewPager) findViewById(R.id.vp_guide);
		llContainer = (LinearLayout) findViewById(R.id.ll_container);
		ivRedPoint = (ImageView) findViewById(R.id.iv_red_point);
		
		initData();//初始化ImageView数据
		mViewPager.setAdapter(new GuideAdapter());
		
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				//某个页面被选中回调
			}
			
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				//当页面滑动过程中的回调
				//更新小红点的距离
			}
			
			@Override
			public void onPageScrollStateChanged(int state) {
				//页面状态发生变化回调
			}
		});
		
		//计算两个圆点距离
		//小红点移动距离=第二个圆点left值-第一个圆点left值
        //mPointDis = llContainer.getChildAt(1).getLeft() - llContainer.getChildAt(0).getLeft();
		//监听layout方法结束的事件,位置确定好之后，才能取圆点距离
		//视图树
		ivRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			
			@Override
			public void onGlobalLayout() {
				//避免多次调用，直接remove掉
				//ivRedPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);//版本兼容问题
				ivRedPoint.getViewTreeObserver().removeGlobalOnLayoutListener(this);
				//layout 方法执行结束的回调
				mPointDis = llContainer.getChildAt(1).getLeft() - llContainer.getChildAt(0).getLeft();
			}
		});
		
	}
	
	//初始化数据
	private void initData(){
		mImageViewList = new ArrayList<ImageView>();
		for(int i=0;i<mImageIds.length;i++){
			ImageView view = new ImageView(this);
			// 通过设置背景，可以让宽高填充布局
			view.setBackgroundResource(mImageIds[i]);
			mImageViewList.add(view);
			
			//初始化小圆点
			ImageView point = new ImageView(this);
			//设置图片(shape形状)不通过设置背景填充布局，只根据实际大小显示
			point.setImageResource(R.drawable.shape_point_gray);
			
			//从第二个小圆点开始设置左边距
			//初始化布局参数，宽高包裹内容
			LinearLayout.LayoutParams params = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			if(i>0){
				//设置小圆点左边距
				params.leftMargin=10;
			}
			
			//设置布局参数
			point.setLayoutParams(params);
			
			//给线性布局容器添加圆点
			llContainer.addView(point);
		}
	}
	
	class GuideAdapter extends PagerAdapter{
		//item个数
		@Override
		public int getCount() {
			return mImageViewList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			//判断arg1是否为View对象
			return arg0==arg1;
		}
		
		//初始化item布局
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView view = mImageViewList.get(position);
			container.addView(view);
			return view;
		}
		
		//销毁item布局
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}
	
}
