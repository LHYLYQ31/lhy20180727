/**
 * Copyright 2018 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.modules.speech.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.iflytek.cloud.speech.RecognizerListener;
import com.iflytek.cloud.speech.RecognizerResult;
import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechError;
import com.iflytek.cloud.speech.SpeechRecognizer;
import com.sinosoft.hms.modules.speech.util.JsonParser;

/**
 * <B>系统名称：hms管理系统</B><BR>
 * <B>模块名称：语言转换</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * 
 * @author 中科软科技 lihaiyi
 * @since 2018年5月19日
 */
@Service
public class SpeechServiceImpl implements SpeechService {
    /**  */
    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(SpeechServiceImpl.class);
    String text = "";
    Boolean flag = false;

    /**
     * <B>方法名称：speech</B><BR>
     * <B>概要说明：语音识别</B><BR>
     * 
     * @see com.sinosoft.hms.modules.speech.service.SpeechService#speech()
     */
    @Override
    public String speech() {
        flag = false;
        SpeechRecognizer mIat = SpeechRecognizer.createRecognizer();
        //2.设置听写参数，详见《MSC Reference Manual》SpeechConstant类
        //        mIat.setParameter(SpeechConstant.DOMAIN, "iat");
        mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mIat.setParameter(SpeechConstant.ACCENT, "mandarin ");
        mIat.setParameter(SpeechConstant.ASR_PTT, "0");
        mIat.setParameter(SpeechConstant.SAMPLE_RATE, "16000");//  
        mIat.setParameter(SpeechConstant.NET_TIMEOUT, "20000");
        mIat.setParameter(SpeechConstant.KEY_SPEECH_TIMEOUT, "60000");

        //3.开始听写
        //
        text = "";
        if (!mIat.isListening()) {
            mIat.startListening(mRecoListener);
        }
        else {
            mIat.stopListening();
        }
        Long nowTime = System.currentTimeMillis();
        while (true) {
            if (flag) {
                break;
            }
            Long nowTime1 = System.currentTimeMillis();
            if (nowTime1 - nowTime > 30 * 1000) {
                break;
            }
        }
        logger.info("转换结果：" + text);
        mIat.stopListening();
        mIat.cancel();
        mIat.destroy();
        // SpeechUtility.getUtility().destroy();
        return text;
    }

    //听写监听器
    private RecognizerListener mRecoListener = new RecognizerListener() {
        //听写结果回调接口(返回Json格式结果，用户可参见附录)； //一般情况下会通过onResults接口多次返回结果，完整的识别内容是多次结果的累加； //关于解析Json的代码可参见MscDemo中JsonParser类； //isLast等于true时会话结束。 
        @Override
        public void onResult(RecognizerResult results, boolean isLast) {
            //用json来获取结果  
            String resutlText = results.getResultString();

            if (isLast) {
                logger.info("转换结果isLast：" + isLast);
            }
            if (StringUtils.isNotBlank(resutlText)) {
                JsonParser json = new JsonParser();
                String newTest = json.parseIatResult(resutlText);
                text += newTest;
                logger.info("转换结果：" + text);
                flag = true;
            }
            else {
                flag = true;
            }

        }

        //会话发生错误回调接口  
        @Override
        public void onError(SpeechError error) {
            flag = true;
            logger.info("错误描述：" + error.getErrorDesc());
            logger.info("错误描述：" + error.getErrorCode());

            //获取错误码描述
        } //开始录音

        @Override
        public void onBeginOfSpeech() {
        }

        //音量值0~30  
        @Override
        public void onVolumeChanged(int volume) {
        }

        //结束录音  
        @Override
        public void onEndOfSpeech() {
        }

        //扩展用接口  
        @Override
        public void onEvent(int eventType, int arg1, int arg2, String msg) {
        }
    };

}
