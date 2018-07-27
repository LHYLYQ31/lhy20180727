/**
 * Copyright 2016 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 goulijun
 * @since 2016年6月7日
 */
public class ValidateUtil {
	public final Map<String, Object> msg;

	/**
	 * <B>构造方法</B><BR>
	 */
	public ValidateUtil() {
		msg = new HashMap<>();
	}

	/**
	 * 
	 * <B>方法名称：字符串的验证</B><BR>
	 * <B>概要说明：字符串的验证</B><BR>
	 * 
	 * @author goulijun
	 * @生成时间：2016年6月7日 下午5:18:02
	 * @param str:待检查的字符串
	 * @param length:字符串的长度显示,长度=0时，不验证
	 * @param lentype:长度判断类型：dy(大于)/xy(小于)/dd(等于)
	 * @param strname:字符串名称(提示用)
	 * @param key:字符串页面获取错误提示key
	 * @param checkEmpty:是否检查空值
	 *            true:检查空值;false:不检查空值
	 */
	public void checkString(String str, Integer length, String lentype, String strname, String key,
			boolean checkEmpty) {
		String showmsg = "";
		if (checkEmpty) {
			if (StringUtils.isBlank(str)) {
				showmsg = strname + "不可以为空!";
			} else {
				if (length != 0) {
					showmsg = checklength(str, length, lentype, strname);
				}
			}
		} else {
			if (length != 0) {
				if (str != null && !str.isEmpty()) {
					if (str.length() > length) {
						showmsg = checklength(str, length, lentype, strname);
					}
				}
			}

		}
		if (!showmsg.isEmpty()) {
			msg.put(key, showmsg);
		}
	}

	/**
	 * 
	 * <B>方法名称：验证字符串的长度</B><BR>
	 * <B>概要说明：验证字符串的长度并返回相应的显示信息</B><BR>
	 * 
	 * @author：goulijun
	 * @生成时间：2016年7月7日 上午9:41:20
	 * @param str：需要验证的字符串
	 * @param length：验证的字符串的长度
	 * @param lentype：字符串长度验证方式标记(dy：大于；xy：小于；dd：等于)
	 * @param strname：验证的显示名称
	 * @return
	 */
	private static String checklength(String str, Integer length, String lentype, String strname) {
		String showmsg = "";
		if (lentype.equals("dy")) {
			if (str.length() > length) {
				showmsg = strname + "长度不可以大于" + length;
			}
		}
		if (lentype.equals("xy")) {
			if (str.length() < length) {
				showmsg = strname + "长度不可以小于" + length;
			}
		}
		if (lentype.equals("dd")) {
			if (str.length() != length) {
				showmsg = strname + "长度必须等于" + length;
			}
		}

		return showmsg;
	}

	/**
	 * 
	 * <B>方法名称：验证字符串</B><BR>
	 * <B>概要说明：验证字符串是否是6位相同的数字</B><BR>
	 * 
	 * @作者：goulijun
	 * @生成时间：2016年6月24日 下午2:04:16
	 * @param str：需要验证的字符串
	 * @return true:相同/false:不相同
	 */
	public boolean isSameCharacter(String str) {
		// 如果不区分大小写，可以添加下面这句
		// s = s.toUpperCase();或者 s = s.toLowerCase();
		String character = str.substring(0, 1);
		String replace = "";
		String test = str.replace(character, replace);
		if ("".equals(test))
			return true;
		return false;
	}

	/**
	 * 
	 * <B>方法名称：验证字符串</B><BR>
	 * <B>概要说明：验证字符串是否是6位连续的数字(例如：123456)</B><BR>
	 * 
	 * @作者：goulijun
	 * @生成时间：2016年6月24日 下午2:07:54
	 * @param s：需要验证的字符串
	 * @return true:连续/false:不连续
	 */
	public boolean isContinuityCharacter(String s) {
		boolean continuity = true;
		char[] data = s.toCharArray();
		for (int i = 0; i < data.length - 1; i++) {
			int a = Integer.parseInt(data[i] + "");
			int b = Integer.parseInt(data[i + 1] + "");
			continuity = continuity && (a + 1 == b || a - 1 == b);
		}
		return continuity;
	}

	/**
	 * 
	 * <B>方法名称：检查手机号码</B><BR>
	 * <B>概要说明：检查是否为合法的手机号码</B><BR>
	 * 
	 * @作者：goulijun
	 * @生成时间：2016年6月24日 下午2:12:34
	 * @param phone：手机号码
	 * @return true:合法/false:不合法
	 */
	public boolean isLegalPhone(String phone) {
		String regex = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
		boolean result = Pattern.matches(regex, phone);
		return result;
	}

	/**
	 * 
	 * <B>方法名称：检查手机号码</B><BR>
	 * <B>概要说明：检查是否为合法的手机号码</B><BR>
	 * 
	 * @作者：goulijun
	 * @生成时间：2016年6月24日 下午2:12:34
	 * @param phone：手机号码
	 * @return true:合法/false:不合法
	 */
	public boolean isLegalPhone2(String phone) {
		String regex = "^(1)\\d{10}$";
		boolean result = Pattern.matches(regex, phone);
		return result;
	}

	/**
	 * 
	 * <B>方法名称：检查姓名</B><BR>
	 * <B>概要说明：检查姓名是否合法(只包括汉字和 . )</B><BR>
	 * 
	 * @作者：goulijun
	 * @生成时间：2016年6月24日 下午3:06:34
	 * @param name：姓名
	 * @return true:合法/false:不合法
	 */
	public static boolean isLegalName(String name) {
		String regex = "[\u4e00-\u9fa5]{2,15}";
		boolean result = Pattern.matches(regex, name);
		return result;
	}

	/**
	 * 
	 * <B>方法名称：判断字符串是否是指定长度的数字串</B><BR>
	 * <B>概要说明：判断字符串是否是指定长度的数字串</B><BR>
	 * 
	 * @作者：goulijun
	 * @生成时间：2016年6月24日 下午5:19:03
	 * @param numstr：字符串
	 * @param length：长度
	 * @return true(是指定长度数字串)/false(不是指定长度数字串)
	 */
	public boolean isNumberStr(String numstr, Integer length) {
		String regex = "^[0-9 ]{" + length + "}$";
		boolean result = Pattern.matches(regex, numstr);
		return result;
	}

	/**
	 * 
	 * <B>方法名称：检查身份证号是否合法</B><BR>
	 * <B>概要说明：检查身份证号是否合法</B><BR>
	 * 
	 * @author：goulijun
	 * @生成时间：2016年6月24日 下午5:46:50
	 * @param idcardno
	 * @return
	 */
	public boolean isLegalIdcardno(String idcardno) {
		String regex = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";
		boolean result = Pattern.matches(regex, idcardno);
		return result;
	}

	/**
	 * 
	 * <B>方法名称：验证邮箱</B><BR>
	 * <B>概要说明：验证邮箱是否合法</B><BR>
	 * 
	 * @author：goulijun
	 * @生成时间：2016年7月7日 上午9:43:58
	 * @param email：需要验证的邮箱
	 * @return：String：验证的提示
	 */
	public String isLegalEmail(String email) {
		String resultmsg = "right";
		String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		if (email == null || email.isEmpty()) {
			resultmsg = "邮箱不可以为空!";
		} else {
			if (email.length() > 90) {
				resultmsg = "邮箱不合法!";
			} else {
				boolean result = Pattern.matches(regex, email);
				if (!result) {
					resultmsg = "邮箱不合法!";
				}
			}
		}
		return resultmsg;
	}

}
