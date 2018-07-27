package com.sinosoft.hms.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * <p>
 * Title: FileUtil.java
 * </p>
 * <p>
 * Description:文件操作工具类
 * </p>
 * <p>
 * 版权所有(C) 2015-2018。中科软科技股份有限公司。
 * </p>
 * 
 * @author kjx
 * @date 2015-9-15
 * @version 2.0
 */
public class FileUtil {
    private static Log log = LogFactory.getLog(FileUtil.class);
    public final static String FILE_PATH = "FILE_PATH";
    public final static String CONTENT = "CONTENT";

    /**
     * 
     * <B>方法名称：loadDiskImg</B><BR>
     * <B>概要说明：加载磁盘上图片</B><BR>
     * 
     * @param path 要加载图片的路径+文件名
     * @param response
     * @throws FileNotFoundException,IOException
     */
    public static String loadDiskImg(String path, HttpServletResponse response) throws FileNotFoundException,
            IOException {
        //加载磁盘上的图片
        InputStream in = new FileInputStream(path);
        return loadImg(in, response);

    }

    /**
     * 
     * <B>方法名称：loadProImg</B><BR>
     * <B>概要说明：加载项目中的图片</B><BR>
     * 
     * @param path 文件的路径+文件名
     * @param response
     * @param request
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String loadProImg(String path, HttpServletResponse response, HttpServletRequest request)
            throws FileNotFoundException, IOException {
        javax.servlet.http.HttpSession session = request.getSession();
        InputStream in = session.getServletContext().getResourceAsStream(path);
        return loadImg(in, response);
    }

    /**
     * 
     * <B>方法名称：loadImg</B><BR>
     * <B>概要说明：加载图片，将图片以流的形式输出到客户端</B><BR>
     * 
     * @param in 文件流
     * @param response
     * @return
     * @throws IOException
     */
    public static String loadImg(InputStream in, HttpServletResponse response) throws IOException {
        byte data[] = new byte[in.available()];
        in.read(data);
        OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
        toClient.write(data); // 输出数据
        toClient.flush();
        toClient.close();
        in.close();
        return null;
    }

}
