/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.activity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.hms.modules.activity.model.Activity;
import com.sinosoft.hms.modules.push.service.PushService;

/**
 * <B>系统名称：饭店管理</B><BR>
 * <B>模块名称：新建活动</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月17日
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    PushService pushService;

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.activity.service.ActivityService#save(com.sinosoft.hms.modules.activity.model.Activity)
     */
    @Override
    public String save(Activity a) {

        return pushService.sendMessage(a);
    }

}
