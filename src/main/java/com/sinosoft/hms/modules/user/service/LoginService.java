/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.user.service;

import javax.servlet.http.HttpServletRequest;

import com.sinosoft.hms.modules.user.model.User;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年1月24日
 */
public interface LoginService {
    /**
     * 
     * <B>方法名称：登录接口</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年1月24日 上午11:43:00
     * @param u
     * @return String
     */
    public String login(HttpServletRequest req, User u);

}
