/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.food.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.hms.common.util.HqlHelper;
import com.sinosoft.hms.common.util.Page;
import com.sinosoft.hms.modules.food.dao.FoodDao;
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
@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    FoodDao foodDao;

    /**
     * <B>方法名称：save</B><BR>
     * <B>概要说明：保存</B><BR>
     * 
     * @see com.sinosoft.hms.modules.food.service.FoodService#save(com.sinosoft.hms.modules.food.model.Food)
     */
    @Override
    public Food save(Food food) {
        foodDao.saveOrUpdate(food);
        return food;
    }

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.food.service.FoodService#list(java.lang.String)
     */
    @Override
    public List<FoodVo> list(Long foodTypeId, String foodName) {
        //        HqlHelper helper = new HqlHelper(Food.class);
        //        if (StringUtils.isNotBlank(name)) {
        //            helper.addCondition("name like ?", "%" + name + "%");
        //        }
        //        helper.addOrder("order", false);
        return foodDao.list(foodTypeId, foodName);
    }

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.food.service.FoodService#update(com.sinosoft.hms.modules.food.model.Food)
     */
    @Override
    public Boolean update(Food food) {
        foodDao.saveOrUpdate(food);
        return true;
    }

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.food.service.FoodService#getModel(com.sinosoft.hms.modules.food.model.Food)
     */
    @Override
    public Food getModel(Food food) {
        HqlHelper helper = new HqlHelper(Food.class);
        if (StringUtils.isNotBlank(food.getId())) {
            helper.addCondition("id= ?", food.getId());
        }
        List<Food> list = (List<Food>) foodDao.queryList(helper);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        else {
            return null;
        }
    }

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.food.service.FoodService#delete(com.sinosoft.hms.modules.food.model.Food)
     */
    @Override
    public Boolean delete(Food food) {
        foodDao.delete(food);
        return true;
    }

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.food.service.FoodService#list(java.lang.Integer, java.lang.Integer,
     *      java.lang.String)
     */
    @Override
    public Page list(Integer pageNum, Integer pageSize, String name) {
        HqlHelper helper = new HqlHelper(Food.class);
        if (StringUtils.isNotBlank(name)) {
            helper.addCondition("name like ?", "%" + name + "%");
        }
        return helper.buildPageBean(pageNum, pageSize, foodDao);
    }

}
