package com.gagauz.demo.observation.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.gagauz.demo.observation.service.MyUserService;

@RestController
public class MyController {
	private static final Logger log = LoggerFactory.getLogger(MyController.class);

	@Autowired
	private MyUserService myUserService;

	@GetMapping("/user/{userId}")
	public Object userName(@PathVariable("userId") Long userId) {
		log.info("Got a request");
		var res = myUserService.userName(userId);
		if (null == res) {
			return ResponseEntity.notFound();
		}
		return ResponseEntity.ok(res);
	}
}
