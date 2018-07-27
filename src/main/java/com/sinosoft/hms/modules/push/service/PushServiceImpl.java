/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.push.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.sinosoft.hms.common.constant.GlobalNames;
import com.sinosoft.hms.modules.activity.model.Activity;
import com.sinosoft.hms.modules.member.model.Member;
import com.sinosoft.hms.modules.member.service.MemberService;

import net.sf.json.JSONObject;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月10日
 */
@Service
public class PushServiceImpl implements PushService {
    @Autowired
    MemberService memberService;
    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    /**
     * <B>方法名称：sendMessage</B><BR>
     * <B>概要说明：发送短信</B><BR>
     * 
     * @see com.sinosoft.hms.modules.push.service.PushService#sendMessage()
     */
    @Override
    public String sendMessage(Activity a) {
        JSONObject json = new JSONObject();
        try {
            //初始化短信服务的接口
            IAcsClient acsClient = initData();
            //构建 SendSmsRequest 
            SendSmsRequest request = getSendSmsRequest(a);
            //hint 此处可能会抛出异常，注意catch
            SendSmsResponse response = acsClient.getAcsResponse(request);
            if ("ok".equals(response.getCode())) {
                json.put("status", "1");
                json.put("msg", "发送成功");
            }
            else {
                json.put("status", "1");
                json.put("msg", "发送失败；原因：" + response.getMessage() + "返回的code：" + response.getCode());
                json.put("msg", "发送失败");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return json.toString();
    }

    /**
     * 
     * <B>方法名称：初始短信接口获得IAcsClient</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月12日 上午11:55:38
     * @return
     * @throws ClientException IAcsClient
     */
    public IAcsClient initData() throws ClientException {
        String accessKeyId = GlobalNames.ACCESS_KEY_ID; //"LTAIFOZiMwmqUpim";
        String accessKeySecret = GlobalNames.ACCESS_KEY_SECRET; //"W8m9Bj019XlmTveAWFmeYtklPWhb7N";
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        return acsClient;
    }

    /**
     * 
     * <B>方法名称：getSendSmsRequest</B><BR>
     * <B>概要说明：获得SendSmsRequest</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月12日 上午11:56:29
     * @return SendSmsRequest
     */
    public SendSmsRequest getSendSmsRequest(Activity a) {
        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号 多条是使用逗号分隔
        request.setPhoneNumbers(getPhones());
        //必填:短信签名-可在短信控制台中找到
        try {
            request.setSignName(URLDecoder.decode(GlobalNames.SIGN_NAME, "utf-8"));
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //必填:短信模板-可在短信控制台中找到"SMS_130917490"
        if ("1".equals(a.getTemplate())) {
            request.setTemplateCode(GlobalNames.TEMPLATE_CODE);
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            request.setTemplateParam(getMessage(a));
        }
        else if ("2".equals(a.getTemplate())) {
            request.setTemplateCode(GlobalNames.TEMPLATE_CODE);
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            request.setTemplateParam(getMessage(a));
        }

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
        return request;
    }

    /**
     * 
     * <B>方法名称：根据短信模板构建发送短信的内容</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月12日 上午11:58:44
     * @return String
     */
    public String getMessage(Activity a) {
        JSONObject json = new JSONObject();
        json.put("name", a.getStore());
        json.put("discount", a.getDiscount());
        return json.toString();
    }

    /**
     * 
     * <B>方法名称：查询会员手机号</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月12日 下午1:37:52
     * @return String
     */
    public String getPhones() {
        List<Member> list = memberService.list();
        String phones = "";
        if (list != null && list.size() > 0) {
            for (Member member : list) {
                phones += member.getMemberPhone();
                phones += ",";
            }
        }
        if (phones.lastIndexOf(",") == phones.length()) {
            phones = phones.substring(0, phones.lastIndexOf(","));
        }

        return phones;
    }
}
