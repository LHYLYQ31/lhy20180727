/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.common.init;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechUtility;
import com.sinosoft.hms.common.cache.MemoryCache;
import com.sinosoft.hms.common.constant.GlobalNames;
import com.sinosoft.hms.common.util.PropOperate;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年1月31日
 */
public class InitData implements ServletContextListener {
    Logger logger = LoggerFactory.getLogger(InitData.class);

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
        // context销毁时，销毁初始化数据
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
        try {
            String filepath = event.getServletContext().getRealPath("/");
            PropOperate pro = new PropOperate(filepath + "WEB-INF/classes/filterUrl.xml");
            MemoryCache.setFilterList(pro.getFilterUrlList());
            MemoryCache.setNoFilterList(pro.getNoFilterUrlList());
            MemoryCache.setAuthList(pro.getAuthUrlList());
            //初始还科大讯飞的语音
            SpeechUtility.createUtility(SpeechConstant.APPID + "=" + GlobalNames.SPEECH_APPID);
        }
        catch (Exception e) {
            logger.error("加载过滤的URL失败:" + e.getMessage());
        }
    }
}
