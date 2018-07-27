/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.income.service;

import java.util.List;

import com.sinosoft.hms.modules.income.model.Income;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月15日
 */
public interface IncomeService {
    /**
     * 
     * <B>方法名称：list</B><BR>
     * <B>概要说明：查询月收入和日收入</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月15日 下午4:32:09
     * @param monthTime
     * @param dayTime
     * @return List<Income>
     */
    List<Income> list(String yearTime, String monthTime, String dayTime);
}
