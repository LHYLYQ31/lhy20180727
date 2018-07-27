/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.user.service;

import com.sinosoft.hms.common.util.Page;
import com.sinosoft.hms.modules.user.model.User;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年1月23日
 */
public interface UserService {
    /**
     * 
     * <B>方法名称：根据用户一直信息查询其他信息</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年1月23日 上午9:47:41
     * @param u 用户信息实体
     * @return User
     */
    public User getModle(User u);

    /**
     * 
     * <B>方法名称：保存用户信息</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年1月23日 上午9:49:09
     * @param u
     * @return User
     */
    public User save(User u);

    /**
     * 
     * <B>方法名称：分页查询用户列表</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年1月24日 上午9:01:47
     * @param pageSize 当前页数
     * @param rowNum 每页条数
     * @param userName 用户名
     * @return Page
     */
    public Page list(int pageSize, int rowNum, String userName);

    /**
     * 
     * <B>方法名称：删除用户操作</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月13日 下午5:18:00
     * @param userId
     * @return String
     */
    String delete(Long userId);

}
