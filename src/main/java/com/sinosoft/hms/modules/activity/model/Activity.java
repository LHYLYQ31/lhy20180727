/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.activity.model;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月17日
 */
public class Activity {
    //验证码
    private String verificationCode;
    //折扣
    private String discount;
    //店名
    private String store;
    //模板
    private String template;

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

}
