/**
 * Copyright 2016 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.sinosoft.hms.common.constant.GlobalNames;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 张战国
 * @since 2016年11月23日
 */
public class CookieUtil {
    private static final String DOMAIN = ConfigProperties.getPropertyValue(GlobalNames.DOMAIN);
    private static String COOKIE_SUB_USERID_KEY = ConfigProperties.getPropertyValue(GlobalNames.COOKIENAME);

    /**
     * 
     * <B>方法名称：setCookie</B><BR>
     * <B>概要说明：建立会话cookie</B><BR>
     * 
     * @param response
     * @param cookieName cookie名
     * @param cookieValue cookie值
     */
    public static void setCookie(HttpServletResponse response, String cookieName,
            String cookieValue) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        if (StringUtils.isNotBlank(DOMAIN)) {
            cookie.setDomain(DOMAIN);
        }
        response.addCookie(cookie);

    }

    /**
     * 
     * <B>方法名称：setCookie</B><BR>
     * <B>概要说明：建立固定生命周期的cookie</B><BR>
     * 
     * @param response
     * @param cookieName cookie名
     * @param cookieValue cookie值
     * @param expiry 生存时间
     */
    public static void setCookie(HttpServletResponse response, String cookieName,
            String cookieValue, int expiry) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(expiry);
        cookie.setPath("/");
        if (StringUtils.isNotBlank(DOMAIN)) {
            cookie.setDomain(DOMAIN);
        }
        response.addCookie(cookie);

    }

    /**
     * 
     * <B>方法名称：removeCookie</B><BR>
     * <B>概要说明：删除cookie</B><BR>
     * 
     * @param response
     * @param cookieName cookie名
     */
    public static void removeCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        if (StringUtils.isNotBlank(DOMAIN)) {
            cookie.setDomain(DOMAIN);
        }
        cookie.setSecure(false);
        response.addCookie(cookie);
    }

    /**
     * 
     * <B>方法名称：getCookie</B><BR>
     * <B>概要说明：获取cookie的值</B><BR>
     * 
     * @param request
     * @param cookieName cookie名
     * @return String cookie值
     */
    public static String getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        String cookieValue = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    if (cookieName.equals("oauserid")) {
                        cookieValue = AesUtil2.cbcDecrypt(cookie.getValue(), AesUtil2.getKey());
                    }
                    else {
                        cookieValue = cookie.getValue();
                    }
                    break;
                }
            }
        }
        return cookieValue;
    }

    /**
     * 
     * <B>方法名称：返回当前登录的用户的user_id</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @param request
     * @return 返回类型为String
     */
    public static String getUserID(HttpServletRequest request) {
        String str = request.getAttribute("userid").toString();
        //String str = getCookie(request,COOKIE_SUB_USERID_KEY);
        if (StringUtils.isNotBlank(str)) {
            //String uid =AesUtil.cbcDecrypt(str);
            return str;
        }
        return null;
    }

    /**
     * 
     * <B>方法名称：返回当前登录的用户的user_id</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @param request
     * @return 返回类型为Long
     */
    public static Long getUserid(HttpServletRequest request) {

        //String str =getCookie(request,COOKIE_SUB_USERID_KEY);
        String str = request.getAttribute("userid").toString();
        if (StringUtils.isNotBlank(str)) {
            //String uid =AesUtil.cbcDecrypt(str);
            return Long.parseLong(str);
        }
        return null;
    }

    /**
     * 
     * <B>方法名称：返回request中attribute的值</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @param request
     * @return 返回类型为String
     */
    public static String getAttribute(HttpServletRequest request, String attributeId) {
        String str = request.getAttribute(attributeId).toString();
        if (StringUtils.isNotBlank(str)) {
            return str;
        }
        return null;
    }
}
