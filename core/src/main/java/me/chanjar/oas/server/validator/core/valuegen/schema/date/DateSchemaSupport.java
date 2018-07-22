package me.chanjar.oas.server.validator.core.valuegen.schema.date;

import io.swagger.v3.oas.models.media.DateSchema;
import io.swagger.v3.oas.models.media.Schema;

public abstract class DateSchemaSupport {

  private DateSchemaSupport() {
    // singleton
  }

  /**
   * @param schema
   * @return
   */
  public static final boolean supports(Schema schema) {
    return DateSchema.class.equals(schema.getClass());
  }
}
