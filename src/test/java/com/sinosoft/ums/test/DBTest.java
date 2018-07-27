/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.ums.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.dubbo.common.json.JSONObject;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年7月12日
 */
public class DBTest {

    /**
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年7月12日 上午8:48:31
     * @param args void
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
        Date startDate = sdf.parse("2018-7-11");
        Calendar ct = Calendar.getInstance();
        ct.setTime(startDate);
        ct.add(Calendar.DATE, +1);
        System.err.println(sdf.format(ct.getTime()));

        JSONObject json = new JSONObject();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("a", "1");
        map.put("b", "2");

        List<Map> list = new ArrayList<Map>();
        list.add(map);
        //        
        //        
        //        
        //        
        //        for (Map map1 : list) {
        //            Iterator it = map1.entrySet().iterator();
        //            while (it.hasNext()) {
        //                Map.Entry entry = (Map.Entry) it.next();
        //                Object key = entry.getKey();
        //                Object value = entry.getValue();
        //                System.out.println("key=" + key + " value=" + value);
        //            }
        //        }
    }

}
