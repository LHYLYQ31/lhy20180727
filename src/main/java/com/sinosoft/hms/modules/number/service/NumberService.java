/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.number.service;

import com.sinosoft.hms.modules.number.model.HotelNumber;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月18日
 */
public interface NumberService {

    /**
     * 
     * <B>方法名称：save</B><BR>
     * <B>概要说明：保存和更新方法</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月18日 下午2:21:47
     * @param num
     * @return Number
     */
    HotelNumber update(HotelNumber num);

    /**
     * 
     * <B>方法名称：getModel</B><BR>
     * <B>概要说明：查询序列号</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月18日 下午2:27:25
     * @return Number
     */
    HotelNumber getModel();

}
