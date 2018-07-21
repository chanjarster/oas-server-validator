package me.chanjar.oas.server.validator.core.valuegen.schema.binary;

import io.swagger.v3.oas.models.media.BinarySchema;
import io.swagger.v3.oas.models.media.Schema;

public abstract class BinarySchemaSupport {

  private BinarySchemaSupport() {
    // singleton
  }

  /**
   * @param schema
   * @return
   */
  public static final boolean supports(Schema schema) {
    return BinarySchema.class.equals(schema.getClass());
  }
}
