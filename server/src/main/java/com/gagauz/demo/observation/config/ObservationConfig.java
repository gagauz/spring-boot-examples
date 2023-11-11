package com.gagauz.demo.observation.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;

@Configuration(proxyBeanMethods = false)
public class ObservationConfig {

	@Bean
	ObservationRegistry observationRegistry() {
		return ObservationRegistry.create();
	}

	@Bean
	ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
		return new ObservedAspect(observationRegistry, this::skipControllers);
	}

	private boolean skipControllers(ProceedingJoinPoint pjp) {
		var targetClass = pjp.getTarget().getClass();
		return targetClass.isAnnotationPresent(RestController.class)
				|| targetClass.isAnnotationPresent(Controller.class);
	}

}
