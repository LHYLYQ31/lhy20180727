/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.food.service;

import java.util.List;

import com.sinosoft.hms.common.util.Page;
import com.sinosoft.hms.modules.food.model.Food;
import com.sinosoft.hms.modules.food.vo.FoodVo;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月11日
 */
public interface FoodService {
    /**
     * 
     * <B>方法名称：save</B><BR>
     * <B>概要说明：保存菜品</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月11日 上午10:00:54
     * @param food
     * @return Food
     */
    Food save(Food food);

    /**
     * 
     * <B>方法名称：list</B><BR>
     * <B>概要说明：查询所有</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月11日 上午10:04:02
     * @param name
     * @return List<Food>
     */
    List<FoodVo> list(Long foodTypeId, String foodName);

    /**
     * 
     * <B>方法名称：update</B><BR>
     * <B>概要说明：修改</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月11日 上午10:05:16
     * @param food
     * @return Boolean
     */
    Boolean update(Food food);

    /**
     * 
     * <B>方法名称：getModel</B><BR>
     * <B>概要说明：查询菜品</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月11日 上午10:08:54
     * @param food
     * @return Food
     */
    Food getModel(Food food);

    /**
     * 
     * <B>方法名称：delete</B><BR>
     * <B>概要说明：删除菜品</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月11日 上午10:09:48
     * @param food
     * @return Boolean
     */
    Boolean delete(Food food);

    Page list(Integer pageNum, Integer pageSize, String name);

}
