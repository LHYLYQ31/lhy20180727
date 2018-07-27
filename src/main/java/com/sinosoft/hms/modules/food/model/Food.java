/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.food.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * <B>系统名称：饭店管理</B><BR>
 * <B>模块名称：菜品管理</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月11日
 */
@Entity
@Table(name = "HOTEL_FOOD")
public class Food {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    //名称
    private String name;
    //单价
    private String price;
    //生成时间
    private String createTime;
    //排列顺序
    private Integer foodOrder;

    public Integer getFoodOrder() {
        return foodOrder;
    }

    public void setFoodOrder(Integer foodOrder) {
        this.foodOrder = foodOrder;
    }

    //种类
    private Long foodTypeId;

    public Long getFoodTypeId() {
        return foodTypeId;
    }

    public void setFoodTypeId(Long foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

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

}
