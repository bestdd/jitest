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

    public static String HttpGet(String urlAndParams) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        ResponseHandler<String> responseHandler = new BasicResponseHandler();

        HttpGet httpGet = new HttpGet(urlAndParams);
        //设置请求头
        httpGet.addHeader("Content-type", "application/json");
        //发送请求
        String returnValue = httpclient.execute(httpGet, responseHandler);

        return returnValue;
    }
}
