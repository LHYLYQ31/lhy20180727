package com.sinosoft.hms.common.util;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.collections.set.SynchronizedSet;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;




public class PostUtil {
	public static String sendPost(String urlPath,String  json) throws Exception{
		 StringBuffer sb =null;
		 String result="";
		 JSONObject  resultjson=new JSONObject();
	
         //创建连接
         URL url = new URL(urlPath);
         HttpURLConnection connection = (HttpURLConnection) url
                 .openConnection();
         connection.setDoOutput(true);
         connection.setDoInput(true);
         connection.setRequestMethod("POST");
         connection.setUseCaches(false);
         connection.setInstanceFollowRedirects(false);
         connection.setRequestProperty("accept", "*/*");
         connection.setRequestProperty("connection", "Keep-Alive");
         connection.setRequestProperty("user-agent",
                 "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
         connection.setRequestProperty("Authorization", "MzI0YzhhODE1NTZlNDJhMTEyYmE5YzY5OmU3ZTM1YTE3YTdmYmVmOWMwYTJlMWIyZQ==");
         connection.connect();
//        post.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
         //POST请求
         DataOutputStream out = new DataOutputStream(
                 connection.getOutputStream());
         //Bety[] 
         out.write(json.getBytes("utf-8"));
        // out.writeBytes(json);
         out.flush();
         out.close();

         //读取响应
         BufferedReader reader = new BufferedReader(new InputStreamReader(
                 connection.getInputStream(),"utf-8")); 
         String lines;
         sb = new StringBuffer("");
         while ((lines = reader.readLine()) != null) {
             lines = new String(lines.getBytes(), "utf-8");
             sb.append(lines);
         }
         result=sb.toString();
         System.out.println(sb);
         reader.close();
         // 断开连接
         connection.disconnect();
    
	 return result; 
 }
public static String  httpPost(String url,JSONObject jsonParam,String Authorization){
        String rev ="";
	    try {  
	        HttpClient httpclient = new DefaultHttpClient();  
	        String uri = "http://www.yourweb.com";  
	        HttpPost httppost = new HttpPost(url);   
	        //添加http头信息  
	        httppost.addHeader("Authorization", "Basic "+Authorization); //认证token  
	        httppost.addHeader("Content-Type", "application/json");  
	        httppost.addHeader("User-Agent", "imgfornote");  
	        httppost.setEntity(new StringEntity(jsonParam.toString()));     
	        HttpResponse response;  
	        response = httpclient.execute(httppost);  
	        //检验状态码，如果成功接收数据  
	        int code = response.getStatusLine().getStatusCode();  
	        System.err.println("code:"+code);
	        rev=code+"";
//	        if (code == 200) {   
//	            rev = EntityUtils.toString(response.getEntity());//返回json格式： {"id": "27JpL~j4vsL0LX00E00005","version": "abc"}         
//	            System.out.println(rev);
//	        }else{
//	           
//	        }
	        }catch (Exception e) {  
	           e.printStackTrace(); 
	        }	
	    return rev;
	}

}
