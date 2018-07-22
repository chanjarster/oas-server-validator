package me.chanjar.oas.server.validator.core.valuegen.schema.bool;

import io.swagger.v3.oas.models.media.BooleanSchema;
import io.swagger.v3.oas.models.media.Schema;

public abstract class BooleanSchemaSupport {

  private BooleanSchemaSupport() {
    // singleton
  }

  public static final boolean supports(Schema schema) {
    return BooleanSchema.class.equals(schema.getClass());
  }
}
