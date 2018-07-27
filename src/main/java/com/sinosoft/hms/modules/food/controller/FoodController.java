/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.food.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinosoft.hms.common.util.Page;
import com.sinosoft.hms.modules.attachment.model.Attachment;
import com.sinosoft.hms.modules.attachment.service.AttachmentService;
import com.sinosoft.hms.modules.food.model.Food;
import com.sinosoft.hms.modules.food.service.FoodService;
import com.sinosoft.hms.modules.food.vo.FoodVo;

import net.sf.json.JSONObject;

/**
 * <B>系统名称：饭店管理</B><BR>
 * <B>模块名称：菜品管理</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月11日
 */
@RestController
@RequestMapping("food")
public class FoodController {
    @Autowired
    FoodService foodService;
    @Autowired
    AttachmentService attachmentService;

    @RequestMapping(value = "pageList")
    public Page list(Integer pageNum, Integer pageSize, String name) {
        return foodService.list(pageNum, pageSize, name);
    }

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
    @RequestMapping(value = "save")
    public Food save(Food food, String attId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
        food.setCreateTime(sdf.format(new Date()));
        food.setFoodOrder(1);
        food = foodService.save(food);
        if (StringUtils.isNotBlank(attId)) {
            Attachment attachment = new Attachment();
            attachment.setId(attId);
            attachment = attachmentService.getModel(attachment);
            attachment.setFoodId(food.getId());
            attachmentService.update(attachment);
        }
        return food;
    }

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
    @RequestMapping("list")
    public List<FoodVo> list(Long foodTypeId, String foodName) {
        return foodService.list(foodTypeId, foodName);
    }

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
    @RequestMapping("update")
    public Boolean update(String id, String name, String price, Integer order) {
        Food selcetfood = new Food();
        selcetfood.setId(id);
        Food food = foodService.getModel(selcetfood);
        if (food != null) {
            food.setName(name);
            food.setPrice(price);
            food.setFoodOrder(order);
        }
        return foodService.update(food);
    }

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
    @RequestMapping("model")
    public Food getModel(String id) {
        Food selcetfood = new Food();
        selcetfood.setId(id);
        return foodService.getModel(selcetfood);
    }

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
    @RequestMapping("delete")
    public String delete(String id) {
        JSONObject json = new JSONObject();
        Food selcetfood = new Food();
        selcetfood.setId(id);
        if (foodService.delete(selcetfood)) {
            json.put("status", "1");
        }
        else {
            json.put("status", "0");
        }
        return json.toString();
    }
}
