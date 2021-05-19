# easy-rules-examples

+ [j-easy/easy-rules](https://github.com/j-easy/easy-rules)
+ [wiki](https://github.com/j-easy/easy-rules/wiki)

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