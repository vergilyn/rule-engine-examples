package com.vergilyn.examples.ruleengine.easyrules.eg0005;

import com.vergilyn.examples.ruleengine.easyrules.eg0005.web.IndexController;
import com.vergilyn.examples.ruleengine.easyrules.eg0005.web.WebApplication;

import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.vergilyn.examples.ruleengine.easyrules.eg0005.web.SuspiciousRequestRule.SUSPICIOUS;

/**
 *
 * <blockquote>
 *   In this tutorial, we will use Easy Rules in a web application. <br/>
 *   The goal is to write a rule to deny-access(拒绝访问) to suspicious(可疑的) requests. <br/>
 *   A suspicious request may be detected based on IP address, user agent, or any other criteria. <br/>
 *   To keep this tutorial simple, <b>we will consider a request as suspicious if it contains a request parameter suspicious.</b>
 * </blockquote>
 *
 * Question: "MockMvc not invoked `Filter#init()`?"
 * <pre>
 *    `WebApplication`启动的时候走的是`ServletWebServerApplicationContext`，会执行`#init()`;
 *    但是，spring-boot-test进行Mock的时候是`SpringBootMockServletContext`，不会执行`#init()`。
 * </pre>
 *
 * Answer: <a href="https://stackoverflow.com/questions/20819130/whats-required-to-make-mockmvc-test-a-filters-init-routine/49761265">
 *          What's required to make mockMVC test a filter's init routine?</a>
 * <pre>
 *
 *     当`@SpringBootTest(webEnvironment = WebEnvironment.MOCK)`(默认MOCK)时，使用`SpringBootMockServletContext`。
 *     所以，最简单的方式是改成`WebEnvironment.RANDOM_PORT`
 * </pre>
 *
 * @author vergilyn
 * @since 2021-05-19
 *
 * @see <a href="https://github.com/j-easy/easy-rules/wiki/web-app">web app</a>
 */
@SpringBootTest(classes = WebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class WebAppTests {
	private static final String URL_TEMPLATE = "/index/get";
	@Autowired
	private MockMvc mockMvc;

	@SneakyThrows
	@Test
	void denyAccess(){
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(URL_TEMPLATE)
				.param(SUSPICIOUS, "true")
				.accept(MediaType.ALL))
				.andReturn();

		MockHttpServletResponse response = mvcResult.getResponse();

		final int status = response.getStatus();
		final String content = response.getContentAsString();

		System.out.printf("[vergilyn] response >>>> status: %d, content: %s \n", status, content);

		Assertions.assertThat(status).isEqualTo(HttpStatus.OK.value());
		Assertions.assertThat(content).isEqualTo(IndexController.DENIED_MSG);
	}

	@SneakyThrows
	@Test
	void success(){
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(URL_TEMPLATE)
									.accept(MediaType.ALL))
									.andReturn();

		MockHttpServletResponse response = mvcResult.getResponse();

		final int status = response.getStatus();
		final String content = response.getContentAsString();

		System.out.printf("[vergilyn] response >>>> status: %d, content: %s \n", status, content);

		Assertions.assertThat(status).isEqualTo(HttpStatus.OK.value());
		Assertions.assertThat(content).isEqualTo(IndexController.SUCCESS_MSG);
	}
}
