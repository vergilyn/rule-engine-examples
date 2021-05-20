package com.vergilyn.examples.ruleengine.easyrules.eg0005.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.jeasy.rules.api.Fact;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.stereotype.Component;

@Component
@WebFilter(urlPatterns = "/*")
public class SuspiciousRequestFilter implements Filter {

	private Rules rules;
	private RulesEngine rulesEngine;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("[vergilyn] >>>> execute Filter#init()...");

		rulesEngine = new DefaultRulesEngine();
		rules = new Rules();
		rules.register(new SuspiciousRequestRule());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws
			IOException, ServletException {
		Facts facts = new Facts();
		// facts.add("request", request);
		facts.add(new Fact<>("request", request));

		rulesEngine.fire(rules, facts);
		filterChain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}
}