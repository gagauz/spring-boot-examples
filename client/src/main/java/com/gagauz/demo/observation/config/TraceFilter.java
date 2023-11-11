package com.gagauz.demo.observation.config;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import static java.util.Optional.ofNullable;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TraceFilter implements Filter {


	private static final String SESSION_ID = "sessionId";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		var request = (HttpServletRequest) servletRequest;
		var response = (HttpServletResponse) servletResponse;

		MDC.put(SESSION_ID, getOrCreateSessionId(request));
		try {
			filterChain.doFilter(request, response);

		} finally {
			MDC.remove(SESSION_ID);
		}
		// call next filter in the filter chain

	}

	private String getOrCreateSessionId(HttpServletRequest request) {
		return ofNullable(request.getSession(false))
				.map(HttpSession::getId)
				.orElse(StringUtils.EMPTY);
	}

	@Override
	public void destroy() {

	}
}