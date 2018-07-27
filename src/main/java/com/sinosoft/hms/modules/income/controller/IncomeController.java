/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.income.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinosoft.hms.modules.income.model.Income;
import com.sinosoft.hms.modules.income.service.IncomeService;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月15日
 */
@RestController
@RequestMapping("income")
public class IncomeController {
    @Autowired
    IncomeService incomeService;

    @RequestMapping("list")
    public List<Income> list(String yearTime, String monthTime, String dayTime) {
        return incomeService.list(yearTime, monthTime, dayTime);
    }

}
