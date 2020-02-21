package com.way.jitest.core.test;

import cn.hutool.core.date.DateUtil;
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
/** @Listeners 启动测试报告监听器*/
@Listeners({JUnitXMLReporter.class, HTMLReporter.class})
public class InterfaceAutoTestNgDemo {
    /**
     * Excel的头信息-请勿修改Excel的头信息
     *
     * 注意：编号一定要顺序填写，用作结果回填
     */
    private static String ID = "编号";
    private static String TITLE = "标题";
    private static String HTTP_URL = "请求地址";
    private static String HTTP_TYPE = "请求类型";
    private static String HTTP_PARAM_TYPE = "请求参数类型";
    private static String HTTP_PARAM = "请求参数内容";
    private static String HTTP_RESULT = "预期结果(key;key;key……)";

    /** 测试时间 */
    private String testTime;
    /** 测试结果 */
    private String testResult;
    /** 当前测试行 */
    private int currentRow;

    //todo 可自定义
    String xlsxPath = System.getProperty("user.dir") + "/src/main/resources/case-template.xlsx";
    String shellName = "工作表1";

    @BeforeMethod
    public void beforeTest() {
        //初始化测试执行时间
        testTime = DateUtil.now();
        //初始化测试结果
        testResult = "FAIL";
    }

    /**
     * testNg执行测试入口类
     *
     * @param  caseMap dataProvider中的数据
     */
    @Test(dataProvider = "caseList")
    public void test(Map<String, Object> caseMap) throws Exception {
        String id = String.valueOf(caseMap.get(ID));
        currentRow = Integer.valueOf(id);

        //获取用例内容
        String url = String.valueOf(caseMap.get(HTTP_URL));
        String param = String.valueOf(caseMap.get(HTTP_PARAM));
        String result = String.valueOf(caseMap.get(HTTP_RESULT));
        String type = String.valueOf(caseMap.get(HTTP_TYPE));
        String paramType = String.valueOf(caseMap.get(HTTP_PARAM_TYPE));
        String[] keyArray = result.split(";");
        String response;
        //post请求，参数为json
        if ("POST".equals(type) && "Json".equals(paramType)) {
            response = HttpUtils.HttpPostWithJson(url, param);
        }else if("POST".equals(type) && "Key:Value".equals(paramType)){
            response = HttpUtils.HttpPost(url + "?" + param);
        }else if(("GET".equals(type) && "Key:Value".equals(paramType))){
            response = HttpUtils.HttpGet(url + "?" + param);
        }else {
            throw new RuntimeException("暂不支持！！！");
        }


        for (String str : keyArray) {
            if (!response.contains(str)) {
                throw new RuntimeException("测试用例【"+caseMap.get(TITLE)+"】执行失败，关键字匹配失败");
            }
        }

        testResult = "PASS";
    }

    @AfterMethod
    public void afterTest() {
        //回填测试结果
        ExcelUtils.updateExcelDate(7,currentRow, testTime, xlsxPath,shellName);
        ExcelUtils.updateExcelDate(8,currentRow, testResult, xlsxPath,shellName);
    }

    /**
     * testNg 数据获取
     * @return  excel中的case数据
     */
    @DataProvider(name = "caseList")
    public Iterator<Object[]> provideData() {
        List<Map<String, Object>> excelData = ExcelUtils.getExcelData(xlsxPath);
        List<Object[]> caseList = new ArrayList<Object[]>();

        for (Object c : excelData) {
            //做一个形式转换
            caseList.add(new Object[]{c});
        }
        return caseList.iterator();
    }
}
