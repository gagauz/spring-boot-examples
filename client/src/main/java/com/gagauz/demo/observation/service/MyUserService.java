package com.gagauz.demo.observation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.micrometer.observation.annotation.Observed;

@Service
@Observed
public class MyUserService {

	@Autowired
	RestTemplate restTemplate;

	public String userName(long userId) {
		return restTemplate.getForObject("http://localhost:8080/user/" + userId, String.class);
	}
}
