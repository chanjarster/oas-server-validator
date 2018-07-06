package me.chanjar.oas.server.validator.core.value.parameter;

import me.chanjar.oas.server.validator.core.value.mediatype.MediaTypeVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;

public class CookieParameterVal extends ParameterVal {

  public CookieParameterVal(String name) {
    super(name);
  }

  public CookieParameterVal(String name, SchemaVal schemaVal) {
    super(name, schemaVal);
  }

  public CookieParameterVal(String name, MediaTypeVal mediaTypeVal) {
    super(name, mediaTypeVal);
  }
}
