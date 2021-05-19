package com.vergilyn.examples.ruleengine.easyrules.eg0001;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * <blockquote>
 *   This tutorial is the hello world of easy rules.
 *   We will create a rule that is always triggered and that will <b>print "hello world"</b> to the console when executed.
 * </blockquote>
 *
 * @author vergilyn
 * @since 2021-05-19
 *
 * @see <a href="https://github.com/j-easy/easy-rules/wiki/hello-world">HelloWorldRule .java</a>
 */
class HelloworldEasyRulesTests {

	@Test
	@DisplayName("create a rules engine and fire this rule")
	public void helloworld(){
		// create facts
		Facts facts = new Facts();

		// create rules
		Rules rules = new Rules();
		rules.register(new HelloWorldRule());

		// create a rules engine and fire rules on known facts
		RulesEngine rulesEngine = new DefaultRulesEngine();
		rulesEngine.fire(rules, facts);
	}

	@Rule(name = "Hello World rule", description = "Always say hello world")
	public static class HelloWorldRule {

		@Condition
		public boolean when() {
			return true;
		}

		@Action
		public void then() throws Exception {
			System.out.println("hello world");
		}
	}
}
