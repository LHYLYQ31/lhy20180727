/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.order.service;

import java.util.List;

import com.sinosoft.hms.common.util.Page;
import com.sinosoft.hms.modules.order.model.Order;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月10日
 */
public interface OrderService {
    /**
     * 
     * <B>方法名称：save</B><BR>
     * <B>概要说明：保存</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月11日 上午11:54:40
     * @param order
     * @return Order
     */
    Order save(Order order);

    /**
     * 
     * <B>方法名称：getModel</B><BR>
     * <B>概要说明：查询订单</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月11日 上午11:55:04
     * @param order
     * @return Order
     */
    Order getModel(Order order);

    /**
     * 
     * <B>方法名称：删除订单</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月11日 上午11:55:21
     * @param id
     * @return Order
     */
    Boolean delete(Long id);

    /**
     * 
     * <B>方法名称：修改订单</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月11日 上午11:55:37
     * @param order
     * @return Order
     */
    Boolean update(Order order);

    /**
     * 
     * <B>方法名称：查询订单列表</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月11日 上午11:55:54
     * @param pageNum
     * @param pageSize
     * @param tableNumber
     * @return Page
     */
    Page list(Integer pageNum, Integer pageSize, String tableNumber, String createTime);

    /**
     * 
     * <B>方法名称：list</B><BR>
     * <B>概要说明：根据时间查询指定时间的订单</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月15日 下午5:46:04
     * @param time 指定时间
     * @return List<Order>
     */
    List<Order> list(String time);

}
