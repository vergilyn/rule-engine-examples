# easy-rules-basic-examples

SEE:  
+ [easy-rules/wiki, Tutorials](https://github.com/j-easy/easy-rules/wiki/hello-world)
+ [easy-rules-tutorials](https://github.com/j-easy/easy-rules/tree/easy-rules-4.1.0/easy-rules-tutorials)

`RuleListener` & `RuleEngineListener`:
```
# see: `org.jeasy.rules.core.DefaultRulesEngine.fire`

rulesEngineListener.beforeEvaluate(rule, facts);
for (Rule rule : rules) {
  if(!ruleListener.beforeEvaluate(rule, facts)){
     continue;
  }

  boolean evaluationResult;
  try{
      evaluationResult = rule.evaluate(facts);
  }catch(Exception e){
      ruleListener.onEvaluationError(rule, facts, e);
  }

  if(evaluationResult){
      ruleListener.afterEvaluate(rule, facts, true);
      try{
          ruleListener.beforeExecute(rule, facts);
          rule.execute(facts);
          ruleListener.onSuccess(rule, facts
      }catch(Exception e){
          ruleListener.onFailure(rule, facts, exception)
      }
  }else{
      ruleListener.afterEvaluate(rule, facts, false);
  }
} // for-end
rulesEngineListener.afterExecute(rule, facts)
```

## Q&A
### \[eg0001\] `IllegalAccessException: XxxRule with modifiers "public"`
```
11:08:49.974 [main] DEBUG org.jeasy.rules.core.DefaultRulesEngine - Engine parameters { skipOnFirstAppliedRule = false, skipOnFirstNonTriggeredRule = false, skipOnFirstFailedRule = false, priorityThreshold = 2147483647 }
11:08:49.982 [main] DEBUG org.jeasy.rules.core.DefaultRulesEngine - Registered rules:
11:08:49.983 [main] DEBUG org.jeasy.rules.core.DefaultRulesEngine - Rule { name = 'Hello World rule', description = 'Always say hello world', priority = '2147483646'}
11:08:49.983 [main] DEBUG org.jeasy.rules.core.DefaultRulesEngine - Known facts:
11:08:49.983 [main] DEBUG org.jeasy.rules.core.DefaultRulesEngine - Rules evaluation started
11:08:49.988 [main] ERROR org.jeasy.rules.core.DefaultRulesEngine - Rule 'Hello World rule' evaluated with error
java.lang.reflect.UndeclaredThrowableException: null
	at com.sun.proxy.$Proxy14.evaluate(Unknown Source)
	at org.jeasy.rules.core.DefaultRulesEngine.doFire(DefaultRulesEngine.java:97)
	at org.jeasy.rules.core.DefaultRulesEngine.fire(DefaultRulesEngine.java:70)
	// ...
Caused by: java.lang.IllegalAccessException: Class org.jeasy.rules.core.RuleProxy can not access a member of class com.vergilyn.examples.ruleengine.easyrules.u0001.HelloWorldRule with modifiers "public"
	at sun.reflect.Reflection.ensureMemberAccess(Reflection.java:102)
	at java.lang.reflect.AccessibleObject.slowCheckMemberAccess(AccessibleObject.java:296)
	at java.lang.reflect.AccessibleObject.checkAccess(AccessibleObject.java:288)
	at java.lang.reflect.Method.invoke(Method.java:491)
	at org.jeasy.rules.core.RuleProxy.evaluateMethod(RuleProxy.java:126)
	at org.jeasy.rules.core.RuleProxy.invoke(RuleProxy.java:107)
	... 67 common frames omitted
11:08:49.991 [main] DEBUG org.jeasy.rules.core.DefaultRulesEngine - Rule 'Hello World rule' has been evaluated to false, it has not been executed

```

`HelloWorldRule.java`: 必须被声明成`public class` OR `public static class`