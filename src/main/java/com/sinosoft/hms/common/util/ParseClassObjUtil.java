/**
 * Copyright 2017 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 goulijun
 * @since 2017年1月6日
 */
public class ParseClassObjUtil {
	/**
	 * 
	 * <B>方法名称：获取属性类型(type)，属性名(name)，属性值(value)的map组成的list</B><BR>
	 * <B>概要说明：</B><BR>
	 * 
	 * @author：goulijun
	 * @生成时间：2017年1月6日 上午10:09:08
	 * @param obj
	 * @return
	 */
	@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
	public List<Object> getFiledsInfo(Object obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		String[] fieldNames = new String[fields.length];
		List<Object> list = new ArrayList();
		Map<String, Object> infoMap = null;
		for (int i = 0; i < fields.length; i++) {
			infoMap = new HashMap();
			infoMap.put("type", fields[i].getType().toString());
			infoMap.put("name", fields[i].getName());
			// infoMap.put("value", getFieldValueByName(fields[i].getName(),
			// obj));
			list.add(infoMap);
		}
		return list;

	}

	/**
	 * 
	 * <B>方法名称：根据属性名获取属性值</B><BR>
	 * <B>概要说明：</B><BR>
	 * 
	 * @author：goulijun
	 * @生成时间：2017年1月6日 上午10:09:32
	 * @param fieldName
	 * @param o
	 * @return
	 */
	public Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * <B>方法名称：获取属性名数组</B><BR>
	 * <B>概要说明：</B><BR>
	 * 
	 * @author：goulijun
	 * @生成时间：2017年1月6日 上午10:10:16
	 * @param o
	 * @return
	 */
	public String[] getFiledName(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		String[] fieldNames = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			fieldNames[i] = fields[i].getName();
		}
		return fieldNames;
	}

	/**
	 * 
	 * <B>方法名称：获取对象的所有属性值，返回一个对象数组</B><BR>
	 * <B>概要说明：</B><BR>
	 * 
	 * @author：goulijun
	 * @生成时间：2017年1月6日 上午10:11:21
	 * @param o
	 * @return
	 */
	public Object[] getFiledValues(Object o) {
		String[] fieldNames = this.getFiledName(o);
		Object[] value = new Object[fieldNames.length];
		for (int i = 0; i < fieldNames.length; i++) {
			value[i] = this.getFieldValueByName(fieldNames[i], o);
		}
		return value;
	}

}
