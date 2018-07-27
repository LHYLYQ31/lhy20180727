/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.order.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.hms.common.util.HqlHelper;
import com.sinosoft.hms.common.util.Page;
import com.sinosoft.hms.modules.order.dao.OrderDao;
import com.sinosoft.hms.modules.order.model.Order;

/**
 * <B>系统名称：饭店管理</B><BR>
 * <B>模块名称：订单管理</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月10日
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.order.service.OrderService#save(com.sinosoft.hms.modules.order.model.Order)
     */
    @Override
    public Order save(Order order) {
        orderDao.saveOrUpdate(order);
        return order;
    }

    /**
     * <B>方法名称：getModel</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.order.service.OrderService#getModel(com.sinosoft.hms.modules.order.model.Order)
     */
    @Override
    public Order getModel(Order order) {
        HqlHelper helper = new HqlHelper(Order.class);
        if (order.getId() != null) {
            helper.addCondition("id=?", order.getId());
        }
        List<Order> list = (List<Order>) orderDao.queryList(helper);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        else {
            return null;
        }
    }

    /**
     * <B>方法名称：delete</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.order.service.OrderService#delete(java.lang.String)
     */
    @Override
    public Boolean delete(Long id) {
        orderDao.deleteById(id);
        return true;
    }

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.order.service.OrderService#update(com.sinosoft.hms.modules.order.model.Order)
     */
    @Override
    public Boolean update(Order order) {
        orderDao.saveOrUpdate(order);
        return true;
    }

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.order.service.OrderService#list(java.lang.Integer, java.lang.Integer,
     *      java.lang.String)
     */
    @Override
    public Page list(Integer pageNum, Integer pageSize, String tableNumber, String createTime) {
        HqlHelper helper = new HqlHelper(Order.class);
        if (StringUtils.isNotBlank(tableNumber)) {
            helper.addCondition("tableNumber=?", tableNumber);
        }
        if (StringUtils.isNotBlank(createTime)) {
            helper.addCondition("createTime like ?", createTime + "%");
        }
        return helper.buildPageBean(pageNum, pageSize, orderDao);
    }

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.order.service.OrderService#list(java.lang.String)
     */
    @Override
    public List<Order> list(String time) {
        HqlHelper helper = new HqlHelper(Order.class);
        helper.addCondition("createTime like ?", time + "%");
        return (List<Order>) orderDao.queryList(helper);
    }

}
