package me.chanjar.oas.server.validator.core.valuegen.schema.uuid;

import io.swagger.v3.oas.models.media.UUIDSchema;
import io.swagger.v3.oas.models.media.Schema;

public abstract class UUIDSchemaSupport {

  private UUIDSchemaSupport() {
    // singleton
  }

  /**
   * pattern not supported
   *
   * @param schema
   * @return
   */
  public static final boolean supports(Schema schema) {
    return UUIDSchema.class.equals(schema.getClass());
  }
}
