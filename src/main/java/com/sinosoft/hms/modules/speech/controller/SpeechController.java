/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.speech.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinosoft.hms.common.constant.GlobalNames;
import com.sinosoft.hms.modules.speech.service.SpeechService;
import com.sinosoft.hms.modules.speech.service.SpeechSynthesizeService;
import com.sinosoft.hms.modules.speech.util.XunfeiLib;

import net.sf.json.JSONObject;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年5月19日
 */
@Controller
@RequestMapping("speech")
public class SpeechController {
    @Autowired
    SpeechService speechService;
    @Autowired
    SpeechSynthesizeService speechSynthesizeService;

    /**
     * 
     * <B>方法名称：speech</B><BR>
     * <B>概要说明：语音识别</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年5月19日 下午1:47:59
     * @return String
     */
    @ResponseBody
    @RequestMapping(value = "speech", produces = "application/json; charset=utf-8")
    public String speech() {
        JSONObject json = new JSONObject();
        json.put("speechResult", speechService.speech());
        System.out.println(json.toString());
        return json.toString();
    }

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
    @ResponseBody
    @RequestMapping(value = "speechSynthesize", produces = "application/json; charset=utf-8")
    public String speechSynthesize(String text, HttpServletRequest request, HttpServletResponse response) {
        return speechSynthesizeService.speechSynthesize(text, request, response);
    }

    /**
     * 
     * <B>方法名称：返回播放的文件流</B><BR>
     * <B>概要说明：</B><BR>
     *
     * @author：lihaiyi
     * @cretetime:2018年5月25日 下午2:56:53
     * @param fileName 文件路径
     * @param request
     * @param response void
     */
    @RequestMapping(value = "say", produces = "application/json; charset=utf-8")
    private void sayPlay(String path, HttpServletRequest request, HttpServletResponse response) {
        Boolean speechFlag = false;
        //输出 wav IO流
        try {
            response.setHeader("Content-Type", "audio/mpeg");
            File file = new File(path);
            if (!file.exists()) {
                speechFlag = true;
                file = new File(GlobalNames.DEFUALT_SPEECH_FILE_URL);
            }
            int len_l = (int) file.length();
            byte[] buf = new byte[2048];
            FileInputStream fis = new FileInputStream(file);
            OutputStream out = response.getOutputStream();
            //写入WAV文件头信息
            out.write(XunfeiLib.getWAVHeader(len_l, 8000, 2, 16));

            len_l = fis.read(buf);
            while (len_l != -1) {
                out.write(buf, 0, len_l);
                len_l = fis.read(buf);
            }
            out.flush();
            out.close();
            fis.close();
            //删除文件和清除队列信息
            //            if (!speechFlag) {
            //                XunfeiLib.delDone(fileName);
            //                file.delete();
            //            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}
