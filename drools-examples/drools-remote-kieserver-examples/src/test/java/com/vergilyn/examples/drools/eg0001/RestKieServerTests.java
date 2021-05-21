package com.vergilyn.examples.drools.eg0001;

import com.vergilyn.examples.drools.AbstractRemoteKieServerTests;

import org.junit.jupiter.api.Test;
import org.kie.server.controller.api.model.spec.ServerTemplate;
import org.kie.server.controller.api.model.spec.ServerTemplateList;
import org.kie.server.controller.client.KieServerControllerClient;
import org.kie.server.controller.client.KieServerControllerClientFactory;

/**
 * SEE: <a href="https://docs.jboss.org/drools/release/7.54.0.Final/drools-docs/html_single/index.html#controller-java-api-con_kie-apis">
 * Drools controller Java client API for KIE Server templates and instances</a>
 * -->`Client configuration example with REST`
 *
 * <p>
 *   高版本docker-image将`kie-server`和`business-central`进行了分离，是2个服务。
 * </p>
 * @author vergilyn
 * @since 2021-05-21
 */
public class RestKieServerTests extends AbstractRemoteKieServerTests {

	@Test
	public void connByRest() {
		String url = BusinessCentralConst.URL_REST;
		String user = BusinessCentralConst.USER_ADMIN;
		String password = BusinessCentralConst.USER_ADMIN_PASSWORD;

		KieServerControllerClient client = KieServerControllerClientFactory.newRestClient(url, user, password);

		ServerTemplateList serverTemplateList = client.listServerTemplates();
		ServerTemplate[] serverTemplates = serverTemplateList.getServerTemplates();

		System.out.printf("[vergilyn] >>>> Found %s server template(s) at controller url: %s",
					serverTemplates == null ? 0 : serverTemplates.length, url);
	}
}
