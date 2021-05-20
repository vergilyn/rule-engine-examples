package com.vergilyn.examples.ruleengine.easyrules.eg0006;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;
import com.vergilyn.examples.ruleengine.easyrules.eg0002.rules.BuzzRule;
import com.vergilyn.examples.ruleengine.easyrules.eg0002.rules.FizzBuzzRule;
import com.vergilyn.examples.ruleengine.easyrules.eg0002.rules.FizzRule;
import com.vergilyn.examples.ruleengine.easyrules.eg0002.rules.NonFizzBuzzRule;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.RuleListener;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngineParameters;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.junit.jupiter.api.Test;

/**
 * 1) listeners的调用顺序由{@linkplain ArrayList#add(Object)}决定，<b>无法设置listener的优先级</b>；
 *  <blockquote>Listeners will be executed in their registration order.</blockquote>
 * <br/>
 * 2) 可以通过{@linkplain RuleListener#beforeEvaluate(Rule, Facts)} 返回`false` 阻止某个Rule执行；<br/>
 * 3) <blockquote>When a composite rule is used, the listener is invoked <b>around the composite rule</b>
 *          and <b>not around its composing rules</b>. </blockquote>
 *
 * @author vergilyn
 * @since 2021-05-20
 *
 * @see <a href="https://github.com/j-easy/easy-rules/wiki/defining-rules-listener">defining rules listener</a>
 */
class RuleListenerTests {

	@Test
	void listener(){
		// create a rules engine
		RulesEngineParameters parameters = new RulesEngineParameters().skipOnFirstAppliedRule(true);
		DefaultRulesEngine rulesEngine = new DefaultRulesEngine(parameters);

		// registry rule-listener
		rulesEngine.registerRuleListeners(buildListeners());

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
		facts.put("number", 1);
		rulesEngine.fire(rules, facts);

		facts.put("number", 5);
		rulesEngine.fire(rules, facts);

		facts.put("number", 7);
		rulesEngine.fire(rules, facts);

		facts.put("number", 35);
		rulesEngine.fire(rules, facts);
	}

	List<RuleListener> buildListeners(){
		List<RuleListener> listeners = Lists.newArrayListWithCapacity(2);

		listeners.add(new RuleListener() {
			@Override
			public void onSuccess(Rule rule, Facts facts) {
				System.out.printf("[vergilyn][listener-01] >>>> rule[%s] execute success.\n", rule.getName());
			}
		});

		listeners.add(new RuleListener() {
			@Override
			public void onSuccess(Rule rule, Facts facts) {
				System.out.printf("[vergilyn][listener-02] >>>> rule[%s] execute success.\n", rule.getName());
			}
		});

		return listeners;
	}
}
