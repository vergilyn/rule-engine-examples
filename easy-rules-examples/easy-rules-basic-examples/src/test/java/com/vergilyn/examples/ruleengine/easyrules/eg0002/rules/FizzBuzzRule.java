package com.vergilyn.examples.ruleengine.easyrules.eg0002.rules;

import org.jeasy.rules.api.Rule;
import org.jeasy.rules.support.composite.UnitRuleGroup;

public class FizzBuzzRule extends UnitRuleGroup {

	/**
	 * @param rules `Object` implements {@linkplain Rule}.
	 *              OR use easy-rule annotation-mode, like {@linkplain BuzzRule}.
	 */
	public FizzBuzzRule(Object... rules) {
		for (Object rule : rules) {
			addRule(rule);
		}
	}

	@Override
	public int getPriority() {
		return 0;
	}
}