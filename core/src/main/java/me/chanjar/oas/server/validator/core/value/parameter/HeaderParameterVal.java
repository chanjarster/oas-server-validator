package me.chanjar.oas.server.validator.core.value.parameter;

import me.chanjar.oas.server.validator.core.value.mediatype.MediaTypeVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;

public class HeaderParameterVal extends ParameterVal {

  public HeaderParameterVal(String name) {
    super(name);
  }

  public HeaderParameterVal(String name, SchemaVal schemaVal) {
    super(name, schemaVal);
  }

  public HeaderParameterVal(String name, MediaTypeVal mediaTypeVal) {
    super(name, mediaTypeVal);
  }
}
