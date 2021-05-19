package com.vergilyn.examples.ruleengine.easyrules.eg0002.rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Priority;
import org.jeasy.rules.annotation.Rule;

@Rule
public class FizzRule {

	@Condition
	public boolean isFizz(@Fact("number") Integer number) {
		return number % 5 == 0;
	}

	@Action
	public void printFizz() {
		System.out.println("fizz");
	}

	@Priority
	public int getPriority() {
		return 1;
	}
}
