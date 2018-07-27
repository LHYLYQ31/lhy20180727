/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.user.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinosoft.hms.common.util.Page;
import com.sinosoft.hms.modules.common.controller.BasicController;
import com.sinosoft.hms.modules.user.model.User;
import com.sinosoft.hms.modules.user.service.UserService;

import net.sf.json.JSONObject;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年1月24日
 */
@Controller
@RequestMapping("/user")
public class UserController extends BasicController {
    @Autowired
    UserService userService;

    /**
     * 
     * <B>方法名称：根据用户一直信息查询其他信息</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年1月23日 上午9:47:41
     * @param u 用户信息实体
     * @return User
     */
    @ResponseBody
    @RequestMapping(value = "/getModel")
    public User getModel(HttpServletRequest req, Long userId, String userName, String userCode) {
        if (userId == null) {
            userId = Long.valueOf(getUserId(req));
        }
        User u = new User();
        u.setUserId(userId);
        u.setUserName(userName);
        u.setUserName(userCode);
        return userService.getModle(u);
    }

    /**
     * 
     * <B>方法名称：保存用户信息</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年1月23日 上午9:49:09
     * @param u
     * @return User
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public User save(User u, String md5_password, HttpServletRequest req) {
        if ("1".equals(getUserId(req))) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            u.setCreateTime(sdf.format(new Date()));
            u.setPassword(md5_password);
            return userService.save(u);
        }
        else {
            return new User();
        }

    }

    /**
     * 
     * <B>方法名称：分页查询用户列表</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年1月24日 上午9:01:47
     * @param pageSize 当前页数
     * @param rowNum 每页条数
     * @param userName 用户名
     * @return Page
     */
    @ResponseBody
    @RequestMapping(value = "/list")
    public Page list(int pageSize, int rowNum, String userName) {
        return userService.list(pageSize, rowNum, userName);
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public String delete(Long userId, HttpServletRequest req) {
        JSONObject json = new JSONObject();
        if (1 != userId) {

            if ("1".equals(getUserId(req))) {
                if ("success".equals(userService.delete(userId))) {
                    json.put("status", "1");
                }
                else {
                    json.put("status", "0");
                }
            }
            else {
                json.put("status", "2");
            }

        }
        else {
            json.put("status", "3");
        }
        return json.toString();
    }

}
