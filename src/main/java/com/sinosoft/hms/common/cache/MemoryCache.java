package com.sinosoft.hms.common.cache;

import java.util.ArrayList;
import java.util.List;

/*
* 
* @ClassName: MemoryCache 
* @Description: 缓存类；
* @author kjx
* @date 2016-3-30 上午08:58:33 
* @version V1.0
*/
public class MemoryCache {

	// 需要拦截的具体访问路径
	private static List<String> filterList = new ArrayList<String>();

	// 不需要拦截的具体访问路径
	private static List<String> noFilterList = new ArrayList<String>();

	// 需要身份验证的路径
	private static List<String> authList = new ArrayList<String>();

	/**
	 * 获取需要身份验证的路径
	 */
	public static List<String> getAuthList() {
		return authList;
	}

	/**
	 * 设置需要身份验证的路径
	 */
	public static void setAuthList(List<String> authList) {
		MemoryCache.authList = authList;
	}

	/**
	 * 获取需要拦截的URL
	 */
	public static List<String> getFilterList() {
		return filterList;
	}

	/**
	 * 设置需要拦截的URL
	 */
	public static void setFilterList(List<String> filterList) {
		MemoryCache.filterList = filterList;
	}

	/**
	 * 获取不需要拦截的URL
	 */
	public static List<String> getNoFilterList() {
		return noFilterList;
	}

	/**
	 * 设置不需要拦截的URL
	 */
	public static void setNoFilterList(List<String> noFilterList) {
		MemoryCache.noFilterList = noFilterList;
	}
}
