package me.chanjar.oas.server.validator.core.valuegen.schema.string;

import io.swagger.v3.oas.models.media.StringSchema;
import org.apache.commons.lang3.StringUtils;


public abstract class StringSchemaSupport {

  private StringSchemaSupport() {
    // singleton
  }

  /**
   * pattern not supported
   * @param schema
   * @return
   */
  public static final boolean supports(StringSchema schema) {
    return StringSchema.class.equals(schema.getClass()) && StringUtils.isBlank(schema.getPattern());
  }
}
