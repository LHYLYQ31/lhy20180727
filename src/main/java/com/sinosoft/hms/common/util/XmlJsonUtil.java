/**
 * Copyright 2017 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.common.util;

import org.jdom.Document;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：xml与json转换工具类</B><BR>
 * 
 * @author 中科软科技 xiece
 * @since 2017年9月20日
 */
public class XmlJsonUtil {

    /**
     * <B>方法名称：xml2json</B><BR>
     * <B>概要说明：将xml字符串<STRONG>转换</STRONG>为JSON字符串</B><BR>
     * 
     * @param xmlString xml字符串
     * @return JSON<STRONG>对象字符串</STRONG>
     */
    public static String xml2json(String xmlString) {
        XMLSerializer xmlSerializer = new XMLSerializer();
        xmlSerializer.setTrimSpaces(true);
        JSON json = xmlSerializer.read(xmlString);
        return json.toString(0, 0);
    }

    /**
     * <B>方法名称：xml2json</B><BR>
     * <B>概要说明：将xmlDocument<STRONG>转换</STRONG>为JSON<STRONG>对象</STRONG></B><BR>
     * 
     * @param xmlDocument XML Document
     * @return JSON<STRONG>对象字符串</STRONG>
     */
    public static String xml2json(Document xmlDocument) {
        return xml2json(xmlDocument.toString());
    }

    /**
     * <B>方法名称：json2xml</B><BR>
     * <B>概要说明：JSON(数组)字符串<STRONG>转换</STRONG>成XML字符串</B><BR>
     * 
     * @param jsonString
     * @return
     */
    public static String json2xml(String jsonString) {
        XMLSerializer xmlSerializer = new XMLSerializer();
        return xmlSerializer.write(JSONSerializer.toJSON(jsonString));
        // return xmlSerializer.write(JSONArray.fromObject(jsonString));//这种方式只支持JSON数组
    }

}