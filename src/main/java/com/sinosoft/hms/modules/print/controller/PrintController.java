/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.print.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinosoft.hms.modules.print.service.PrintService;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月10日
 */
@Controller
@RequestMapping("print")
public class PrintController {
    @Autowired
    PrintService printService;

    /**
     * 
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月10日 下午9:03:45
     * @param order 订单号
     * @param num 数量
     * @param sum 总金额
     * @param practical 实收
     * @param change 找零
     */
    @RequestMapping("print")
    private void print(Long orderId) {
        printService.print(orderId);
    }

}
