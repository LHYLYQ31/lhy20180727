/**
 * Copyright 2016 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 * @author 中科软科技 张战国
 * @since 2016年7月27日
 */
public class AesUtil2 {
    private static final String DEFAULT_PASSWORD_CRYPT_KEY = ConfigProperties
            .getPropertyValue("DEFAULT_PASSWORD_CRYPT_KEY");
    private static final String AES = "AES";
    private static final String AES_KEY = ConfigProperties.getPropertyValue("AES_KEY");
    private static final String CHAR_SET = "utf-8";
    private static Cipher cipher = null;
    private static byte[] key = null;
    private static Log log = LogFactory.getLog(AesUtil.class);
    static {
        // Cipher对象实际完成加密操作
        try {
            cipher = Cipher.getInstance(AES);
            /*
            // 如果密钥文件存在，则将密钥添加到证书中
            InputStream input = new FileInputStream(new File(ConfigProperties.getPropertyValue("DATA_KEY_PATH")));
            if (input != null) {
                key = new byte[input.available()];
                input.read(key);
                input.close();
            }
            */
            setKey(AES_KEY);//从配置文件中读取密钥
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 
     * <B>方法名称：</B>cbcEncrypt<BR>
     * <B>概要说明：</B>使用默认秘钥加密<BR>
     * @param content
     * @param iv
     * @return
     * @throws Exception
     */
    public static String cbcEncrypt(String content)  {
       try{
           MessageDigest md = MessageDigest.getInstance("MD5");
           byte[] thedigest = md.digest(DEFAULT_PASSWORD_CRYPT_KEY.getBytes(CHAR_SET));
           SecretKeySpec skc = new SecretKeySpec(thedigest, "AES");
           Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
           //算法参数
           byte[] iv = "abcdefgh12345678".getBytes("UTF-8");
           AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
           cipher.init(Cipher.ENCRYPT_MODE, skc,paramSpec);
           byte[] input = content.getBytes("UTF-8");
           int len = input.length;
           byte[] cipherText = new byte[cipher.getOutputSize(len)];
           int ctLength = cipher.update(input, 0, len, cipherText, 0);
           ctLength += cipher.doFinal(cipherText, ctLength);
        return byte2hex(cipherText);
       }catch(Exception e){
           log.error("aes加密出错"+e);
       }
       return "";
        
      }

      /**
       * 
       * <B>方法名称：</B>cbcDecrypt<BR>
       * <B>概要说明：</B>使用默认秘钥解密<BR>
       * @param encrypted
       * @param iv
       * @return
       * @throws Exception
       */
      public static String cbcDecrypt(String encrypted){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(DEFAULT_PASSWORD_CRYPT_KEY.getBytes(CHAR_SET));
            SecretKeySpec skey = new SecretKeySpec(thedigest, "AES");
            Cipher dcipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            //算法参数
            byte[] iv = "abcdefgh12345678".getBytes("UTF-8");
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
            dcipher.init(Cipher.DECRYPT_MODE, skey,paramSpec);
            byte[] clearbyte = dcipher.doFinal(toByte(encrypted));
            return new String(clearbyte);
        }catch(Exception e){
            log.error("aes解密出错"+e);
        }
        return "";
      }
      /**
       * 
       * <B>方法名称：</B><BR>
       * <B>概要说明：</B><BR>
       * @param content
       * @param secretKey
       * @param iv
       * @return
       * @throws Exception
       */
      public static String cbcEncrypt(String content, byte[] secretKey) {
          try{
              MessageDigest md = MessageDigest.getInstance("MD5");
              byte[] thedigest = md.digest(secretKey);
              SecretKeySpec skc = new SecretKeySpec(thedigest, "AES");
              Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
              //算法参数
              byte[] iv = "abcdefgh12345678".getBytes("UTF-8");
              AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
              cipher.init(Cipher.ENCRYPT_MODE, skc,paramSpec);
              byte[] input = content.getBytes("UTF-8");
              int len = input.length;
              byte[] cipherText = new byte[cipher.getOutputSize(len)];
              int ctLength = cipher.update(input, 0, len, cipherText, 0);
              ctLength += cipher.doFinal(cipherText, ctLength);
             /* byte[] input = content.getBytes("UTF-8");
              KeyGenerator kgen = KeyGenerator.getInstance("AES");
              // 使用128 位
              SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
              random.setSeed(skey);
              kgen.init(128, random);
              SecretKey secretKey = kgen.generateKey();
              byte[] encodeFormat = secretKey.getEncoded();
              SecretKeySpec key = new SecretKeySpec(encodeFormat, "AES");
              // Cipher对象实际完成加密操作
              Cipher cipher = Cipher.getInstance("AES");
              // 加密内容进行编码
              byte[] byteContent = content.getBytes("utf-8");
              // 用密匙初始化Cipher对象
              cipher.init(Cipher.ENCRYPT_MODE, key);
              // 正式执行加密操作
              byte[] result = cipher.doFinal(byteContent);
              int len = input.length;
              byte[] cipherText = new byte[cipher.getOutputSize(len)];
              int ctLength = cipher.update(input, 0, len, cipherText, 0);
              ctLength += cipher.doFinal(cipherText, ctLength);*/
              return byte2hex(cipherText);
          }catch(Exception e){
              log.error("aes加密出错"+e);
          }
          return "";
        }

        
        public static String cbcDecrypt(String encrypted, byte[] secretKey) {
          try{
              MessageDigest md = MessageDigest.getInstance("MD5");
              byte[] thedigest = md.digest(secretKey);
              SecretKeySpec skey = new SecretKeySpec(thedigest, "AES");
              Cipher dcipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
              //算法参数
              byte[] iv = "abcdefgh12345678".getBytes("UTF-8");
              AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
              dcipher.init(Cipher.DECRYPT_MODE, skey,paramSpec);
              byte[] clearbyte = dcipher.doFinal(toByte(encrypted));
              return new String(clearbyte,"UTF-8");
              /*KeyGenerator kgen = KeyGenerator.getInstance("AES");
              SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
              random.setSeed(skey);
              // 使用128 位
              kgen.init(128, random);
              SecretKey secretKey = kgen.generateKey();
              byte[] encodeFormat = secretKey.getEncoded();
              SecretKeySpec key = new SecretKeySpec(encodeFormat, "AES");
              // Cipher对象实际完成加密操作
              Cipher cipher = Cipher.getInstance("AES");
              log.error("key："+key);
              // 用密匙初始化Cipher对象
              cipher.init(Cipher.DECRYPT_MODE, key);
              // 正式执行解密操作
              byte[] clearbyte = cipher.doFinal(toByte(encrypted));
              log.error("clearbyte："+clearbyte);
              return new String(clearbyte);*/
          }catch(Exception e){
              log.error("aes解密出错"+e);
          }
          return "";
        }

      
      public static byte[] toByte(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0, l = binary.length; i < l; i++) {
          binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
      }

      
      public static String byte2hex(byte[] inStr) {
        StringBuilder out = new StringBuilder(inStr.length * 2);
        for (int i = 0, l = inStr.length; i < l; i++) {
          // 字节做"与"运算，去除高位置字节 11111111
          if (((int) inStr[i] & 0xff) < 0x10) {
            //小于十前面补零  
            out.append("0");
          }
          out.append(Long.toString((int) inStr[i] & 0xff, 16));
        }
        return out.toString();
      }

      public static void main(String[] args) {
        try {
          byte[] iv = "abcdefgh12345678".getBytes("UTF-8");
          String enc = cbcEncrypt("{\"result\":\"T\",\"url\":\"id=20160725134406?q=啊\"}",key);
          System.out.println("1__" + enc);
          System.out.println("2__" + cbcDecrypt("c99e63d6c84e068648a598fd397911f85cf99b439a28f433c6fbd722ec609954e73c9819e0721a308597b2017f4e087e74006e95882b17e52193528ad8e8542fb46b61f7844b4720d60544cf61a4e06054d29c0d8b5560327dfa3160319d99e178c2e8c7b6d4fb502cd42f3b4a66069d6f955d95efbf003bf91ceb7d138fd4ea",key));
          //解密来自nodejs的密文
//          String node_enc = "cdb9f691d835e3e5b8602e7c49529de3f732ae59a85fb276386a4c02c06844ce6bede3f76a07a15236e44963f3fc2538";
//          System.out.println("3__" + cbcDecrypt(node_enc,key));
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      
      public static byte[] getKey() {
          return key;
      }
      
      public static void setKey(String secretKey) {
        try {
            AesUtil2.key = secretKey.getBytes(CHAR_SET);
        }
        catch (UnsupportedEncodingException e) {
            log.error("设置KEY出错",e);
        }
      }
}
