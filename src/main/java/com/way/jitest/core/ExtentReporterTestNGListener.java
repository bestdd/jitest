package com.way.jitest.core;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ResourceCDN;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.util.List;
import java.util.Map;

/**
 * extentReport 测试报告生成器
 *
 * @author xkm
 * @date 2020/2/24
 */
public class ExtentReporterTestNGListener implements IReporter {
    private static final String OUTPUT_FOLDER = "test-output/";
    private static final String FILE_NAME = "Extent.html";

    private ExtentReports extent;

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        init();

        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();

            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();

                buildTestNodes(context.getFailedTests(), Status.FAIL);
                buildTestNodes(context.getSkippedTests(), Status.SKIP);
                buildTestNodes(context.getPassedTests(), Status.PASS);

            }
        }

        for (String s : Reporter.getOutput()) {
            extent.setTestRunnerOutput(s);
        }

        extent.flush();
    }

    private void init() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(OUTPUT_FOLDER + FILE_NAME);
        htmlReporter.config().setDocumentTitle("jitest自动化测试报告");
        htmlReporter.config().setReportName("jitest自动化测试报告");
        htmlReporter.config().setResourceCDN(ResourceCDN.EXTENTREPORTS);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setReportUsesManualConfiguration(true);
    }

    private void buildTestNodes(IResultMap tests, Status status) {
        ExtentTest test;

        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                //todo 自定义修改 case的名称
                Map<String,Object> parameter = (Map<String,Object>) result.getParameters()[0];
                test = extent.createTest(String.valueOf(parameter.get("标题"))); //显示方法名称
                //test.createNode("子案例");  //创建子案例

                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group); //根据group

                if (result.getThrowable() != null) {
                    test.log(status, result.getThrowable()); //异常案例，显示log到报告
                }
                else {
                    test.log(status, "Test " + status.toString().toLowerCase() + "ed");
                }

                test.getModel().setStartTime(DateUtil.date());
                test.getModel().setEndTime(DateUtil.date());
                test.getModel().setDescription("case内容："+ JSON.toJSONString(parameter));
            }
        }
    }

}
