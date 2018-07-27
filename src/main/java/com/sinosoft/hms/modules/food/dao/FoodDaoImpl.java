/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.food.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.sinosoft.hms.modules.common.dao.BaseDaoImpl;
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
@Repository
public class FoodDaoImpl extends BaseDaoImpl<Food> implements FoodDao {

    @Override
    public List<FoodVo> list(Long foodTypeId, String foodName) {
        Session session = getCurrentSession();
        String hql = "select new com.sinosoft.hms.modules.food.vo.FoodVo(f.id,f.name,f.price,f.createTime,f.foodOrder,ft.name) from Food f,FoodType ft where f.foodTypeId=ft.id";
        if (foodTypeId != null) {
            hql += " and ft.id = ?";
        }
        if (StringUtils.isNotBlank(foodName)) {
            hql += " and f.name like ?";
        }
        hql += " order by f.foodOrder desc";
        Query query = session.createQuery(hql);
        if (foodTypeId != null && StringUtils.isNotBlank(foodName)) {
            query.setParameter(0, foodTypeId);
            query.setParameter(1, "%" + foodName + "%");
        }
        else if (foodTypeId != null && StringUtils.isBlank(foodName)) {
            query.setParameter(0, foodTypeId);
        }
        else if (foodTypeId == null && StringUtils.isNotBlank(foodName)) {
            query.setParameter(0, "%" + foodName + "%");
        }
        List<FoodVo> list = query.list();
        return list;
    }

}
