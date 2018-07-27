/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.member.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinosoft.hms.common.util.Page;
import com.sinosoft.hms.modules.member.model.Member;
import com.sinosoft.hms.modules.member.service.MemberService;

import net.sf.json.JSONObject;

/**
 * <B>系统名称：饭店管理</B><BR>
 * <B>模块名称：会员管理</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月11日
 */
@RestController
@RequestMapping("member")
public class MemberController {
    @Autowired
    MemberService memberService;

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
    @RequestMapping("save")
    public Member save(Member member) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        member.setCreateTime(sdf.format(new Date()));
        return memberService.save(member);
    }

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
    @RequestMapping("update")
    public Boolean update(Member member) {
        return memberService.update(member);
    }

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
    @RequestMapping("getModel")
    public Member getModel(Integer id, String memberPhone) {
        if (id != null || StringUtils.isNotBlank(memberPhone)) {
            Member member = new Member();
            member.setId(id);
            member.setMemberPhone(memberPhone);
            return memberService.getModel(member);
        }
        else {
            return null;
        }

    }

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
    @RequestMapping("delete")
    public String delete(Integer id) {
        JSONObject json = new JSONObject();
        Member member = new Member();
        member.setId(id);
        if (memberService.delete(member)) {
            json.put("status", "1");
        }
        else {
            json.put("status", "0");
        }
        return json.toString();
    }

    /**
     * 
     * <B>方法名称：list</B><BR>
     * <B>概要说明：分页查询会员信息</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月11日 上午9:43:58
     * @param member
     * @return Boolean
     */
    @RequestMapping("list")
    public Page list(Integer pageNum, Integer pageSize, String memberPhone, String memberName) {
        return memberService.list(pageNum, pageSize, memberPhone, memberName);
    }

}
