package me.chanjar.oas.server.validator.core.valuegen.schema.array;

import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.Schema;

public abstract class ArraySchemaSupport {

  private ArraySchemaSupport() {
    // singleton
  }

  public static final boolean supports(Schema schema) {
    return ArraySchema.class.equals(schema.getClass());
  }
}
