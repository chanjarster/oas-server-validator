package me.chanjar.oas.server.validator.core.valuegen.schema.email;

import io.swagger.v3.oas.models.media.EmailSchema;
import io.swagger.v3.oas.models.media.Schema;

public abstract class EmailSchemaSupport {

  private EmailSchemaSupport() {
    // singleton
  }

  /**
   * @param schema
   * @return
   */
  public static final boolean supports(Schema schema) {
    return EmailSchema.class.equals(schema.getClass());
  }
}
