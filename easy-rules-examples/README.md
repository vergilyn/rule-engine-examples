# easy-rules-examples

+ [j-easy/easy-rules](https://github.com/j-easy/easy-rules)
+ [wiki](https://github.com/j-easy/easy-rules/wiki)

## 个人使用体验和看法
怎么说了，食之有一点点味，弃之无所谓....

1. 使用足够简单，代码容易理解。

2. 规则管理起来可能不够直观。
比如`if-else`，需要建立2个rule，并且确保`condition`互斥。

又比如`eg0002`，4个rule之间的执行顺序`priority`(0-1-2-3)不是很直观，
如果此时需要在`1-2`增加一个Rule，则需要调整`1&2`对应Rule的`priority`。

3. 稍微复杂一点的IF转换成easy-rule？
+ [`NestedIfTests.java`](easy-rules-basic-examples/src/test/java/com/vergilyn/examples/ruleengine/easyrules/eg1001/NestedIfTests.java)
```
if(number <= 0){
    if (number > -10){
        System.out.printf("number[%d] between (-10, 0]\n", number);
    }else{
        System.out.printf("number[%d] between (-inf, -10]\n", number);
    }
}else{
    if (number < 5){
        System.out.printf("number[%d] between (0, 5)\n", number);
    }else{
        System.out.printf("number[%d] between [5, +inf)\n", number);
    }
}

## 转换思路：可以变成 4个If-elseIf 的形式，然后写对应的easy-rule
- number <= 0 && number > -10
- number <= 0 && number <= -10
- number > 0 && number < 5
- number > 0 && number >= 5
```

4. 复杂IF延伸，例如后续IF的判断依赖的参数不一样
```
if(a){
  boolean a1 = isInnerA();
  if(a1){
  }
}
```

貌似可以通过`facts`传递，但是个人感觉这样的话隐蔽性忒高了，很难避免某个Rule中把facts内容修改了。
并且，因为rule-priority加深了facts中参数的变更复杂度。


## Project status
As of December 2020, Easy Rules is in maintenance mode(维护模式).  
This means only bug fixes will be addressed from now on(<b color="RED">只会修复bug</b>).  
Version 4.1.x is the only supported version.

Please consider upgrading to this version at your earliest convenience.

## 原理
代码其实很少，核心的代码：
+ `DefaultRulesEngine`
+ `RuleProxy`

1. Rule 只支持当满足`condition`时，执行`actions`
例如`basic-examples`中的`eg003`，如果`age >= 18`时需要print，需要再写一个Rule，
而不能是通过`alcohol-rule.yml`定义`if-else`。


## Q&A
### logger level
`simplelogger.properties` Add log configuration to enable debug level by default:
```
# 
org.slf4j.simpleLogger.defaultLogLevel=debug

```

2021-05-19，没成功，还是用`logback.xml`指定！