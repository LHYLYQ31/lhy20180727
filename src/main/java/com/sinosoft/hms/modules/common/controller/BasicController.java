/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年3月21日
 */
public class BasicController {
    /**
     * 
     * <B>方法名称：获取当前登录用户的id</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年3月21日 下午3:42:46
     * @param request
     * @return String
     */
    public String getUserId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userId = session.getAttribute("userId") == null ? "" : session.getAttribute("userId").toString();
        return userId;
    }

}
