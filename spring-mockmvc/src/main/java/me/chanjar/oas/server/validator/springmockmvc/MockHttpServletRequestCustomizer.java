package me.chanjar.oas.server.validator.springmockmvc;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@FunctionalInterface
public interface MockHttpServletRequestCustomizer {

  void customize(MockHttpServletRequestBuilder requestBuilder);

}
