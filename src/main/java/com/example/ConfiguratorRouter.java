package com.example;

import org.apache.camel.builder.RouteBuilder;
import org.apache.log4j.Logger;

public class ConfiguratorRouter extends RouteBuilder {

	private static final Logger LOGGER = Logger.getLogger(ConfiguratorRouter.class);

	@Override
	public void configure() throws Exception {
		from("cxfrs://http://localhost:9000?resourceClasses=" + ConfiguratorResource.class.getName())
			.setBody(constant("SUCCESS"));
	}
}
