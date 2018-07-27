/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.member.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.hms.common.util.HqlHelper;
import com.sinosoft.hms.common.util.Page;
import com.sinosoft.hms.modules.member.dao.MemberDao;
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
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberDao memberDao;

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.member.service.MemberService#save(com.sinosoft.hms.modules.member.model.Member)
     */
    @Override
    public Member save(Member member) {
        memberDao.saveOrUpdate(member);
        return member;
    }

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.member.service.MemberService#update(com.sinosoft.hms.modules.member.model.Member)
     */
    @Override
    public Boolean update(Member member) {
        memberDao.saveOrUpdate(member);
        return true;
    }

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.member.service.MemberService#getModel(com.sinosoft.hms.modules.member.model.Member)
     */
    @Override
    public Member getModel(Member member) {
        HqlHelper helper = new HqlHelper(Member.class);
        if (member.getId() != null) {
            helper.addCondition("id=?", member.getId());
        }
        if (StringUtils.isNotBlank(member.getMemberPhone())) {
            helper.addCondition("memberPhone=?", member.getMemberPhone());
        }
        List<Member> list = (List<Member>) memberDao.queryList(helper);
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
     * @see com.sinosoft.hms.modules.member.service.MemberService#delete(com.sinosoft.hms.modules.member.model.Member)
     */
    @Override
    public Boolean delete(Member member) {
        memberDao.deleteById(member.getId());
        return true;
    }

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.member.service.MemberService#list()
     */
    @Override
    public List<Member> list() {
        HqlHelper helper = new HqlHelper(Member.class);
        List<Member> list = (List<Member>) memberDao.queryList(helper);
        return list;
    }

    /**
     * <B>方法名称：分页查询会员信息</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.member.service.MemberService#list(java.lang.Integer, java.lang.Integer,
     *      java.lang.String, java.lang.String)
     */
    @Override
    public Page list(Integer pageNum, Integer pageSize, String memberPhone, String memberName) {
        HqlHelper helper = new HqlHelper(Member.class);
        if (StringUtils.isNotBlank(memberName)) {
            helper.addCondition("memberName like ?", "%" + memberName + "%");
        }
        if (StringUtils.isNotBlank(memberPhone)) {
            helper.addCondition("memberPhone like ?", "%" + memberPhone + "%");
        }
        return helper.buildPageBean(pageNum, pageSize, memberDao);
    }

}
