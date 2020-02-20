> 这是一个让你快速入手、快速入门的自动化工具 
```
jitest不只是能使用的接口自动化工具，这是一个让你可学习，可提升的工具
jitest分为v1.0,v2.0,v3.0 使用工具和复杂度不同，测试同学自由选择

当前版本：v1.0
使用技术：httpClient  fastJson
示例方法：InterfaceAutoDemo.java

追寻能力的过程是价值的实现
```
### 使用步骤 
- 安装工具：jdk下载安装配置（百度即可）
- 安装工具：idea下载安装、导入项目 （百度即可）
- 入口方法: InterfaceAutoDemo.java  修改文件中提示自定义的内容，右击运行
### 帮你学习

#### java
对功能测试的同学不推荐系统学习，可先根据jitest在文件中做一个简单接口测试，就会对java有入门级别认识
#### maven
阅读pom.xml文件，了解引入fastJson和httpClient的方式，就会入门maven这款项目构建工具
#### fastJson
主要负责 json字符串各类操作，功能丰富，几乎可覆盖所有使用场景 
```
String text = JSON.toJSONString(obj); //对象转换为json字符串 
VO vo = JSON.parseObject("{...}", VO.class); //json字符串转换为
```
#### httpClient
可参考使用：HttpUtils.java   
官方使用示例：http://hc.apache.org/httpcomponents-client-4.5.x/httpclient/examples/org/apache/http/examples/client/