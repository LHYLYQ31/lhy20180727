/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.ums.common.exception;

import com.sinosoft.ums.common.enums.ResultEnum;

/**
 * <B>系统名称：ums</B><BR>
 * <B>模块名称：自定义异常</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年7月10日
 */
public class UMSException extends RuntimeException {

    private Integer code;

    /**
     * <B>构造方法</B><BR>
     * 
     * @param message
     */
    public UMSException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
