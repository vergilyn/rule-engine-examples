# drools-examples

**Official:**
+ <http://www.drools.org/>
+ [github, drools](https://github.com/kiegroup/drools)
+ [document, drools](https://drools.org/learn/documentation.html)
+ [examples](https://github.com/kiegroup/drools/tree/master/drools-examples)

**Code Demo:**
- [别再说你不会，规则引擎Drools了](https://blog.csdn.net/wuzhiwei549/article/details/104813821)
- [深入了解Drools](https://www.jianshu.com/p/725811f420db)：也是基本的code-demo
- [Workbench 与 kie-server 搭建及使用]：（主要）business-central的使用示例，（次要）java如何通过kie-server调用business-central发布的rule。

**Blogs:**
- [Drools 简介](https://blog.csdn.net/chinrui/article/details/74906748)：简单提了一些原理

[Workbench 与 kie-server 搭建及使用]: https://blog.csdn.net/chinrui/article/details/79018351

## 使用感受
### business-central & kie-server
（都是默认的docker image 7.54.0.Final）
`business-central`貌似只是一个可视化的管理平台？  
可以通过UI创建、定义、测试、发布规则（可以生成drl、java代码），并且可以build&install(deploy)，大概理解是：
通过business-central创建rule然后发布（会有一个container_id），那么java程序就可以通过kie-server 的api根据container_id获取到这个rule，
传入需要的rule参数，就可以执行规则。参考：[Workbench 与 kie-server 搭建及使用]

business-central **体验感受很差**，：
- 页面反应慢
- 部分ui不友好，比如 create-guided-rule。不知道怎么配置出博客中的示例规则（无法配置出设置`applyInfo.name=xxx`）：[Workbench 与 kie-server 搭建及使用]

**maven OR GIT**
大致理解是，通过business-central配置的Rule 发布后会保存到 maven/git，这样通过kie-server才可以获取到rule。


## docker
SEE：<https://hub.docker.com/u/jboss>

- kie-server
- business-central-workbench
- drools-workbench (并没有最新的，最新版本`7.54.Final`，但docker版本才到`7.17.Final`)

## Q&A
### NPE `KieServices`
例如 [HelloworldDroolsTests.java](drools-basic-examples/src/test/java/com/vergilyn/examples/ruleengine/drools/eg0001/HelloworldDroolsTests.java):
```
@Test
void helloworld() {
    // From the kie services, a container is created from the classpath
    KieServices ks = KieServices.get();
    KieContainer kc = ks.getKieClasspathContainer();  // ks NPE

    execute(ks, kc);
}
```

**解决：添加maven依赖`drools-compiler.jar`**

- [Drools 7.4.1 kieservices.factory.get() returns null](https://stackoverflow.com/questions/47556233/drools-7-4-1-kieservices-factory-get-returns-null)

### RuntimeException "You're trying to compile a Drools asset without mvel. Please add the module org.drools:drools-mvel to your classpath."
**解决：添加maven依赖`drools-mvel.jar`**
