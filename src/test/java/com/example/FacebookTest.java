package com.example;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FacebookTest extends CamelSpringTestSupport {

	@Override
	protected AbstractApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext("facebook.xml");
	}

	@Test
	public void testFacebookRout() throws Exception {
		// This will probably need to be updated with a token attached to the example URLs at this URL:
		// https://developers.facebook.com/docs/reference/api/
		String token = "AAAAAAITEghMBAIeBeE8AfcXTjqL1x4GFI03ZAll5ZBvICRPRrpCxRcz8TqhQd40hAyZBhGELzt8k6fpN8SZAPYZCw84BZCVYV3T6XBGudJ3wZDZD";
		token = URLEncoder.encode(token, "UTF-8");
		MockEndpoint endPoint = getMockEndpoint("mock:finish");
		endPoint.setExpectedMessageCount(1);
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("AccessToken", token);
		sendBody("direct:start", null, headers);
		endPoint.assertIsSatisfied();
	}

}
