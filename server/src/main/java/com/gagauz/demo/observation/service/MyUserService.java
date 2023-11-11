package com.gagauz.demo.observation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xl0e.utils.Delay;

import io.micrometer.observation.annotation.Observed;

@Service
@Observed
public class MyUserService {
	private static final Logger log = LoggerFactory.getLogger(MyUserService.class);

	// Example of using an annotation to observe methods
	// <user.name> will be used as a metric name
	// <getting-user-name> will be used as a span name
	// <userType=userType2> will be set as a tag for both metric & span
	public String userName(long userId) {
		log.info("Getting user name for user with id <{}>", userId);
		Delay.randomDelayMs(200);
		if (userId > 10 && userId < 15)
			throw new RuntimeException("Invalid user id:" + userId);
		if (userId > 50)
			return null;
		return "foo " + userId;
	}
}
