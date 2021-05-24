package com.vergilyn.examples.ruleengine.drools.eg0001;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * @author vergilyn
 * @since 2021-05-21
 *
 * @see <a href="https://github.com/kiegroup/drools/tree/master/drools-examples/src/main/java/org/drools/examples/helloworld">examples, helloworld</a>
 */
public class HelloworldDroolsTests {

	@Test
	void helloworld() {
		// From the kie services, a container is created from the classpath
		KieServices ks = KieServices.get();
		KieContainer kc = ks.getKieClasspathContainer();

		execute(ks, kc);
	}

	public static void execute(KieServices ks, KieContainer kc) {
		// From the container, a session is created based on
		// its definition and configuration in the META-INF/kmodule.xml file
		KieSession ksession = kc.newKieSession("HelloWorldKS");

		// Once the session is created, the application can interact with it
		// In this case it is setting a global as defined in the `HelloWorld.drl` file
		ksession.setGlobal("list", new ArrayList<Object>());

		// The application can also setup listeners
		ksession.addEventListener(new DebugAgendaEventListener());
		ksession.addEventListener(new DebugRuleRuntimeEventListener());

		// Set up a file based audit logger
		KieRuntimeLogger logger = ks.getLoggers().newFileLogger(ksession, "./helloworld");

		// To set up a ThreadedFileLogger, so that the audit view reflects events whilst debugging,
		// uncomment the next line
		// KieRuntimeLogger logger = ks.getLoggers().newThreadedFileLogger( ksession, "./helloworld", 1000 );

		// The application can insert facts into the session
		final Message message = new Message();
		message.setMessage("Hello World");
		message.setStatus(Message.HELLO);
		ksession.insert(message);

		// and fire the rules
		ksession.fireAllRules();

		// Close logger
		logger.close();

		// and then dispose the session
		ksession.dispose();
	}

	@Data
	@NoArgsConstructor
	public static class Message {
		public static final int HELLO = 0;
		public static final int GOODBYE = 1;

		private String message;
		private int status;

		public static Message doSomething(Message message) {
			return message;
		}

		public boolean isSomething(String msg, List<Object> list) {
			list.add(this);
			return this.message.equals(msg);
		}
	}
}
