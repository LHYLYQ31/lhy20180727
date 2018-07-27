/**
 * Copyright 2017 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.common.filter;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sinosoft.hms.common.cache.MemoryCache;
import com.sinosoft.hms.common.constant.GlobalNames;
import com.sinosoft.hms.common.util.AesUtil2;
import com.sinosoft.hms.common.util.HttpUtil;

import net.sf.json.JSONObject;

/**
 * <B>系统名称：移动推送管理系统</B><BR>
 * <B>模块名称：拦截器</B><BR>
 * <B>中文类名：拦截器</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2017年11月21日
 */
public class LoginFilter implements Filter {
    Logger logger = LoggerFactory.getLogger(LoginFilter.class);
    private static String validate_key = "";

    /**
     * <B>方法名称：拦截器结束方法</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {

    }

    /**
     * <B>方法名称：拦截器</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse,
     *      javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String contextPath = request.getContextPath();
        HttpSession session = request.getSession();
        String key = request.getParameter("validate_key");
        //或取ajax请求的消息头
        String XRequested = request.getHeader("X-Requested-With");
        //对外提供的接口不拦截
        if (StringUtils.isNotBlank(key) && key.equals(validate_key)) {
            chain.doFilter(request, response);
        }
        else {
            //判断是否是需要拦截的路径
            if (HttpUtil.isStaticReq(request) || checkPath(request)) {
                chain.doFilter(request, response);
            }
            else {
                String ticket = session.getAttribute("ticket") == null ? "" : session.getAttribute("ticket").toString();
                //判断是否登录
                if (StringUtils.isEmpty(ticket)) {
                    //重定向到登录页面
                    if ("XMLHttpRequest".equals(XRequested)) {
                        ajaxSendRedirect(response);
                    }
                    else {

                        response.sendRedirect(contextPath + "/modules/login/login.html");
                    }
                }
                else {
                    //判断登录是否超时
                    if (checkOverdue(ticket)) {
                        request.getSession().setAttribute("ticket", "");
                        request.getSession().setAttribute("userId", "");
                        request.getSession().setAttribute("userName", "");
                        if ("XMLHttpRequest".equals(XRequested)) {
                            ajaxSendRedirect(response);
                        }
                        else {

                            response.sendRedirect(contextPath + "/modules/login/login.html");
                        }
                    }
                    else {
                        chain.doFilter(request, response);
                    }
                }
            }

        }

    }

    /**
     * <B>方法名称：拦截器初始还方法</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        validate_key = filterConfig.getInitParameter("validate_key");

    }

    /**
     * 
     * <B>方法名称：判断路径是否拦截</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年1月31日 下午1:38:18
     * @param request
     * @return Boolean
     */
    public Boolean checkPath(HttpServletRequest request) {
        List list = MemoryCache.getNoFilterList();
        Iterator it = list.iterator();
        Boolean result = false;
        String path = request.getServletPath();//请求路径
        logger.info("请求路径" + path);
        while (it.hasNext()) {
            //如果是退出操作
            if (path.indexOf("loginOut") != -1) {
                request.getSession().setAttribute("ticket", "");
                request.getSession().setAttribute("userId", "");
                break;
            }
            String noFilterPath = (String) it.next();
            if (path.indexOf(noFilterPath) != -1) {
                //不需要拦截
                result = true;
                break;
            }

        }
        return result;

    }

    /**
     * 
     * <B>方法名称：判断ticket是否过期</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年1月31日 下午1:46:35
     * @param ticket
     * @return Boolean
     */
    public Boolean checkOverdue(String ticket) {
        Boolean result = false;
        Long ctime = System.currentTimeMillis();
        String key = GlobalNames.AES_KEY;
        String j_ticket = AesUtil2.cbcDecrypt(ticket, key.getBytes());
        Long login_time = Long.valueOf(j_ticket.split("-")[1]);
        if (login_time + 30 * 60 * 1000 < ctime) {
            result = true;
        }
        return result;
    }

    /**
     * 
     * <B>方法名称：ajax请求登录超时被拦截处理方式</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年3月20日 上午10:30:57
     * @param response void
     */
    public void ajaxSendRedirect(HttpServletResponse response) {
        JSONObject json = new JSONObject();
        json.put("status", "IsAjax");
        try {
            response.getWriter().write(json.toString());
        }
        catch (IOException e) {
            e.printStackTrace();
            logger.error("ajax请求登录超时报错" + e.getMessage());
            logger.debug("ajax请求重定向报错", e);
        }
    }

}
