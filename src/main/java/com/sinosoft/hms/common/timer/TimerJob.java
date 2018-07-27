/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.common.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinosoft.hms.modules.number.model.HotelNumber;
import com.sinosoft.hms.modules.number.service.NumberService;

/**
 * <B>系统名称：饭店管理</B><BR>
 * <B>模块名称：定时器</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月17日
 */
@Component
public class TimerJob {
    @Autowired
    NumberService numberService;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void run() {
        logger.info("+++++++++++++++++执行定时任务，将牌号归零+++++++++++++++");
        HotelNumber num = numberService.getModel();
        num.setOrderNumber("1");
        numberService.update(num);
    }

}
