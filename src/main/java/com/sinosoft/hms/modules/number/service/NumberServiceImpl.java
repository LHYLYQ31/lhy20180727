/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.number.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.hms.common.constant.GlobalNames;
import com.sinosoft.hms.modules.number.dao.NumberDao;
import com.sinosoft.hms.modules.number.model.HotelNumber;

/**
 * <B>系统名称：饭店管理</B><BR>
 * <B>模块名称：号码记录管理</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月18日
 */
@Service
public class NumberServiceImpl implements NumberService {
    @Autowired
    NumberDao numberDao;

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.number.service.NumberService#save(java.lang.Number)
     */
    @Override
    public HotelNumber update(HotelNumber num) {
        numberDao.saveOrUpdate(num);
        return num;
    }

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.number.service.NumberService#getModel()
     */
    @Override
    public HotelNumber getModel() {
        String id = GlobalNames.NUMBER_ID;
        return (HotelNumber) numberDao.getById(Long.valueOf(id));
    }

}
