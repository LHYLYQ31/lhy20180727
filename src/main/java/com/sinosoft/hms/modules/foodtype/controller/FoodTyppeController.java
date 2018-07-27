/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.foodtype.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinosoft.hms.modules.foodtype.model.FoodType;
import com.sinosoft.hms.modules.foodtype.service.FoodTypeService;

import net.sf.json.JSONObject;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月14日
 */
@RestController
@RequestMapping("foodType")
public class FoodTyppeController {
    @Autowired
    FoodTypeService foodTypeService;

    /**
     * 
     * <B>方法名称：list</B><BR>
     * <B>概要说明：查询类型列表</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月14日 上午9:54:38
     * @return List<FoodType>
     */
    @RequestMapping("list")
    public List<FoodType> list() {
        return foodTypeService.list();
    }

    /**
     * 
     * <B>方法名称：save</B><BR>
     * <B>概要说明：保存</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月14日 上午9:54:59
     * @return FoodType
     */
    @RequestMapping("save")
    public FoodType save(FoodType ft) {
        return foodTypeService.save(ft);
    }

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
    @RequestMapping("delete")
    public String delete(Long id) {
        JSONObject json = new JSONObject();
        if (id != null) {
            if (foodTypeService.delete(id)) {
                json.put("status", "1");
            }
            else {
                json.put("status", "0");
            }
        }
        else {
            json.put("status", "2");
            json.put("msg", "主键id为空");
        }
        return json.toString();

    }

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
    @RequestMapping("getModel")
    public FoodType getModel(Long id) {
        FoodType ft = new FoodType();
        ft.setId(id);
        return foodTypeService.getModel(ft);
    }
}
