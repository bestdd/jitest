package com.way.jitest.common;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * @author xkm
 * @date 2020/2/18
 */
public class HttpUtils {

    public static String HttpPostWithJson(String url, String json) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        ResponseHandler<String> responseHandler = new BasicResponseHandler();

        HttpPost httpPost = new HttpPost(url);
        //设置请求头
        httpPost.addHeader("Content-type", "application/json");
        //设置请求体
        httpPost.setEntity(new StringEntity(json));
        //发送请求
        String returnValue = httpclient.execute(httpPost, responseHandler);

        return returnValue;
    }

    /**
     * post请求 参数为key:value
     * @param  urlAndParams  url/参数：localhost:8080/itest/get?param1=1&param2=2
     * @return
     */
    public static String HttpPost(String urlAndParams) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        ResponseHandler<String> responseHandler = new BasicResponseHandler();

        HttpPost httpPost = new HttpPost(urlAndParams);
        //发送请求
        String returnValue = httpclient.execute(httpPost, responseHandler);

        return returnValue;
    }

    /**
     * get请求 参数为key:value
     * @param  urlAndParams  url/参数：localhost:8080/itest/get?param1=1&param2=2
     * @return
     */
    public static String HttpGet(String urlAndParams) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        ResponseHandler<String> responseHandler = new BasicResponseHandler();

        HttpGet httpGet = new HttpGet(urlAndParams);
        //发送请求
        String returnValue = httpclient.execute(httpGet, responseHandler);

        return returnValue;
    }
}
