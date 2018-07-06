package me.chanjar.oas.server.validator.core.valuegen.schema.integer;

import io.swagger.v3.oas.models.media.IntegerSchema;

public abstract class IntegerSchemaSupport {

  private IntegerSchemaSupport() {
    // singleton
  }

  public static final boolean supports(IntegerSchema schema) {
    return IntegerSchema.class.equals(schema.getClass());
  }
}
