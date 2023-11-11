package com.gagauz.demo.observation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.AbstractEnvironment;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class ObservationServerApplication {
	private static final Logger LOG = LoggerFactory.getLogger(ObservationServerApplication.class);

	public static void main(String[] args) {
		System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "prod");
		SpringApplication.run(ObservationServerApplication.class, args);
	}

}