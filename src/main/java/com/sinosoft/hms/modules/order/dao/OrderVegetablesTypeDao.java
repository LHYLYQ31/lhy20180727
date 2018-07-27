/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.order.dao;

import java.util.List;

import com.sinosoft.hms.modules.common.dao.BaseDao;
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
public interface OrderVegetablesTypeDao extends BaseDao<OrderVegetablesType> {
    /**
     * 
     * <B>方法名称：list</B><BR>
     * <B>概要说明：根据订单查询菜品的详情信息</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月11日 下午9:59:51
     * @param orderId
     * @return List<OrderInfo>
     */
    List<OrderInfo> list(String orderId);

}
