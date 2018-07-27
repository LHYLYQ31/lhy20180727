package com.sinosoft.hms.modules.common.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinosoft.hms.common.constant.GlobalNames;
import com.sinosoft.hms.common.util.ConfigProperties;
import com.sinosoft.hms.common.util.CookieUtil;

@Controller
@RequestMapping("/cookie")
public class GBCookie {
    public static String userid = "userid";

    /**
     * !
     * 
     * <B>方法名称：setCookie</B><BR>
     * <B>概要说明：设置cookie值</B><BR>
     * 
     * @param cookiename
     *            cookie的name
     * @param cookievalue
     *            cookie的值
     * @param request
     *            访问请求
     * @param response
     */
    @ResponseBody
    @RequestMapping("/add")
    public void setCookie(String cookiename, String cookievalue, HttpServletResponse response) {
        try {
            Cookie cookie = new Cookie(cookiename, java.net.URLEncoder.encode(cookievalue, "utf-8"));
            cookie.setMaxAge(ConfigProperties.getIntPropertyValue(GlobalNames.COOKIE_INVALID_TIME, 60 * 60 * 4));
            if (!"*".equals(ConfigProperties.getPropertyValue(GlobalNames.DOMAIN))) {
                cookie.setDomain(ConfigProperties.getPropertyValue(GlobalNames.DOMAIN));
            }
            cookie.setPath("/");
            cookie.setSecure(false);
            response.addCookie(cookie);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * !
     * 
     * <B>方法名称：syncCookie</B><BR>
     * <B>概要说明：同步cookie值，更新应用系统Cookie值</B><BR>
     * 
     * @param cookiename
     *            cookie的name
     * @param cookievalue
     *            cookie的值
     * @param request
     *            访问请求
     * @param response
     *            返回请求
     */
    @ResponseBody
    @RequestMapping("/sync")
    public void syncCookie(String status, String cookie, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (GlobalNames.SUCESS_STARUS.equals(status) && StringUtils.isNotBlank(cookie)) {
                String userid = CookieUtil.getUserID(request);
                if (userid == null || (userid != null && !cookie.equals(userid))) {
                    CookieUtil.setCookie(response, "", cookie);
                }
            }
            else {
                CookieUtil.removeCookie(response, "userid");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * !
     * 
     * <B>方法名称：getCookie</B><BR>
     * <B>概要说明：获取cookie的值</B><BR>
     * 
     * @param cookiename
     *            要获取cookie对应的name
     * @param request
     *            访问请求
     * @param response
     *            返回请求
     * @return String value cookie的值
     */
    @ResponseBody
    @RequestMapping("/search")
    public String getCookie(String cookiename, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                // 获得具体的Cookie
                Cookie cookie = cookies[i];
                String name = cookie.getName();
                String value = cookie.getValue();
                if (cookiename.equals(name)) {
                    return value;
                }
            }
        }
        return null;
    }

    /**
     * !
     * 
     * <B>方法名称：delCookie</B><BR>
     * <B>概要说明：删除cookie</B><BR>
     * 
     * @param cookiename
     *            要删除的cookie的那么
     * @param request
     *            访问请求
     * @param response
     *            返回请求
     */
    @RequestMapping("/delete")
    public void delCookie(String cookiename, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie(cookiename, null);
        cookie.setMaxAge(0);
        // cookie.setDomain(".sxzkzy.com");
        cookie.setPath("/");
        cookie.setSecure(false);
        response.addCookie(cookie);
    }
}
