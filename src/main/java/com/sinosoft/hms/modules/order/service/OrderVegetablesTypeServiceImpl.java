/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.order.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.hms.modules.order.dao.OrderVegetablesTypeDao;
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
@Service
public class OrderVegetablesTypeServiceImpl implements OrderVegetablesTypeService {
    @Autowired
    OrderVegetablesTypeDao orderVegetablesTypeDao;
    /*
     * @Autowired
     * FoodService foodService;
     */

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.order.service.OrderVegetablesTypeService#save(com.sinosoft.hms.modules.order.model.OrderVegetablesType)
     */
    @Override
    public OrderVegetablesType save(OrderVegetablesType ovt) {
        orderVegetablesTypeDao.saveOrUpdate(ovt);
        return ovt;
    }

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.order.service.OrderVegetablesTypeService#list(java.lang.String)
     */
    @Override
    public List<OrderInfo> list(String orderId) {
        //        HqlHelper helper = new HqlHelper(OrderVegetablesType.class);
        //        helper.addCondition("orderId=?", orderId);
        //        List<OrderVegetablesType> list = (List<OrderVegetablesType>) orderVegetablesTypeDao.queryList(helper);
        //        //orderVegetablesTypeDao.list(orderId)
        //        //        List<OrderInfo> infoList = new ArrayList<>();
        //        //        for (OrderVegetablesType ovt : list) {
        //        //            Food f = new Food();
        //        //            f.setId(ovt.getFoodId());
        //        //            f = foodService.getModel(f);
        //        //            OrderInfo info = new OrderInfo(ovt.getId(), ovt.getNum(), f.getName(), f.getPrice());
        //        //            infoList.add(info);
        //        //        }
        return orderVegetablesTypeDao.list(orderId);
    }

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.order.service.OrderVegetablesTypeService#detete(com.sinosoft.hms.modules.order.model.OrderVegetablesType)
     */
    @Override
    public Boolean delete(OrderVegetablesType ovt) {
        if (StringUtils.isNotBlank(ovt.getOrderId())) {
            String hql = "delete from OrderVegetablesType where orderId=?";
            orderVegetablesTypeDao.delete(hql, new String[] { ovt.getOrderId() });
        }
        if (StringUtils.isNotBlank(ovt.getId())) {
            orderVegetablesTypeDao.deleteById(ovt.getId());
        }
        return true;
    }

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.order.service.OrderVegetablesTypeService#update(com.sinosoft.hms.modules.order.model.OrderVegetablesType)
     */
    @Override
    public Boolean update(OrderVegetablesType ovt) {
        orderVegetablesTypeDao.update(ovt);
        return true;
    }

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.order.service.OrderVegetablesTypeService#getModel(com.sinosoft.hms.modules.order.model.OrderVegetablesType)
     */
    @Override
    public OrderVegetablesType getModel(OrderVegetablesType ovt) {
        if (StringUtils.isNotBlank(ovt.getId())) {
            return (OrderVegetablesType) orderVegetablesTypeDao.getById(ovt.getId());
        }
        else {
            return null;
        }
    }

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.order.service.OrderVegetablesTypeService#getTotlePrice(java.lang.String)
     */
    @Override
    public String getTotlePrice(String orderId) {
        List<OrderInfo> list = list(orderId);
        Float total = 0.0f;
        for (OrderInfo info : list) {
            total = total + Float.valueOf(info.getPrice()) * Float.valueOf(info.getNum());
        }
        return total.toString();
    }

}
