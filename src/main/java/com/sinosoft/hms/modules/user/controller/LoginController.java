/**
 * Copyright 2017 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinosoft.hms.modules.user.model.User;
import com.sinosoft.hms.modules.user.service.LoginService;
import com.sinosoft.hms.modules.user.service.UserService;

/**
 * <B>系统名称：移动推送管理系统</B><BR>
 * <B>模块名称：登录模块</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2017年11月24日
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    LoginService loginService;

    /**
     * 
     * 
     * <B>方法名称：登录接口</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年1月24日 上午11:59:16
     * @param req
     * @param res
     * @param userCode 用户名
     * @param password 密码
     * @return String
     */

    @ResponseBody
    @RequestMapping(value = "/login", produces = "application/json; charset=utf-8")
    public String login(HttpServletRequest req, HttpServletResponse res, String userCode, String password) {
        User u = new User();
        u.setPassword(password);
        u.setUserCode(userCode);
        return loginService.login(req, u);

    }

    /**
     * 
     * <B>方法名称：退出等</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月13日 上午11:04:43
     * @param req
     * @param res
     * @param userCode
     * @param password void
     */
    @ResponseBody
    @RequestMapping(value = "/loginOut", produces = "application/json; charset=utf-8")
    public void loginOut(HttpServletRequest req, HttpServletResponse res, String userCode, String password) {

    }

}
