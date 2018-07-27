/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.order.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.sinosoft.hms.common.util.Page;
import com.sinosoft.hms.modules.order.model.Order;
import com.sinosoft.hms.modules.order.model.OrderVegetablesType;
import com.sinosoft.hms.modules.order.service.OrderService;
import com.sinosoft.hms.modules.order.service.OrderVegetablesTypeService;
import com.sinosoft.hms.modules.order.vo.OrderInfo;
import com.sinosoft.hms.modules.print.service.PrintService;
import com.sinosoft.hms.modules.push.service.PushService;

import net.sf.json.JSONObject;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月10日
 */
@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderVegetablesTypeService orderVegetablesTypeService;
    @Autowired
    PushService pushService;
    @Autowired
    PrintService printService;

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
    @RequestMapping("save")
    public String save(Order order, String data) {
        JSONObject json = new JSONObject();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
        if (StringUtils.isNotBlank(data) && data.length() > 2) {

            order.setCreateTime(sdf.format(new Date()));
            Order saveOrder = orderService.save(order);
            Map<String, com.alibaba.fastjson.JSONObject> maps = (Map<String, com.alibaba.fastjson.JSONObject>) JSON
                    .parse(data);
            for (Map.Entry<String, com.alibaba.fastjson.JSONObject> entry : maps.entrySet()) {
                String key = entry.getKey().toString();
                com.alibaba.fastjson.JSONObject value = entry.getValue();
                //  String value = entry.getValue().toString();
                OrderVegetablesType ovt = new OrderVegetablesType();
                ovt.setFoodId(key.split("_")[1]);
                ovt.setNum(value.getString("num"));
                ovt.setOrderId(String.valueOf(saveOrder.getId()));
                ovt.setCreateTime(sdf.format(new Date()));
                orderVegetablesTypeService.save(ovt);
            }
            json.put("status", "1");
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    printService.print(saveOrder.getId());
                }
            });
            thread.start();

        }
        else {
            json.put("status", "2");
        }
        return json.toString();
    }

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
    @RequestMapping(value = "getModel", produces = "application/json; charset=utf-8")
    public String getModel(Long id) {
        JSONObject json = new JSONObject();
        Order order = new Order();
        order.setId(id);
        Order selectOrder = orderService.getModel(order);
        if (selectOrder != null) {
            List<OrderInfo> list = orderVegetablesTypeService.list(String.valueOf(selectOrder.getId()));
            json.put("status", "1");
            json.put("children", list);
            json.put("order", selectOrder);
        }
        else {
            json.put("satus", "0");
        }
        return json.toString();
    }

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
    @RequestMapping("delete")
    public String delete(String ovtId, Long orderId) {
        JSONObject json = new JSONObject();
        if (orderId != null) {
            OrderVegetablesType ovt = new OrderVegetablesType();
            ovt.setOrderId(String.valueOf(orderId));
            if (orderVegetablesTypeService.delete(ovt)) {
                orderService.delete(orderId);
                json.put("status", "1");
            }
            else {
                json.put("status", "0");
            }
        }
        else if (StringUtils.isNotBlank(ovtId)) {
            OrderVegetablesType ovt = new OrderVegetablesType();
            ovt.setId(ovtId);
            OrderVegetablesType selectOVT = orderVegetablesTypeService.getModel(ovt);
            orderVegetablesTypeService.delete(ovt);
            Order order = new Order();
            order.setId(Long.valueOf(selectOVT.getOrderId()));
            Order selectOrder = orderService.getModel(order);
            selectOrder.setTotalPrice(orderVegetablesTypeService.getTotlePrice(String.valueOf(selectOrder.getId())));
            orderService.update(selectOrder);
            json.put("status", "1");
        }
        else {
            json.put("status", "0");
        }

        return json.toString();
    }

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
    @RequestMapping("update")
    public String update(Long orderId, String tableNumber, String ovtId, String num, String type) {
        Order selectOrder = null;
        JSONObject json = new JSONObject();
        if ("1".equals(type)) {
            Order order = new Order();
            order.setId(orderId);
            selectOrder = orderService.getModel(order);
            if (StringUtils.isNotBlank(tableNumber)) {

                selectOrder.setTableNumber(tableNumber);
            }
        }
        else if ("2".equals(type)) {
            OrderVegetablesType ovt = new OrderVegetablesType();
            ovt.setId(ovtId);
            OrderVegetablesType selectOVT = orderVegetablesTypeService.getModel(ovt);
            if (selectOVT != null) {
                selectOVT.setNum(num);
                orderVegetablesTypeService.update(selectOVT);
                Order order = new Order();
                order.setId(Long.valueOf(selectOVT.getOrderId()));
                selectOrder = orderService.getModel(order);
                selectOrder.setTotalPrice(orderVegetablesTypeService.getTotlePrice(String.valueOf(selectOVT
                        .getOrderId())));
            }
        }
        //修改总价
        orderService.update(selectOrder);
        json.put("status", "1");
        return json.toString();
    }

    /**
     * 
     * <B>方法名称：查询订单列表</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月11日 上午11:55:54
     * @param page
     * @param pageSize
     * @param tableNumber
     * @return Page
     */
    @RequestMapping("list")
    public Page list(Integer pageNum, Integer pageSize, String tableNumber, String createTime) {
        return orderService.list(pageNum, pageSize, tableNumber, createTime);
    }

}
