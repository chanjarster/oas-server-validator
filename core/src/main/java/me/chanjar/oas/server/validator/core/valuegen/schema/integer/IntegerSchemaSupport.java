package me.chanjar.oas.server.validator.core.valuegen.schema.integer;

import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.Schema;

public abstract class IntegerSchemaSupport {

  private IntegerSchemaSupport() {
    // singleton
  }

  public static final boolean supports(Schema schema) {
    return IntegerSchema.class.equals(schema.getClass());
  }
}
