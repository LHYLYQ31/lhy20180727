/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.push.service;

import com.sinosoft.hms.modules.activity.model.Activity;

/**
 * <B>系统名称：饭店管理</B><BR>
 * <B>模块名称：短信管理</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月10日
 */
public interface PushService {
    /**
     * 
     * <B>方法名称：sendMessage</B><BR>
     * <B>概要说明：发送短信</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月12日 上午11:42:39
     * @return String
     */
    String sendMessage(Activity a);
}
