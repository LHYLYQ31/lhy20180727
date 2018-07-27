/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.order.vo;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月11日
 */
public class OrderInfo {
    private String id;
    //菜品数量
    private String num;

    //名称
    private String name;
    //单价
    private String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
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

    /**
     * <B>构造方法</B><BR>
     * 
     * @param id
     * @param num
     * @param name
     * @param price
     */
    public OrderInfo(String id, String num, String name, String price) {
        super();
        this.id = id;
        this.num = num;
        this.name = name;
        this.price = price;
    }

}
