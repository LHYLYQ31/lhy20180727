/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package hms.test;

import com.sinosoft.hms.common.constant.GlobalNames;

import net.sf.json.JSONObject;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月12日
 */
public class Test {

    public static void main(String[] args) {
        JSONObject json = new JSONObject();
        System.out.println("/order/save".indexOf("/order/list"));

        GlobalNames.SERIAL_NUMBER = GlobalNames.SERIAL_NUMBER + 1;
        System.out.println(GlobalNames.SERIAL_NUMBER);
    }

}
