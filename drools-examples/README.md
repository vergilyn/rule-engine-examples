# drools-examples

**Official:**
+ <http://www.drools.org/      >
+ [github, drools](https://github.com/kiegroup/drools)
+ [document, drools](https://drools.org/learn/documentation.html)

**Code Demo:**
- [别再说你不会，规则引擎Drools了](https://blog.csdn.net/wuzhiwei549/article/details/104813821)
- [深入了解Drools](https://www.jianshu.com/p/725811f420db)：也是基本的code-demo

**Blogs:**
- [Drools 简介](https://blog.csdn.net/chinrui/article/details/74906748)：简单提了一些原理

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
