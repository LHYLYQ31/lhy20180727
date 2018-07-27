/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.print.service;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月10日
 */
public interface PrintService {
    /**
     * 
     * <B>方法名称：print</B><BR>
     * <B>概要说明：后台打印</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月11日 下午7:58:07
     * @param orderId void
     */
    void print(Long orderId);

}
