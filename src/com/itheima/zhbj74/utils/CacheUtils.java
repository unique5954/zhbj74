package com.itheima.zhbj74.utils;

import android.content.Context;

public class CacheUtils {

	/**
	 * ��urlΪkey����jsonΪvalue�������ڱ���
	 */
	public static void setCache(String url,String json,Context ctx){
		PrefUtils.setString(ctx, url, json);
	}
	
	/**
	 * ���ļ����棬��MD5(url)Ϊ�ļ���,��jsonΪ�ļ�����
	 */
	//TODO
	
	/**
	 * ������MD5(url)Ϊ�ļ������ļ�
	 */
	//TODO
	
	/**
	 * ��ȡ����
	 */
	public static String getCache(String url,Context ctx){
		return PrefUtils.getString(ctx, url, null);
	}
}
