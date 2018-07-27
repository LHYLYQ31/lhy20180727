/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.attachment.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.sinosoft.hms.common.constant.GlobalNames;
import com.sinosoft.hms.modules.attachment.model.Attachment;
import com.sinosoft.hms.modules.attachment.service.AttachmentService;

import net.sf.json.JSONObject;

/**
 * <B>系统名称：饭店管理</B><BR>
 * <B>模块名称：附件管理</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年4月11日
 */
@RestController
@RequestMapping("attachment")
public class AttachmentController {
    Logger log = LoggerFactory.getLogger(AttachmentController.class);
    @Autowired
    AttachmentService attachmentService;

    /**
     * 
     * <B>方法名称：delete</B><BR>
     * <B>概要说明：删除附件信息</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月11日 上午10:34:18
     * @param id 附件id
     * @return Boolean
     */
    @RequestMapping("delete")
    public String delete(String id) {
        JSONObject json = new JSONObject();
        if (attachmentService.delete(id)) {
            json.put("status", "1");
        }
        else {
            json.put("status", "0");
        }
        return json.toString();
    }

    /**
     * 
     * <B>方法名称：上传附件（上传到服务器磁盘上）</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/upload", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public String upload(HttpServletRequest request) {
        Attachment att = new Attachment();
        //为文件目录路径添加时间戳部分
        String filePath = GlobalNames.FILE_PATH;
        String newFileName = "";//返回的文件名
        String fileName = "";//返回文件名
        JSONObject json = new JSONObject();
        try {
            // 创建解析器，解析request的上下文
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                    request.getSession().getServletContext());
            // 先判断request中是否包含multipart类型的数据
            if (multipartResolver.isMultipart(request)) {

                // 再将request中的数据转化成multipart类型的数据
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

                // 遍历上传的文件
                Iterator<?> iterator = multiRequest.getFileNames();

                // 检测是否有下一个文件
                while (iterator.hasNext()) {
                    MultipartFile file = multiRequest.getFile((String) iterator.next());
                    // 判断文件是否为空
                    if (file != null) {

                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        att.setCreateTime(formatter.format(new Date()));
                        fileName = file.getOriginalFilename();
                        att.setFileName(fileName);
                        String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename()
                                .lastIndexOf(".") + 1).toLowerCase();
                        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
                        String ymd = sdf.format(new Date());
                        newFileName = ymd + File.separator + df.format(new Date()) + "_" + new Random().nextInt(1000)
                                + "."
                                + fileExt;
                        att.setFilePath(newFileName);
                        filePath = filePath + newFileName;
                        File dirTempFile = new File(filePath);
                        if (!dirTempFile.exists()) {
                            dirTempFile.getParentFile().mkdirs();
                        }
                        // 转存文件  
                        file.transferTo(new File(filePath));
                        //上传成功后，保存上传信息到库中
                        att = attachmentService.save(att);
                        json.put("status", "1");
                        json.put("attachmentId", att.getId());
                        json.put("oldName", fileName);
                        log.info("===" + json.toString());
                    }
                }
            }
        }
        catch (Exception e) {
            json.put("status", "0");
            json.put("msg", "upload failed");
            e.printStackTrace();
            log.error("=========上传保错：{}", e);
        }
        return json.toString();
    }

    /**
     * 
     * <B>方法名称：readImage</B><BR>
     * <B>概要说明：读取图片的数据</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月11日 上午11:23:40
     * @param response
     * @param readUrl void
     */
    @RequestMapping("readImageByAttachmentId")
    public void readImageByAttachmentId(String id, HttpServletResponse response, HttpServletRequest request) {
        InputStream in = null;
        byte[] body = null;
        OutputStream toClient = null;
        try {
            Attachment attachment = new Attachment();
            attachment.setId(id);
            Attachment att = attachmentService.getModel(attachment);
            String url = GlobalNames.FILE_PATH;
            if (att != null) {
                in = new FileInputStream(url + att.getFilePath());
            }
            else {
                HttpSession session = request.getSession();
                in = session.getServletContext().getResourceAsStream(
                        "images/zanwu.png");
            }
            body = new byte[in.available()];
            in.read(body);
            toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
            toClient.write(body); // 输出数据
            toClient.flush();

        }
        catch (Exception e) {
            log.error("读取图片报错：{}" + e); // 要找的文件不存在
            e.printStackTrace();
        }
        finally {
            if (in != null) {
                try {
                    in.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if (toClient != null) {
                try {
                    toClient.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 
     * <B>方法名称：readImage</B><BR>
     * <B>概要说明：读取图片的数据</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年4月11日 上午11:23:40
     * @param response
     * @param readUrl void
     */
    @RequestMapping("readImageByFoodId")
    public void readImageByFoodId(String foodId, HttpServletResponse response, HttpServletRequest request) {
        InputStream in = null;
        byte[] body = null;
        OutputStream toClient = null;
        try {
            Attachment attachment = new Attachment();
            attachment.setFoodId(foodId);
            Attachment att = attachmentService.getModel(attachment);
            String url = GlobalNames.FILE_PATH;
            if (att != null && new File(url + att.getFilePath()).exists()) {
                in = new FileInputStream(url + att.getFilePath());
            }
            else {
                HttpSession session = request.getSession();
                in = session.getServletContext().getResourceAsStream(
                        "static/images/food.jpg");
            }
            body = new byte[in.available()];
            in.read(body);
            toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
            toClient.write(body); // 输出数据
            toClient.flush();

        }
        catch (Exception e) {
            log.error("读取图片报错：{}" + e); // 要找的文件不存在
            e.printStackTrace();
        }
        finally {
            if (in != null) {
                try {
                    in.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if (toClient != null) {
                try {
                    toClient.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
