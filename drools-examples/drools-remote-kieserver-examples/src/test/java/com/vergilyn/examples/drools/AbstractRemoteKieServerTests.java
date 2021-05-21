package com.vergilyn.examples.drools;

/**
 *
 * @author vergilyn
 * @since 2021-05-21
 *
 * @see <a href="https://docs.jboss.org/drools/release/7.54.0.Final/drools-docs/html_single/index.html#controller-java-api-con_kie-apis">
 *          Drools controller Java client API for KIE Server templates and instances</a>
 */
public abstract class AbstractRemoteKieServerTests {



	protected interface KieServerConst{
		String URL_REST = "http://localhost:18080/kie-server/services/rest/server";

		String USER = "kieserver";
		String PASSWORD = "kieserver1!";
	}

	/**
	 * docker `business-central` Users and roles:
	 * <pre>
	 *     USER        PASSWORD    ROLE
	 *     *************************************************
	 *     admin       admin       admin,analyst,kiemgmt
	 *     krisv       krisv       admin,analyst
	 *     john        john        analyst,Accounting,PM
	 *     sales-rep   sales-rep   analyst,sales
	 *     katy        katy        analyst,HR
	 *     jack        jack        analyst,IT
	 * </pre>
	 */
	protected interface BusinessCentralConst{
		String URL_REST = "http://localhost:18081/business-central/rest/controller";

		String USER_ADMIN = "admin";
		String USER_ADMIN_PASSWORD = "admin";
	}


}
