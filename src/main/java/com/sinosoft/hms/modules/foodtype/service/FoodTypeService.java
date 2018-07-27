/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.foodtype.service;

import java.util.List;

import com.sinosoft.hms.modules.foodtype.model.FoodType;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月14日
 */
public interface FoodTypeService {
    /**
     * 
     * <B>方法名称：list</B><BR>
     * <B>概要说明：查询类型列表</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月14日 上午9:54:38
     * @return List<FoodType>
     */
    List<FoodType> list();

    /**
     * 
     * <B>方法名称：save</B><BR>
     * <B>概要说明：保存</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月14日 上午9:54:59
     * @return FoodType
     */
    FoodType save(FoodType ft);

    /**
     * 
     * <B>方法名称：删除</B><BR>
     * <B>概要说明：delete</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月14日 上午9:55:21
     * @param id
     * @return Boolean
     */
    Boolean delete(Long id);

    /**
     * 
     * <B>方法名称：update</B><BR>
     * <B>概要说明：修改类型</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月14日 上午9:55:46
     * @param ft
     * @return Boolean
     */
    Boolean update(FoodType ft);

    /**
     * 
     * <B>方法名称：查询获取类型</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月14日 上午9:56:03
     * @param ft
     * @return FoodType
     */
    FoodType getModel(FoodType ft);

}
