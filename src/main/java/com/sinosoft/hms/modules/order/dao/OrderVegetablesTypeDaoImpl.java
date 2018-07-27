/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.order.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.sinosoft.hms.modules.common.dao.BaseDaoImpl;
import com.sinosoft.hms.modules.order.model.OrderVegetablesType;
import com.sinosoft.hms.modules.order.vo.OrderInfo;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月10日
 */
@Repository
public class OrderVegetablesTypeDaoImpl extends BaseDaoImpl<OrderVegetablesType> implements OrderVegetablesTypeDao {

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.order.dao.OrderVegetablesTypeDao#list(java.lang.String)
     */
    @Override
    public List<OrderInfo> list(String orderId) {
        Session session = getCurrentSession();
        String hql = "select new com.sinosoft.hms.modules.order.vo.OrderInfo(ovc.id,ovc.num,f.name,f.price) from OrderVegetablesType ovc,Food f where ovc.foodId=f.id ";
        hql += "and ovc.orderId=?";
        Query query = session.createQuery(hql);
        query.setParameter(0, orderId);

        return query.list();
    }

}
