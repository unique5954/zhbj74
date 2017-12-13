package com.itheima.zhbj74.domain;

import java.util.ArrayList;

/**
 * 分类信息封装
 * json:逢{}创建对象，逢[]创建集合
 * @author DCK170503
 *
 */
public class NewsMenu {
	public int retcode;
	public ArrayList<Integer> extend;
	public ArrayList<NewsMenuData> data;
	
	
	
	@Override
	public String toString() {
		return "NewsMenu [retcode=" + retcode + ", extend=" + extend
				+ ", data=" + data + "]";
	}

	//侧边栏菜单
	public class NewsMenuData{
		public int id;
		public String title;
		public int type;
		
		public ArrayList<NewsTabData> children;

		@Override
		public String toString() {
			return "NewsMenuData [id=" + id + ", title=" + title + ", type="
					+ type + ", children=" + children + "]";
		}
		
		
	}
	
	//页签
	public class NewsTabData{
		public int id;
		public String title;
		public int type;
		public String url;
		
		@Override
		public String toString() {
			return "NewsTabData [id=" + id + ", title=" + title + ", type="
					+ type + ", url=" + url + "]";
		}
		
		
	}
}
