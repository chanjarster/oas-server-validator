package me.chanjar.oas.server.validator.core.loader.spec;

import io.swagger.v3.oas.models.parameters.QueryParameter;

public class QueryParameterExtractor extends AbstractParameterExtractor<QueryParameter> {

  public QueryParameterExtractor() {
    super("query");
  }

}
