package com.sinosoft.hms.common.util;

import java.io.InputStream;
import java.security.MessageDigest;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sinosoft.hms.common.constant.GlobalNames;

public class AesUtil {
    private static final String DEFAULT_PASSWORD_CRYPT_KEY = ConfigProperties.getPropertyValue(GlobalNames.AES_KEY);
    private static final String AES = "AES";
    private static final String CHAR_SET = "utf-8";
    private static Cipher cipher = null;
    private static byte[] key = null;
    private static Log log = LogFactory.getLog(AesUtil.class);

    static {
        // Cipher对象实际完成加密操作
        try {
            cipher = Cipher.getInstance(AES);
            // 如果密钥文件存在，则将密钥添加到证书中
            InputStream input = null;// new FileInputStream(new File(""));
            if (input != null) {
                key = new byte[input.available()];
                input.read(key);
                input.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加密
     * 
     * @param content
     * @return
     */
    public static String cbcEncrypt(String content) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(DEFAULT_PASSWORD_CRYPT_KEY.getBytes(CHAR_SET));
            SecretKeySpec skc = new SecretKeySpec(thedigest, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            // 算法参数
            byte[] iv = "abcdefgh12345678".getBytes("UTF-8");
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, skc, paramSpec);
            byte[] input = content.getBytes("UTF-8");
            int len = input.length;
            byte[] cipherText = new byte[cipher.getOutputSize(len)];
            int ctLength = cipher.update(input, 0, len, cipherText, 0);
            ctLength += cipher.doFinal(cipherText, ctLength);
            return byte2hex(cipherText);
        }
        catch (Exception e) {
            log.error("aes加密出错" + e);
        }
        return "";
    }

    /**
     * 加密
     * 
     * @param content
     * @param key1
     * @return
     */
    public static String cbcEncrypt(String content, byte[] key1) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(key1);
            SecretKeySpec skc = new SecretKeySpec(thedigest, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            // 算法参数
            byte[] iv = "abcdefgh12345678".getBytes("UTF-8");
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, skc, paramSpec);
            byte[] input = content.getBytes("UTF-8");
            int len = input.length;
            byte[] cipherText = new byte[cipher.getOutputSize(len)];
            int ctLength = cipher.update(input, 0, len, cipherText, 0);
            ctLength += cipher.doFinal(cipherText, ctLength);
            return byte2hex(cipherText);
        }
        catch (Exception e) {
            log.error("aes加密出错" + e);
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
            if ((inStr[i] & 0xff) < 0x10) {
                // 小于十前面补零
                out.append("0");
            }
            out.append(Long.toString(inStr[i] & 0xff, 16));
        }
        return out.toString();
    }

    public static byte[] getKey() {
        return key;
    }

    /**
     * 解密
     * 
     * @param encrypted
     * @return
     */
    public static String cbcDecrypt(String encrypted) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(DEFAULT_PASSWORD_CRYPT_KEY.getBytes(CHAR_SET));
            SecretKeySpec skey = new SecretKeySpec(thedigest, "AES");
            Cipher dcipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            // 算法参数
            byte[] iv = "abcdefgh12345678".getBytes("UTF-8");
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
            dcipher.init(Cipher.DECRYPT_MODE, skey, paramSpec);
            byte[] clearbyte = dcipher.doFinal(toByte(encrypted));
            return new String(clearbyte, "UTF-8");

        }
        catch (Exception e) {
            log.error("aes解密出错" + e);
        }
        return "";
    }

    /**
     * 解密
     * 
     * @param encrypted
     * @return
     */
    public static String cbcDecrypt(String encrypted, byte[] key1) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(key1);
            SecretKeySpec skey = new SecretKeySpec(thedigest, "AES");
            Cipher dcipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            // 算法参数
            byte[] iv = "abcdefgh12345678".getBytes("UTF-8");
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
            dcipher.init(Cipher.DECRYPT_MODE, skey, paramSpec);
            byte[] clearbyte = dcipher.doFinal(toByte(encrypted));
            return new String(clearbyte, "UTF-8");

        }
        catch (Exception e) {
            log.error("aes解密出错" + e);
        }
        return "";
    }

    public static void main(String[] args) {
        try {
            byte[] iv = "abcdefgh12345678".getBytes("UTF-8");
            String enc = cbcEncrypt("{\"result\":\"T\",\"url\":\"id=20160725134406?q=啊\"}", key);
            String abc = "17950";
            abc = cbcEncrypt("abc-" + abc);
            System.out.println(abc);
            String bcd = cbcDecrypt("552a5de7c842643d291f33dd47d241ea");
            System.out.println(StringUtils.isBlank(bcd));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
