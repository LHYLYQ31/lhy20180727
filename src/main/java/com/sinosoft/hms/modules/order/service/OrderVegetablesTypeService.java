/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.order.service;

import java.util.List;

import com.sinosoft.hms.modules.order.model.OrderVegetablesType;
import com.sinosoft.hms.modules.order.vo.OrderInfo;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月10日
 */
public interface OrderVegetablesTypeService {
    /**
     * 
     * <B>方法名称：save</B><BR>
     * <B>概要说明：保存</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月11日 下午12:04:22
     * @param ovt
     * @return OrderVegetablesType
     */
    OrderVegetablesType save(OrderVegetablesType ovt);

    /**
     * 
     * <B>方法名称：list</B><BR>
     * <B>概要说明：根据订单id查询子项</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月11日 下午12:03:59
     * @param orderId
     * @return List<OrderVegetablesType>
     */
    List<OrderInfo> list(String orderId);

    /**
     * 
     * <B>方法名称：detete</B><BR>
     * <B>概要说明：根据不同条件删除子项</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月11日 下午12:03:29
     * @param ovt
     * @return Boolean
     */
    Boolean delete(OrderVegetablesType ovt);

    /**
     * 
     * <B>方法名称：update</B><BR>
     * <B>概要说明：修改</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月11日 下午12:03:11
     * @param ovt
     * @return Boolean
     */
    Boolean update(OrderVegetablesType ovt);

    /**
     * 
     * <B>方法名称：getModel</B><BR>
     * <B>概要说明：查询订单详情</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月12日 下午4:33:14
     * @param ovt
     * @return OrderVegetablesType
     */
    OrderVegetablesType getModel(OrderVegetablesType ovt);

    /**
     * 
     * <B>方法名称：根据订单id计算总价</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月12日 下午5:36:11
     * @param orderId
     * @return String
     */
    String getTotlePrice(String orderId);
}
