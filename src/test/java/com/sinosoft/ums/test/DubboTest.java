/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.ums.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sinosoft.ums.modules.user.contant.UserConfig1;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年7月12日
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DubboTest {
    @Autowired
    UserConfig1 d;

    @Test
    public void testFindAll() {
        System.out.println(d.getName());
        System.out.println(d.getAge());
        System.out.println(d.getPhone());
        //service.findAll().forEach(salesman -> System.out.println(salesman.toString()));
    }
}
