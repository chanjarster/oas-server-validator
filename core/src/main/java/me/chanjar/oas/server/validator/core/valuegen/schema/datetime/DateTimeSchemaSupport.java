package me.chanjar.oas.server.validator.core.valuegen.schema.datetime;

import io.swagger.v3.oas.models.media.DateTimeSchema;
import io.swagger.v3.oas.models.media.Schema;

public abstract class DateTimeSchemaSupport {

  private DateTimeSchemaSupport() {
    // singleton
  }

  /**
   * @param schema
   * @return
   */
  public static final boolean supports(Schema schema) {
    return DateTimeSchema.class.equals(schema.getClass());
  }
}
