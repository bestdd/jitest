package com.way.jitest.core.test;

import com.alibaba.fastjson.JSON;
import com.way.jitest.common.ExcelUtils;
import com.way.jitest.common.HttpUtils;
import org.testng.annotations.*;
import org.testng.reporters.JUnitXMLReporter;
import org.uncommons.reportng.HTMLReporter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author xkm
 * @date 2020/2/20
 */
@Listeners({JUnitXMLReporter.class, HTMLReporter.class})
public class InterfaceAutoTestNgDemo {
    /**
     * Excel的头信息-请勿修改Excel的头信息
     */
    private static String TITLE = "标题";
    private static String HTTP_URL = "请求地址";
    private static String HTTP_TYPE = "请求类型";
    private static String HTTP_PARAM_TYPE = "请求参数类型";
    private static String HTTP_PARAM = "请求参数内容";
    private static String HTTP_RESULT = "预期结果(key;key;key……)";

    @BeforeTest
    public void beforeTest() {
        System.out.println("before");
    }

    @Test(dataProvider = "caseList")
    public void test(Map<String, Object> caseMap) throws Exception {
        System.out.println(JSON.toJSONString(caseMap));
        if ("POST".equals(caseMap.get(HTTP_TYPE))) {
            String url = String.valueOf(caseMap.get(HTTP_URL));
            String param = String.valueOf(caseMap.get(HTTP_PARAM));
            String result = String.valueOf(caseMap.get(HTTP_RESULT));
            String[] split = result.split(";");

            String response = HttpUtils.HttpPostWithJson(url, param);
            for (String str : split) {
                if (!response.contains(str)) {
                    throw new RuntimeException("测试用例【"+caseMap.get(TITLE)+"】执行失败，关键字匹配失败");
                }
            }
        } else {
            throw new RuntimeException("暂不支持！！！");
        }

        System.out.println(caseMap.get(HTTP_RESULT));
    }

    @AfterTest
    public void afterTest() {
        System.out.println("after");

    }

    @DataProvider(name = "caseList")
    public Iterator<Object[]> provideData() {
        //todo 可自定义
        String xlsxPath = System.getProperty("user.dir") + "/src/main/resources/case-template.xlsx";

        List<Map<String, Object>> excelData = ExcelUtils.getExcelData(xlsxPath);
        List<Object[]> caseList = new ArrayList<Object[]>();

        for (Object c : excelData) {
            //做一个形式转换
            caseList.add(new Object[]{c});
        }
        return caseList.iterator();
    }
}
