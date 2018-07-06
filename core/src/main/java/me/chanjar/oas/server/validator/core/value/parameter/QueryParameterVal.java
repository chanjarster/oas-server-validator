package me.chanjar.oas.server.validator.core.value.parameter;

import me.chanjar.oas.server.validator.core.value.mediatype.MediaTypeVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;

public class QueryParameterVal extends ParameterVal {

  public QueryParameterVal(String name) {
    super(name);
  }

  public QueryParameterVal(String name, SchemaVal schemaVal) {
    super(name, schemaVal);
  }

  public QueryParameterVal(String name, MediaTypeVal mediaTypeVal) {
    super(name, mediaTypeVal);
  }
}
