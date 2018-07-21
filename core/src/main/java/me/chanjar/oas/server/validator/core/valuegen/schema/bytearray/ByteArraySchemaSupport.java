package me.chanjar.oas.server.validator.core.valuegen.schema.bytearray;

import io.swagger.v3.oas.models.media.ByteArraySchema;
import io.swagger.v3.oas.models.media.Schema;

public abstract class ByteArraySchemaSupport {

  private ByteArraySchemaSupport() {
    // singleton
  }

  /**
   * @param schema
   * @return
   */
  public static final boolean supports(Schema schema) {
    return ByteArraySchema.class.equals(schema.getClass());
  }
}
