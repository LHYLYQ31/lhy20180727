/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.user.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.hms.common.util.HqlHelper;
import com.sinosoft.hms.common.util.Page;
import com.sinosoft.hms.modules.user.dao.UserDao;
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
@Service
public class UserServiceImpl implements UserService {
    private static Log log = LogFactory.getLog(UserServiceImpl.class);
    @Autowired
    UserDao userDao;

    /**
     * <B>方法名称：查询用户信息</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.pushmanager.login.service.UserService#getModle(com.sinosoft.pushmanager.login.model.User)
     */
    @Override
    public User getModle(User u) {
        User result = new User();
        System.out.println(u.getPassword());
        if (u != null) {
            HqlHelper helper = new HqlHelper(User.class);
            if (u.getUserId() != null) {

                helper.addCondition("userId=?", u.getUserId());
            }
            if (StringUtils.isNotBlank(u.getUserCode())) {
                helper.addCondition("userCode=?", u.getUserCode());
            }
            if (StringUtils.isNotBlank(u.getPassword())) {
                helper.addCondition("password=?", u.getPassword());
            }
            List<User> list = (List<User>) userDao.queryList(helper);
            if (!list.isEmpty() && list.size() == 1) {
                result = list.get(0);
            }
            else {
                log.info("查询用户信息是多条数据或该用户不存在");
                log.debug("查询用户信息是多条数据或该用户不存在,请查询库");
            }
        }
        return result;
    }

    /**
     * <B>方法名称：注册用户</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.pushmanager.login.service.UserService#save(com.sinosoft.pushmanager.login.model.User)
     */
    @Override
    public User save(User u) {
        HqlHelper helper = new HqlHelper(User.class);
        userDao.save(u);
        return u;
    }

    /**
     * <B>方法名称：分页查询用户列表</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.pushmanager.login.service.UserService#list(int, int, java.lang.String)
     */
    @Override
    public Page list(int pageSize, int rowNum, String userName) {
        HqlHelper helper = new HqlHelper(User.class);
        if (StringUtils.isNotBlank(userName)) {
            helper.addCondition("userName like ?", "%" + userName + "%");
        }
        helper.addOrder("userOrder", true);
        return helper.buildPageBean(pageSize, rowNum, userDao);
    }

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.user.service.UserService#delete(java.lang.Long)
     */
    @Override
    public String delete(Long userId) {
        userDao.deleteById(userId);
        return "success";
    }

}
