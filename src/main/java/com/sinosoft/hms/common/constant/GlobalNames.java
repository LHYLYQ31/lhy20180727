package com.sinosoft.hms.common.constant;

import com.sinosoft.hms.common.util.ConfigProperties;

public class GlobalNames {
    /**
     * cookie存活时间 (秒)
     */
    public static String COOKIE_INVALID_TIME = "COOKIE_INVALID_TIME";
    // #cookie跨域
    public static String DOMAIN = "DOMAIN";
    public static String COOKIENAME = "userid";
    //AES编码和解码秘钥AES_KEY
    public static String AES_KEY = ConfigProperties.getPropertyValue("AES_KEY");
    // 默认每页的条数
    public static Integer DEFAULT_PAGE_SIZE = 10;
    public static String SUCESS_STARUS = "1";

    /**
     * 每页默认条数
     */
    public static String PAGE_SIZE = "PAGE_SIZE";
    // 用户对象
    public static final String USER = "APP_USER";
    /**
     * 文件管理参数
     */
    //文件存储路径
    public static final String FILE_PATH = ConfigProperties.getPropertyValue("FILE_PATH");
    //语音文件保存路径
    public static final String SPEECH_FILE_URL = ConfigProperties.getPropertyValue("SPEECH_FILE_URL");
    //默认语音路径
    public static final String DEFUALT_SPEECH_FILE_URL = ConfigProperties.getPropertyValue("DEFUALT_SPEECH_FILE_URL");

    /**
     * ##短信接口配置参数
     */
    //  #接口调用凭证
    public static final String ACCESS_KEY_ID = ConfigProperties.getPropertyValue("ACCESS_KEY_ID");
    public static final String ACCESS_KEY_SECRET = ConfigProperties.getPropertyValue("ACCESS_KEY_SECRET");
    //#短信模板
    public static final String TEMPLATE_CODE = ConfigProperties.getPropertyValue("TEMPLATE_CODE");
    //#短信服务签名 中文使用如果编码不对使用编码
    public static final String SIGN_NAME = ConfigProperties.getPropertyValue("SIGN_NAME");
    /**
     * 序列号
     */
    public static final String NUMBER_ID = ConfigProperties.getPropertyValue("NUMBER_ID");
    public static Integer SERIAL_NUMBER = 1;
    /** 科大讯飞的APPID **/
    public static final String SPEECH_APPID = ConfigProperties.getPropertyValue("SPEECH_APPID");
}
