/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.ums.modules.user.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinosoft.ums.common.enums.ResultEnum;
import com.sinosoft.ums.common.exception.UMSException;

/**
 * <B>系统名称：ums</B><BR>
 * <B>模块名称：用户管理</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年7月10日
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/user", produces = "application/json; charset=UTF-8")
    public String say(String name) {
        if (StringUtils.isNotBlank(name)) {
            throw new UMSException(ResultEnum.PARAMS_IS_NULL_ERROR);
        }

        return "你好" + name;
    }

}
