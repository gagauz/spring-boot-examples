package com.gagauz.demo.observation.handler;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.observation.ServerRequestObservationContext;
import org.springframework.stereotype.Component;

import io.micrometer.common.KeyValues;
import io.micrometer.observation.Observation;
import io.micrometer.observation.Observation.Context;
import io.micrometer.observation.ObservationHandler;

@Component
public class MyHandler implements ObservationHandler<Observation.Context> {

	private static final Logger log = LoggerFactory.getLogger(MyHandler.class);

	@Override
	public void onStart(Observation.Context context) {
		log.info("start: [{} {}], high: [{}], low: [{}]", context.getName(),
				context.getContextualName(),
				dumpKeyValues(context.getHighCardinalityKeyValues()),
				dumpKeyValues(context.getLowCardinalityKeyValues()));
	}

	@Override
	public void onStop(Observation.Context context) {
		log.info(" stop: [{} {}], high: [{}], low: [{}]", context.getName(),
				context.getContextualName(),
				dumpKeyValues(context.getHighCardinalityKeyValues()),
				dumpKeyValues(context.getLowCardinalityKeyValues()));
	}

	@Override
	public void onError(Context context) {
		log.error("<< Ctx name: [{} {}], high: [{}], low: [{}]", context.getName(),
				context.getContextualName(),
				dumpKeyValues(context.getHighCardinalityKeyValues()),
				dumpKeyValues(context.getLowCardinalityKeyValues()));
	}

	@Override
	public boolean supportsContext(Observation.Context context) {
		return !(context instanceof ServerRequestObservationContext);
	}

	private String dumpKeyValues(KeyValues keyValues) {
		return keyValues.stream()
				.map(kv -> kv.getKey() + '=' + kv.getValue())
				.collect(Collectors.joining(", "));
	}
}