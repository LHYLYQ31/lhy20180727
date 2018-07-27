/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.speech.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechError;
import com.iflytek.cloud.speech.SpeechSynthesizer;
import com.iflytek.cloud.speech.SynthesizeToUriListener;
import com.sinosoft.hms.common.constant.GlobalNames;

import net.sf.json.JSONObject;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年5月25日
 */
@Service
public class SpeechSynthesizeServiceImpl implements SpeechSynthesizeService {
    Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
    Boolean flag = false;

    /**
     * 
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * 
     * @see com.sinosoft.hms.modules.speech.service.SpeechSynthesizeService#speechSynthesize(java.lang.String,
     *      javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public String speechSynthesize(String text, HttpServletRequest request, HttpServletResponse response) {
        //SpeechUtility.createUtility(SpeechConstant.APPID + "=" + GlobalNames.SPEECH_APPID);
        //1.创建 SpeechSynthesizer 对象 
        flag = false;
        SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer();
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");//设置发音人
        mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速，范围0~100
        mTts.setParameter(SpeechConstant.PITCH, "50");//设置语调，范围0~100
        mTts.setParameter(SpeechConstant.VOLUME, "50");//设置音量，范围0~100
        //3.开始合成
        //设置合成音频保存位置（可自定义保存位置），默认保存在“./tts_test.pcm”
        String url = GlobalNames.SPEECH_FILE_URL + new SimpleDateFormat("yyyy-MM-dd").format(new Date())
                + File.separator + System.currentTimeMillis() + ".pcm";
        logger.info("文件路径：{}" + url);
        mTts.synthesizeToUri(text, url, synthesizeToUriListener);
        //校验文件是否生成
        int count = 0;
        while (true) {
            logger.info("等待文件生成++++++++++++++++");
            try {
                Thread.sleep(1000);
                if (count > 10) {
                    break;
                }
                count++;
                if (flag) {

                    mTts.cancel();
                    mTts.destroy();
                    logger.info("文件生成++++++++++++++++");
                    break;
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        JSONObject json = new JSONObject();
        json.put("url", url);
        //this.sayPlay(url, request, response);
        return json.toString();
    }

    //合成监听器
    SynthesizeToUriListener synthesizeToUriListener = new SynthesizeToUriListener() {
        //progress为合成进度0~100 
        @Override
        public void onBufferProgress(int progress) {
            logger.info("生成文件进程：" + progress);
            flag = true;
        }

        //会话合成完成回调接口
        //uri为合成保存地址，error为错误信息，为null时表示合成会话成功
        @Override
        public void onSynthesizeCompleted(String uri, SpeechError error) {
            logger.info("报错码：" + error);
            if (error != null) {
                logger.info("报错码：" + error.getErrorCode());
                logger.info("报错描述：" + error.getErrorDesc());
            }
            else {
                logger.info("成功生成语音文件");
                flag = true;
            }
            logger.info("生成文件路径：" + uri);
        }

        @Override
        public void onEvent(int arg0, int arg1, int arg2, int arg3, Object arg4, Object arg5) {
            // TODO Auto-generated method stub

        }
    };
}
