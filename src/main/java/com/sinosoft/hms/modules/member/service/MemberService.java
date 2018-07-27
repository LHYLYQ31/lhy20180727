/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.member.service;

import java.util.List;

import com.sinosoft.hms.common.util.Page;
import com.sinosoft.hms.modules.member.model.Member;

/**
 * <B>系统名称：饭店管理系统</B><BR>
 * <B>模块名称：会员管理</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月10日
 */
public interface MemberService {
    /**
     * 
     * <B>方法名称：save</B><BR>
     * <B>概要说明：新增会员</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月11日 上午9:41:13
     * @param member 会员实体类
     * @return Member
     */
    Member save(Member member);

    /**
     * 
     * <B>方法名称：update</B><BR>
     * <B>概要说明：修改会员信息</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月11日 上午9:42:13
     * @param member
     * @return Boolean
     */
    Boolean update(Member member);

    /**
     * 
     * <B>方法名称：getModel</B><BR>
     * <B>概要说明：根据条件查询会议信息</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月11日 上午9:42:54
     * @param member
     * @return Member
     */
    Member getModel(Member member);

    /**
     * 
     * <B>方法名称：delete</B><BR>
     * <B>概要说明：删除会员信息</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月11日 上午9:43:58
     * @param member
     * @return Boolean
     */
    Boolean delete(Member member);

    /**
     * 
     * <B>方法名称：查询全部会员的手机号</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月12日 下午1:27:26
     * @return List<Member>
     */
    List<Member> list();

    /**
     * 
     * <B>方法名称：list</B><BR>
     * <B>概要说明：分页查询会员列表</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月13日 下午4:32:39
     * @param pageNum
     * @param pageSize
     * @param memberPhone
     * @param memberName
     * @return Page
     */
    Page list(Integer pageNum, Integer pageSize, String memberPhone, String memberName);
}
