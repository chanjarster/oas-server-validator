package me.chanjar.oas.server.validator.core.value.parameter;

import me.chanjar.oas.server.validator.core.value.mediatype.MediaTypeVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;

public class ParameterVal {

  private final String name;

  private final SchemaVal schemaVal;

  private final MediaTypeVal mediaTypeVal;

  /**
   * Whether to ignoring this QueryParameterVal when building uri.
   */
  private final boolean ignoreMe;

  /**
   * Constructs a ParameterVal which ignoreMe == true
   * @param name
   */
  public ParameterVal(String name) {
    this.name = name;
    this.schemaVal = null;
    this.mediaTypeVal = null;
    this.ignoreMe = true;
  }

  public ParameterVal(String name, SchemaVal schemaVal) {
    this.name = name;
    this.schemaVal = schemaVal;
    this.ignoreMe = false;
    this.mediaTypeVal = null;
  }

  public ParameterVal(String name, MediaTypeVal mediaTypeVal) {
    this.name = name;
    this.mediaTypeVal = mediaTypeVal;
    this.ignoreMe = false;
    this.schemaVal = null;
  }

  public String getName() {
    return name;
  }

  public SchemaVal getSchemaVal() {
    return schemaVal;
  }

  public boolean isIgnoreMe() {
    return ignoreMe;
  }

  public MediaTypeVal getMediaTypeVal() {
    return mediaTypeVal;
  }

}
