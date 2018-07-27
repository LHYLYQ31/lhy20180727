/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.speech.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <B>系统名称：hms管理系统</B><BR>
 * <B>模块名称：语音管理</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年5月25日
 */
public interface SpeechSynthesizeService {
    /**
     * 
     * <B>方法名称：speechSynthesize</B><BR>
     * <B>概要说明：在线转成语音文件</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年5月25日 上午11:28:51
     * @param text 需要转的文本
     * @return String
     */
    String speechSynthesize(String text, HttpServletRequest request, HttpServletResponse response);

}
