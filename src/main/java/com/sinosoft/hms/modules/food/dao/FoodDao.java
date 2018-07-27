/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.food.dao;

import java.util.List;

import com.sinosoft.hms.modules.common.dao.BaseDao;
import com.sinosoft.hms.modules.food.model.Food;
import com.sinosoft.hms.modules.food.vo.FoodVo;

/**
 * <B>系统名称：饭店管理</B><BR>
 * <B>模块名称：菜品管理</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月11日
 */
public interface FoodDao extends BaseDao<Food> {

    List<FoodVo> list(Long foodTypeId, String foodName);

}
