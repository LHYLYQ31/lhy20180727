/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <B>系统名称：饭店管理系统</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：用户</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年1月23日
 */
@Entity
@Table(name = "HOTEL_USER")
public class User {
    @Id
    /*
     * @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payablemoney_seq")
     * @SequenceGenerator(name = "payablemoney_seq", sequenceName = "seq_payment")
     */
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId")
    //用户id
    private Long userId;
    //登录人姓名
    private String userName;
    //登录密码
    private String password;
    //登录账户
    private String userCode;
    //排列顺序
    private Long userOrder;
    //添加时间
    private String createTime;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(Long userOrder) {
        this.userOrder = userOrder;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

}
