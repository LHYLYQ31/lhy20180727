/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.ums.common.enums;

/**
 * <B>系统名称：ums</B><BR>
 * <B>模块名称：结果异常返回值枚举</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年7月10日
 */
public enum ResultEnum {
    UNKNOW_ERROR(-1, "未知错误"),
    PARAMS_IS_NULL_ERROR(-2, "参数为空"),
    FILE_NO_EXIST_ERROR(-3, "文件不存在");

    private Integer code;
    private String msg;

    /**
     * <B>构造方法</B><BR>
     * 
     * @param code
     * @param msg
     */
    private ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
