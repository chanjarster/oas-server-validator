package me.chanjar.oas.server.validator.core.valuegen.schema.number;

import io.swagger.v3.oas.models.media.NumberSchema;
import io.swagger.v3.oas.models.media.Schema;

public abstract class NumberSchemaSupport {

  private NumberSchemaSupport() {
    // singleton
  }

  public static final boolean supports(Schema schema) {
    return NumberSchema.class.equals(schema.getClass());
  }
}
