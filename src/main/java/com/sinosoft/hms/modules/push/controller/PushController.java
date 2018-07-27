/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.push.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinosoft.hms.modules.activity.model.Activity;
import com.sinosoft.hms.modules.push.service.PushService;

/**
 * <B>系统名称：饭店管理</B><BR>
 * <B>模块名称：推送管理</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月10日
 */
@RestController
@RequestMapping("push")
public class PushController {
    @Autowired
    PushService pushService;

    /**
     * 
     * <B>方法名称：发送短信</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月12日 下午12:02:55
     * @return String
     */
    @RequestMapping("sendMessage")
    public String sendMessage() {
        Activity a = new Activity();
        return pushService.sendMessage(a);
    }
}
