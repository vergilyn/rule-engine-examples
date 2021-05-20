package com.vergilyn.examples.ruleengine.easyrules.eg1001;

import java.util.List;

import com.google.common.collect.Lists;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RuleBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NestedIfTests {
	static final String KEY_NUMBER = "number";

	@ParameterizedTest
	@DisplayName("traditional nested-if code")
	@ValueSource(ints =  {-20, -5, 0, 4, 10})
	void traditional(int number){
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
	}

	@ParameterizedTest
	@DisplayName("easy-rules nested-if code")
	@ValueSource(ints = {-20, -5, 0, 4, 10})
	void easyRule(int number){
		// define facts
		Facts facts = new Facts();
		facts.put(KEY_NUMBER, number);

		Rules rules = new Rules();
		rules.register(buildRules().toArray());

		// fire rules on known facts
		RulesEngine rulesEngine = new DefaultRulesEngine();
		rulesEngine.fire(rules, facts);
	}

	/**
	 * {@linkplain #traditional(int)} 拆分成N个互斥的 If-esleIf
	 */
	List<Rule> buildRules(){
		List<Rule> rules = Lists.newArrayListWithCapacity(16);

		// if(number <= 0 ) --> if(number > -10)
		rules.add(new RuleBuilder()
				.name("number <= 0 && number > -10")
				.when(facts -> {
					Integer number = facts.get(KEY_NUMBER);
					return number <= 0 && number > -10;
				})
				.then(facts -> {
					Integer number = facts.get(KEY_NUMBER);
					System.out.printf("number[%d] between (-10, 0]\n", number);
				})
				.build());

		// if(number <= 0) --> else
		rules.add(new RuleBuilder()
				.name("number <= 0 && number <= -10")
				.when(facts -> {
					Integer number = facts.get(KEY_NUMBER);
					return number <= 0 && number <= -10;
				})
				.then(facts -> {
					Integer number = facts.get(KEY_NUMBER);
					System.out.printf("number[%d] between (-inf, -10]\n", number);
				})
				.build());

		// else --> if (number < 5)
		rules.add(new RuleBuilder()
				.name("number > 0 && number < 5")
				.when(facts -> {
					Integer number = facts.get(KEY_NUMBER);
					return number > 0 && number < 5;
				})
				.then(facts -> {
					Integer number = facts.get(KEY_NUMBER);
					System.out.printf("number[%d] between (0, 5)\n", number);
				})
				.build());

		// else --> else
		rules.add(new RuleBuilder()
				.name("number > 0 && number >= 5")
				.when(facts -> {
					Integer number = facts.get(KEY_NUMBER);
					return number > 0 && number >= 5;
				})
				.then(facts -> {
					Integer number = facts.get(KEY_NUMBER);
					System.out.printf("number[%d] between [5, +inf)\n", number);
				})
				.build());

		return rules;
	}
}
