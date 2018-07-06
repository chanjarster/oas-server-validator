package me.chanjar.oas.server.validator.core.spec;

import io.swagger.v3.oas.models.parameters.CookieParameter;

public class CookieParameterExtractor extends AbstractParameterExtractor<CookieParameter> {

  public CookieParameterExtractor() {
    super("cookie");
  }

}
