package me.chanjar.oas.server.validator.core.value.parameter;

import me.chanjar.oas.server.validator.core.value.mediatype.MediaTypeVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;

public class PathParameterVal extends ParameterVal {

  public PathParameterVal(String name) {
    super(name);
  }

  public PathParameterVal(String name, SchemaVal schemaVal) {
    super(name, schemaVal);
  }

  public PathParameterVal(String name, MediaTypeVal mediaTypeVal) {
    super(name, mediaTypeVal);
  }
}
