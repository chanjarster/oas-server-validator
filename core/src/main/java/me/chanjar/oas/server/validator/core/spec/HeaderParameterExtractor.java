package me.chanjar.oas.server.validator.core.spec;

import io.swagger.v3.oas.models.parameters.HeaderParameter;

public class HeaderParameterExtractor extends AbstractParameterExtractor<HeaderParameter> {

  public HeaderParameterExtractor() {
    super("header");
  }

}
