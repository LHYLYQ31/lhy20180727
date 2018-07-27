/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.activity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinosoft.hms.modules.activity.model.Activity;
import com.sinosoft.hms.modules.activity.service.ActivityService;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月17日
 */
@RestController
@RequestMapping("activity")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    /**
     * 
     * <B>方法名称：save</B><BR>
     * <B>概要说明：新建活动</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月17日 下午3:56:44
     * @param a
     * @return String
     */
    @RequestMapping("save")
    public String save(Activity a) {
        return activityService.save(a);
    }

}
