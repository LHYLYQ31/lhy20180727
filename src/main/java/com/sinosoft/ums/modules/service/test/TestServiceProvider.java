/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.ums.modules.service.test;

import org.springframework.stereotype.Service;

import com.sinosoft.mms.intercace.device.model.DeviceInteface;
import com.sinosoft.mms.intercace.device.service.DeviceServiceInterface;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年7月11日
 */
@Service
public class TestServiceProvider implements DeviceServiceInterface {

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.mms.intercace.device.service.DeviceServiceInterface#save(com.sinosoft.mms.intercace.device.model.DeviceInteface)
     */
    @Override
    public DeviceInteface save(DeviceInteface device) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.mms.intercace.device.service.DeviceServiceInterface#getModel(java.lang.String)
     */
    public DeviceInteface getModel(String uuId) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.mms.intercace.device.service.DeviceServiceInterface#update(com.sinosoft.mms.intercace.device.model.DeviceInteface)
     */
    @Override
    public Boolean update(DeviceInteface device) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.mms.intercace.device.service.DeviceServiceInterface#getModel(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public DeviceInteface getModel(String uuId, String userId) {
        // TODO Auto-generated method stub
        return null;
    }

}
