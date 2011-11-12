package com.example;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConfiguratorTest extends CamelSpringTestSupport {

	@Override
	protected AbstractApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext("configurator.xml");
	}

	@Test
	public void testConfiguratorRout() throws Exception {
		String model = "a7";
		model = URLEncoder.encode(model, "UTF-8");

		MockEndpoint endPoint = getMockEndpoint("mock:finish");
		endPoint.setExpectedMessageCount(1);

		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("Model", model);

		sendBody("direct:start", null, headers);
		endPoint.assertIsSatisfied();
	}

}
