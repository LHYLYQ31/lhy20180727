/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.user.service;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.hms.common.constant.GlobalNames;
import com.sinosoft.hms.common.util.AesUtil2;
import com.sinosoft.hms.modules.user.model.User;

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
@Service
public class LoginServiceImpl implements LoginService {
    private String key = GlobalNames.AES_KEY;
    @Autowired
    UserService userService;

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.pushmanager.login.service.loginService#login(java.lang.String, java.lang.String)
     */
    @Override
    public String login(HttpServletRequest req, User u) {
        JSONObject json = new JSONObject();
        if (StringUtils.isBlank(u.getUserCode()) || StringUtils.isBlank(u.getUserCode())) {
            json.put("status", 0);
            json.put("msg", "登录失败");
        }
        else {

            User result = userService.getModle(u);
            if (result == null) {
                json.put("status", 0);
                json.put("msg", "登录失败");
            }
            else {
                String ticket = getTicket(result.getUserId());
                HttpSession session = req.getSession();
                session.setAttribute("userId", result.getUserId());
                session.setAttribute("ticket", ticket);
                json.put("status", 1);
                json.put("msg", "登录成功");
                json.put("ticket", ticket);
                json.put("userId", result.getUserId());
            }
        }

        return json.toString();
    }

    /**
     * 
     * <B>方法名称：生成一个秘钥</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年1月24日 上午11:47:06
     * @param userid
     * @return String
     */
    private String getTicket(Long userid) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        Long time = System.currentTimeMillis();
        String ticket = userid + "-" + time;
        return AesUtil2.cbcEncrypt(ticket, key.getBytes());
    }
}
