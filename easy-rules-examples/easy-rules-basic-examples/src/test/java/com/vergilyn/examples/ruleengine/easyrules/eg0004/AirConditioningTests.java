package com.vergilyn.examples.ruleengine.easyrules.eg0004;

import org.jeasy.rules.api.Action;
import org.jeasy.rules.api.Condition;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.InferenceRulesEngine;
import org.jeasy.rules.core.RuleBuilder;
import org.junit.jupiter.api.Test;

/**
 * <blockquote>
 *   This tutorial shows an example of how to use the {@linkplain InferenceRulesEngine}. <br/>
 *   We would like to implement a simple air conditioning system with the following requirement: <br/>
 *   Given the temperature(气温) as a {@code fact},
 *   when it is <b>hot</b>, then the system should cool the air <b>until a certain degree</b>. <br/>
 *   We consider <b>25 degrees</b> as the threshold for "hot".
 * </blockquote>
 *
 * @author vergilyn
 * @since 2021-05-19
 *
 * @see <a href="https://github.com/j-easy/easy-rules/wiki/air-conditioning">air-conditioning</a>
 */
class AirConditioningTests {
	private static final String KEY_TEMPERATURE = "temperature";
	private static final int HOT_THRESHOLD = 25;

	@Test
	void airConditioning(){
		// define facts
		Facts facts = new Facts();
		facts.put(KEY_TEMPERATURE, 30);

		// define rules
		Rule airConditioningRule = new RuleBuilder()
				.name("air conditioning rule")
				// create a condition to define when it is "hot".
				.when(new Condition() {
					@Override
					public boolean evaluate(Facts facts) {
						Integer temperature = facts.get(KEY_TEMPERATURE);
						return temperature > HOT_THRESHOLD;
					}
				})
				// Then an action to decrease the temperature.
				.then(new Action() {
					@Override
					public void execute(Facts facts) throws Exception {
						Integer temperature = facts.get(KEY_TEMPERATURE);
						System.out.printf("It is hot[%d]! cooling air..\n", temperature);
						facts.put(KEY_TEMPERATURE, temperature - 1);
					}
				})
				.build();

		Rules rules = new Rules();
		rules.register(airConditioningRule);

		// fire rules on known facts
		RulesEngine rulesEngine = new InferenceRulesEngine();
		rulesEngine.fire(rules, facts);
	}
}
