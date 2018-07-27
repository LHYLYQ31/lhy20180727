/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.income.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.hms.modules.income.model.Income;
import com.sinosoft.hms.modules.order.model.Order;
import com.sinosoft.hms.modules.order.service.OrderService;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月15日
 */
@Service
public class IncomeServiceImpl implements IncomeService {
    @Autowired
    OrderService orderService;

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.income.service.IncomeService#list(java.lang.String, java.lang.String)
     */
    @Override
    public List<Income> list(String yearTime, String monthTime, String dayTime) {
        List<Income> list = new ArrayList<>();
        SimpleDateFormat daySDF = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat monthSDF = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat yearSDF = new SimpleDateFormat("yyyy");
        if (StringUtils.isBlank(yearTime)) {
            yearTime = yearSDF.format(new Date());
        }
        if (StringUtils.isBlank(dayTime)) {
            dayTime = daySDF.format(new Date());
        }
        if (StringUtils.isBlank(monthTime)) {
            monthTime = monthSDF.format(new Date());
        }
        list.add(getIncome(yearTime, "y"));
        list.add(getIncome(monthTime, "m"));
        list.add(getIncome(dayTime, "d"));
        return list;
    }

    /**
     * 
     * <B>方法名称：getIncome</B><BR>
     * <B>概要说明：获取指定时间的总收入</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月15日 下午5:54:24
     * @param time
     * @return Income
     */
    public Income getIncome(String time, String type) {
        Income income = new Income();
        income.setId(UUID.randomUUID().toString().replace("-", ""));
        income.setTime(time);
        Float sum = 0.0f;
        List<Order> list = orderService.list(time);
        for (Order o : list) {
            sum += Float.valueOf(o.getTotalPrice());
        }
        income.setIncome(sum.toString());
        income.setType(type);
        return income;
    }

}
