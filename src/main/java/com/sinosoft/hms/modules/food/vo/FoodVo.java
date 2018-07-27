/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.food.vo;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月14日
 */
public class FoodVo {
    private String id;
    //名称
    private String name;
    //单价
    private String price;
    //生成时间
    private String createTime;
    //排列顺序
    private Integer order;
    //种类
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * <B>构造方法</B><BR>
     * 
     * @param id
     * @param name
     * @param price
     * @param createTime
     * @param order
     * @param type
     */
    public FoodVo(String id, String name, String price, String createTime, Integer order, String type) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.createTime = createTime;
        this.order = order;
        this.type = type;
    }

}
