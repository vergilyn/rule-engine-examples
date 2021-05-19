package com.vergilyn.examples.ruleengine.easyrules.eg0002;

import com.vergilyn.examples.ruleengine.easyrules.eg0002.rules.BuzzRule;
import com.vergilyn.examples.ruleengine.easyrules.eg0002.rules.FizzBuzzRule;
import com.vergilyn.examples.ruleengine.easyrules.eg0002.rules.FizzRule;
import com.vergilyn.examples.ruleengine.easyrules.eg0002.rules.NonFizzBuzzRule;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.api.RulesEngineParameters;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.support.composite.UnitRuleGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * dependency: {@linkplain UnitRuleGroup}, `easy-rules-support.jar`.
 *
 * <blockquote>
 *   This tutorial implements the FizzBuzz application with Easy Rules.
 *   FizzBuzz is a simple application that requires to count from 1 to 100 and: <br/>
 *     1) print "fizz" if the number is multiple of 5;  <br/>
 *     2) print "buzz" if the number is multiple of 7;  <br/>
 *     3) print "fizzbuzz" if the number is multiple of 5 and 7;  <br/>
 *     4) print the number itself otherwise.  <br/>
 * </blockquote>
 *
 * @author vergilyn
 * @see <a href="https://github.com/j-easy/easy-rules/wiki/fizz-buzz">FizzBuzz.java</a>
 * @since 2021-05-19
 */
class FizzBuzzTests {

	@Test
	@DisplayName("bad code")
	void bad() {
		for (int i = 1; i <= 100; i++) {
			if (i % 5 == 0 && i % 7 == 0)
				System.out.println("fizzbuzz");
			else if (i % 5 == 0)
				System.out.println("fizz");
			else if (i % 7 == 0)
				System.out.println("buzz");
			else
				System.out.println(i);
		}
	}

	@Test
	@DisplayName("use easy-rules")
	public void easyRules() {
		// create a rules engine
		// `skipOnFirstAppliedRule = true` parameter to skip subsequent rules whenever a rule is applied.
		RulesEngineParameters parameters = new RulesEngineParameters().skipOnFirstAppliedRule(true);
		RulesEngine rulesEngine = new DefaultRulesEngine(parameters);

		// create rules
		Rules rules = new Rules();
		rules.register(new FizzRule());  // priority: 1. `i % 5 == 0`
		rules.register(new BuzzRule());  // priority: 2. `i % 7 == 0`
		rules.register(new FizzBuzzRule(new FizzRule(), new BuzzRule()));  // priority: 0. `i % 5 == 0 && i % 7 == 0`
		rules.register(new NonFizzBuzzRule());  // priority: 3. else

		// fire rules
		Facts facts = new Facts();
		for (int i = 1; i <= 100; i++) {
			facts.put("number", i);
			rulesEngine.fire(rules, facts);
		}
	}

	/**
	 * {@linkplain #refactor()} 其实rule可以是单例的，只要其中的（condition/action等）方法是thread-safe的话。
	 */
	@Test
	@DisplayName("rule is thread-safe?")
	void threadSafe(){
		// create a rules engine
		RulesEngineParameters parameters = new RulesEngineParameters().skipOnFirstAppliedRule(true);
		RulesEngine rulesEngine = new DefaultRulesEngine(parameters);

		// create rules
		final FizzRule fizzRule = new FizzRule();
		final BuzzRule buzzRule = new BuzzRule();
		final FizzBuzzRule fizzBuzzRule = new FizzBuzzRule(fizzRule, buzzRule);

		Rules rules = new Rules();
		rules.register(fizzRule);
		rules.register(buzzRule);
		rules.register(fizzBuzzRule);
		rules.register(new NonFizzBuzzRule());

		// fire rules
		Facts facts = new Facts();
		for (int i = 1; i <= 100; i++) {
			facts.put("number", i);
			rulesEngine.fire(rules, facts);
		}
	}
}
