package me.chanjar.oas.server.validator.core.valuegen.schema.binary;

import io.swagger.v3.oas.models.media.BinarySchema;

public abstract class BinarySchemaSupport {

  private BinarySchemaSupport() {
    // singleton
  }

  /**
   * @param schema
   * @return
   */
  public static final boolean supports(BinarySchema schema) {
    return BinarySchema.class.equals(schema.getClass());
  }
}
