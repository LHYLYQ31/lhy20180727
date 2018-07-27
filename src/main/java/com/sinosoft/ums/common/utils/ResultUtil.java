/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.ums.common.utils;

import com.sinosoft.ums.common.result.Result;

/**
 * <B>系统名称：ums</B><BR>
 * <B>模块名称：返回结果工具类</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年7月10日
 */
public class ResultUtil {
    /**
     * 
     * <B>方法名称：success</B><BR>
     * <B>概要说明：有参成功响应</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年7月10日 上午9:37:02
     * @param obj
     * @return Result
     */
    public static Result success(Object obj) {
        Result<Object> result = new Result<Object>();
        result.setCode(200);
        result.setMsg("成功");
        result.setData(obj);
        return result;
    }

    /**
     * 
     * <B>方法名称：success</B><BR>
     * <B>概要说明：无参成功响应</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年7月10日 上午9:37:27
     * @return Result
     */
    public static Result success() {
        return success(null);
    }

    /**
     * 
     * <B>方法名称：error</B><BR>
     * <B>概要说明：错误响应</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年7月10日 上午9:38:20
     * @param code
     * @param msg
     * @return Result
     */
    public static Result error(Integer code, String msg) {
        Result<Object> result = new Result<Object>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
