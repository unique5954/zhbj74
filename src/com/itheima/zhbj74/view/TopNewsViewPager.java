package com.itheima.zhbj74.view;

import java.util.Currency;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.text.method.MovementMethod;
import android.util.AttributeSet;
import android.view.MotionEvent;
/**
 * ͷ�������Զ���ViewPager
 * @author DCK170503
 *
 */
public class TopNewsViewPager extends ViewPager{

	private int startX;
	private int startY;
	private int endX;
	private int endY;

	public TopNewsViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TopNewsViewPager(Context context) {
		super(context);
	}
	
	/**
	 * 1�����»���Ҫ����
	 * 2�����һ������ҵ�ǰ�ǵ�һ��ҳ�棬Ҫ����
	 * 3�����󻬶����ҵ�ǰ�����һ��ҳ�棬Ҫ����
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		//�������и��ؼ������ڿؼ���Ҫ�����¼�
		getParent().requestDisallowInterceptTouchEvent(true);
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startX = (int) ev.getX();
			startY = (int) ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			endX = (int) ev.getX();
			endY = (int) ev.getY();
			
			int dx = endX - startX;
			int dy = endY - startY;
			
			if(Math.abs(dy) < Math.abs(dx)){
				int currentItem = getCurrentItem();
				//���һ���
				if(dx>0){
					//����
					System.out.println("����");
					if(currentItem==0){
						//��һ��ҳ��,Ҫ����
						System.out.println("����,��һ��ҳ��,Ҫ����");
						getParent().requestDisallowInterceptTouchEvent(false);
					}
				}else{
					//����
					System.out.println("����");
					int count = getAdapter().getCount();//item����
					if(currentItem==count-1){
						//���һ��ҳ��,Ҫ����
						System.out.println("����,���һ��ҳ��,Ҫ����");
						getParent().requestDisallowInterceptTouchEvent(false);
					}
				}
			}else{
				//���»���
				System.out.println("���»���,Ҫ����");
				getParent().requestDisallowInterceptTouchEvent(false);
			}
			
			break;

		default:
			break;
		}
		return super.dispatchTouchEvent(ev);
	}
	

}
