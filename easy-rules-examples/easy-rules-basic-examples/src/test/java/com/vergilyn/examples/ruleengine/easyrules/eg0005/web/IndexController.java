package com.vergilyn.examples.ruleengine.easyrules.eg0005.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class IndexController {
	public static final String SUCCESS_MSG = "Welcome!";
	public static final String DENIED_MSG = "Access denied!";

	@GetMapping("/get")
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (isSuspicious(request)) {
			return DENIED_MSG;
		} else {
			return SUCCESS_MSG;
		}
	}

	private boolean isSuspicious(HttpServletRequest request) {
		return request.getAttribute(SuspiciousRequestRule.SUSPICIOUS) != null;
	}
}