package com.itheima.zhbj74.utils;

import android.content.Context;

public class CacheUtils {

	/**
	 * 以url为key，以json为value，保存在本地
	 */
	public static void setCache(String url,String json,Context ctx){
		PrefUtils.setString(ctx, url, json);
	}
	
	/**
	 * 用文件缓存，以MD5(url)为文件名,以json为文件内容
	 */
	//TODO
	
	/**
	 * 查找以MD5(url)为文件名的文件
	 */
	//TODO
	
	/**
	 * 获取缓存
	 */
	public static String getCache(String url,Context ctx){
		return PrefUtils.getString(ctx, url, null);
	}
}
