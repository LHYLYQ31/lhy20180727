/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.foodtype.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.hms.common.util.HqlHelper;
import com.sinosoft.hms.modules.foodtype.dao.FoodTypeDao;
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
@Service
public class FoodTypeServiceImpl implements FoodTypeService {
    @Autowired
    FoodTypeDao foodTypeDao;

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.foodtype.service.FoodTypeService#list()
     */
    @Override
    public List<FoodType> list() {
        HqlHelper helper = new HqlHelper(FoodType.class);

        return (List<FoodType>) foodTypeDao.queryList(helper);
    }

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.foodtype.service.FoodTypeService#save()
     */
    @Override
    public FoodType save(FoodType ft) {
        foodTypeDao.save(ft);
        return ft;
    }

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.foodtype.service.FoodTypeService#delete(java.lang.Long)
     */
    @Override
    public Boolean delete(Long id) {
        foodTypeDao.deleteById(id);
        return true;
    }

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.foodtype.service.FoodTypeService#update(com.sinosoft.hms.modules.foodtype.model.FoodType)
     */
    @Override
    public Boolean update(FoodType ft) {
        foodTypeDao.saveOrUpdate(ft);
        return true;
    }

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.foodtype.service.FoodTypeService#getModel(com.sinosoft.hms.modules.foodtype.model.FoodType)
     */
    @Override
    public FoodType getModel(FoodType ft) {
        HqlHelper helper = new HqlHelper(FoodType.class);
        if (ft.getId() != null) {
            helper.addCondition("id=?", ft.getId());
        }
        List<FoodType> list = (List<FoodType>) foodTypeDao.queryList(helper);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        else {
            return null;
        }
    }

}
