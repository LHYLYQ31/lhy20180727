/**
 * Copyright 2017 SinoSoft. All Rights Reserved.
 */
package com.sinosoft.hms.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.collections.MapUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * HTTP请求对象
 * 
 * @author YYmmiinngg
 */
public class HttpRequester {
    private String defaultContentEncoding;

    public HttpRequester() {
        this.defaultContentEncoding = "UTF-8";
    }

    /**
     * 发送GET请求
     * 
     * @param urlString
     *            URL地址
     * @return 响应对象
     * @throws IOException
     */
    public HttpRespons sendGet(String urlString) throws IOException {
        return this.send(urlString, "GET", null, null);
    }

    /**
     * 发送GET请求
     * 
     * @param urlString
     *            URL地址
     * @param params
     *            参数集合
     * @return 响应对象
     * @throws IOException
     */
    public HttpRespons sendGet(String urlString, Map<String, String> params)
            throws IOException {
        return this.send(urlString, "GET", params, null);
    }

    /**
     * 发送GET请求
     * 
     * @param urlString
     *            URL地址
     * @param params
     *            参数集合
     * @param propertys
     *            请求属性
     * @return 响应对象
     * @throws IOException
     */
    public HttpRespons sendGet(String urlString, Map<String, String> params,
            Map<String, String> propertys) throws IOException {
        return this.send(urlString, "GET", params, propertys);
    }

    /**
     * 发送POST请求
     * 
     * @param urlString
     *            URL地址
     * @return 响应对象
     * @throws IOException
     */
    public HttpRespons sendPost(String urlString) throws IOException {
        return this.send(urlString, "POST", null, null);
    }

    /**
     * <B>方法名称：sendPost</B><BR>
     * <B>概要说明：post发送json参数</B><BR>
     * 
     * @param urlString
     * @param jsonParam
     * @return
     * @throws IOException
     */
    public HttpRespons sendPost(String urlString, String jsonParam) throws IOException {
        HttpURLConnection urlConnection = null;
        String method = "POST";
        URL url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod(method);
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);
        urlConnection.setUseCaches(false);

        urlConnection.setRequestProperty("Content-Type",
                "application/json");

        OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
        //发送参数  
        writer.write(jsonParam);
        //清理当前编辑器的左右缓冲区，并使缓冲区数据写入基础流  
        writer.flush();
        urlConnection.getOutputStream().close();
        //设置响应值
        return this.makeContent(urlString, urlConnection);
    }

    /**
     * <B>方法名称：uploadMultipartFile</B><BR>
     * <B>概要说明：发送带文件的请求</B><BR>
     * 
     * @param url
     * @param params
     * @param fileMap
     * @return 响应结果
     */
    public String uploadMultipartFile(String url, Map<String, String> params,
            Map<String, MultipartFile> fileMap) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            //防止中文文件名称乱码
            builder.setMode(HttpMultipartMode.RFC6532);
            if (MapUtils.isNotEmpty(params)) {
                Iterator<Map.Entry<String, String>> iter = params.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry<String, String> entry = iter.next();
                    String inputName = entry.getKey();
                    String inputValue = entry.getValue();
                    if (inputValue == null) {
                        continue;
                    }
                    builder.addTextBody(inputName, inputValue);// 类似浏览器表单提交，对应input的name和value
                }
            }

            if (MapUtils.isNotEmpty(fileMap)) {
                Iterator<Map.Entry<String, MultipartFile>> iter = fileMap.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry<String, MultipartFile> entry = iter.next();
                    String inputName = entry.getKey();
                    MultipartFile inputValue = entry.getValue();
                    if (inputValue == null) {
                        continue;
                    }

                    builder.addBinaryBody(inputName, inputValue.getInputStream(), ContentType.MULTIPART_FORM_DATA,
                            inputValue.getOriginalFilename());// 文件流
                }
            }
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);// 执行提交
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                // 将响应内容转换为字符串
                result = EntityUtils.toString(response.getEntity());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                httpClient.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 发送POST请求
     * 
     * @param urlString
     *            URL地址
     * @param params
     *            参数集合
     * @return 响应对象
     * @throws IOException
     */
    public HttpRespons sendPost(String urlString, Map<String, String> params)
            throws IOException {
        return this.send(urlString, "POST", params, null);
    }

    /**
     * 发送POST请求
     * 
     * @param urlString
     *            URL地址
     * @param params
     *            参数集合
     * @param propertys
     *            请求属性
     * @return 响应对象
     * @throws IOException
     */
    public HttpRespons sendPost(String urlString, Map<String, String> params,
            Map<String, String> propertys) throws IOException {
        return this.send(urlString, "POST", params, propertys);
    }

    /**
     * 发送HTTP请求
     * 
     * @param urlString
     * @return 响映对象
     * @throws IOException
     */
    private HttpRespons send(String urlString, String method,
            Map<String, String> parameters, Map<String, String> propertys)
            throws IOException {
        HttpURLConnection urlConnection = null;

        if (method.equalsIgnoreCase("GET") && parameters != null) {
            StringBuffer param = new StringBuffer();
            int i = 0;
            for (String key : parameters.keySet()) {
                if (i == 0)
                    param.append("?");
                else
                    param.append("&");
                param.append(key).append("=").append(parameters.get(key));
                i++;
            }
            urlString += param;
        }
        URL url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod(method);
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);
        urlConnection.setUseCaches(false);
        if (propertys != null)
            for (String key : propertys.keySet()) {
                urlConnection.addRequestProperty(key, propertys.get(key));
            }

        if (method.equalsIgnoreCase("POST") && parameters != null) {
            StringBuffer param = new StringBuffer();
            for (String key : parameters.keySet()) {
                param.append("&");
                param.append(key).append("=").append(parameters.get(key));
            }
            urlConnection.getOutputStream().write(param.toString().getBytes());
            urlConnection.getOutputStream().flush();
            urlConnection.getOutputStream().close();
        }

        return this.makeContent(urlString, urlConnection);
    }

    /**
     * 得到响应对象
     * 
     * @param urlConnection
     * @return 响应对象
     * @throws IOException
     */
    private HttpRespons makeContent(String urlString,
            HttpURLConnection urlConnection) throws IOException {
        HttpRespons httpResponser = new HttpRespons();
        try {
            InputStream in = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(in));
            httpResponser.contentCollection = new Vector<String>();
            StringBuffer temp = new StringBuffer();
            String line = bufferedReader.readLine();
            while (line != null) {
                httpResponser.contentCollection.add(line);
                temp.append(line).append("\r\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();

            String ecod = urlConnection.getContentEncoding();
            if (ecod == null)
                ecod = this.defaultContentEncoding;

            httpResponser.urlString = urlString;

            httpResponser.defaultPort = urlConnection.getURL().getDefaultPort();
            httpResponser.file = urlConnection.getURL().getFile();
            httpResponser.host = urlConnection.getURL().getHost();
            httpResponser.path = urlConnection.getURL().getPath();
            httpResponser.port = urlConnection.getURL().getPort();
            httpResponser.protocol = urlConnection.getURL().getProtocol();
            httpResponser.query = urlConnection.getURL().getQuery();
            httpResponser.ref = urlConnection.getURL().getRef();
            httpResponser.userInfo = urlConnection.getURL().getUserInfo();

            httpResponser.content = new String(temp.toString().getBytes(), ecod);
            httpResponser.contentEncoding = ecod;
            httpResponser.code = urlConnection.getResponseCode();
            httpResponser.message = urlConnection.getResponseMessage();
            httpResponser.contentType = urlConnection.getContentType();
            httpResponser.method = urlConnection.getRequestMethod();
            httpResponser.connectTimeout = urlConnection.getConnectTimeout();
            httpResponser.readTimeout = urlConnection.getReadTimeout();

            return httpResponser;
        }
        catch (IOException e) {
            throw e;
        }
        finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }
    }

    /**
     * 默认的响应字符集
     */
    public String getDefaultContentEncoding() {
        return this.defaultContentEncoding;
    }

    /**
     * 设置默认的响应字符集
     */
    public void setDefaultContentEncoding(String defaultContentEncoding) {
        this.defaultContentEncoding = defaultContentEncoding;
    }

}
