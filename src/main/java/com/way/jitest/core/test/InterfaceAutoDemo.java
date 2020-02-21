package com.way.jitest.core.test;

import com.alibaba.fastjson.JSON;
import com.way.jitest.common.HttpUtils;
import com.way.jitest.core.dto.ReceiptQueryDto;

/**
 * @author xkm
 * @date 2020/2/18
 */
public class InterfaceAutoDemo {




    public static void main(String[] args) throws Exception {
        //todo  请自定义——入参
        String jsonString = "{\"param1\":1,\"param2\":2}";
        //todo  请自定义——请求url
        String url = "http://localhost:8080/itest/post/json";
        //发送post请求
        String response = HttpUtils.HttpPostWithJson(url, jsonString);
        System.out.println("响应内容为：" + response);
        //todo 请自定义——响应内容校验：采用关键字匹配
        String key1 = "处理成功";
        String key2 = "0000";
        System.out.println("==========================");
        if(response.contains(key1) && response.contains(key2)){
            System.out.println("测试用例执行成功，Hello World！");
        }else {
            System.out.println("测试用例执行失败");
        }
        System.out.println("==========================");
    }
}
