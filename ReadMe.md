> 这是一个让你快速入手、快速入门的自动化工具 
```
jitest不只是能使用的接口自动化工具，这是一个让你可学习，可提升的工具。
jitest分为v1.0,v2.0,v3.0 使用工具和复杂度不同，测试同学自由选择

当前版本：v2.0
使用技术：excel testNg reportNg 
示例方法：InterfaceAutoTestNgDemo.java

追寻能力的过程是价值的实现
```
### 使用步骤 
- 安装工具：jdk下载安装配置（百度即可）
- 安装工具：idea下载安装、导入项目 （百度即可）
- 启动项目
```
[1]cd jitest/src/main/resources
[2]java -java itestAPI-1.0.jar 
用做接口测试（推荐使用自己的项目调试入门）
```
- 录入用例：jitest/src/main/resources/case-template.xlsx
- 入口方法：InterfaceAutoTestNgDemo.java 右击运行
- 测试报告位置：jitest/target/test-output/html/index.html
### 帮你学习

#### java
InterfaceAutoTestNgDemo.java文件是利用testNg负责接口自动化测试流程、利用reportNg在 
#### maven
阅读pom.xml文件，了解引入fastJson和httpClient的方式，就会入门maven这款项目构建工具
#### testNg
TestNG是一个测试框架，可先根据jitest在InterfaceAutoTestNgDemo.java文件中做一个简单接口测试，就会对testNg有入门级别认识
了解testNg的常用注解：https://www.yiibai.com/testng/basic-annotations.html
#### huTool
一款几乎涵盖多数日常使用的工具类，非常推荐，可以帮你少写50%的代码。比如：Excel表操作、获取时间的各类方法、字符串工具......  
官方使用教程：https://www.hutool.club/docs/ 
#### fastJson
主要负责 json字符串各类操作，功能丰富，几乎可覆盖所有使用场景 
```
String text = JSON.toJSONString(obj); //对象转换为json字符串 
VO vo = JSON.parseObject("{...}", VO.class); //json字符串转换为
```
#### httpClient
可参考使用：HttpUtils.java   
官方使用示例：http://hc.apache.org/httpcomponents-client-4.5.x/httpclient/examples/org/apache/http/examples/client/