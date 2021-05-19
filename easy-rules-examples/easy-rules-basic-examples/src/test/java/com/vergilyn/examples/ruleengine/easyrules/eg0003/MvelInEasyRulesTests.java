package com.vergilyn.examples.ruleengine.easyrules.eg0003;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRule;
import org.jeasy.rules.mvel.MVELRuleFactory;
import org.jeasy.rules.support.reader.YamlRuleDefinitionReader;
import org.junit.jupiter.api.Test;

/**
 * <blockquote>
 *   This tutorial shows how to use <a href="https://www.github.com/mvel/mvel">MVEL</a> with Easy Rules.
 *   MVEL support was added in v3.1 and is provided through the easy-rules-mvel module.
 *   <br/>
 *   This module contains <b>`APIs & Yml`</b> to define rules using MVEL. We will use these APIs here.
 *   The goal is to implement a simple shop application with the following requirement:
 *   <br/>&emsp;deny children from buying alcohol. The minimum legal age to be considered as adult is 18.
 * </blockquote>
 *
 *
 * @author vergilyn
 * @since 2021-05-19
 *
 * @see <a href="https://github.com/j-easy/easy-rules/wiki/shop">
 *     how to use <b>MVEL</b> with Easy Rules.</a>
 * @see <a href="https://www.github.com/mvel/mvel"><b>MVEL</b></a>
 */
class MvelInEasyRulesTests {
	private static final String ALCOHOL_RULE_YML = "alcohol-rule.yml";

	@Test
	@SneakyThrows
	void mvelRule(){
		//create a person instance (fact)
		Person tom = new Person("Tom", 14);
		Facts facts = new Facts();
		facts.put("person", tom);

		// create rules-01, by MVEL program-api.
		MVELRule ageRule = new MVELRule()
				.name("age rule")
				.description("Check if person's age is > 18 and mark the person as adult")
				.priority(1)
				// It will check if the person's `age > 18`, and mark `adult = true`.
				.when("person.age >= 18")
				.then("person.setAdult(true);");

		/* create rules-02, by yml.
		 * vergilyn-comment, 2021-05-19 >>>>
		 *   easy-rules的Rule逻辑是：`condition = true`则执行`actions`，并不存在`condition = false`时，执行`Rule#xx()`。
		 *   此时，可以再写一个Rule来达到此目的。
		 */
		MVELRuleFactory ruleFactory = new MVELRuleFactory(new YamlRuleDefinitionReader());
		Rule alcoholRule = ruleFactory.createRule(readRuleYml());

		// create a rule set
		Rules rules = new Rules();
		rules.register(ageRule);
		rules.register(alcoholRule);

		//create a default rules engine and fire rules on known facts
		RulesEngine rulesEngine = new DefaultRulesEngine();

		System.out.println("Tom: Hi! can I have some Vodka please?");
		rulesEngine.fire(rules, facts);
	}

	private Reader readRuleYml(){
		final InputStream inputStream = this.getClass().getResourceAsStream(ALCOHOL_RULE_YML);
		return new InputStreamReader(inputStream);
	}

	@Data
	@NoArgsConstructor
	public static class Person {
		private String name;

		private int age;

		/** true: 成年人 */
		private boolean adult;

		public Person(String name, int age) {
			this.name = name;
			this.age = age;
		}
	}
}
