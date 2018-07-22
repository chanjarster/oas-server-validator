package me.chanjar.oas.server.validator.core.valuegen.schema.password;

import io.swagger.v3.oas.models.media.PasswordSchema;
import io.swagger.v3.oas.models.media.Schema;

public abstract class PasswordSchemaSupport {

  private PasswordSchemaSupport() {
    // singleton
  }

  /**
   * pattern not supported
   *
   * @param schema
   * @return
   */
  public static final boolean supports(Schema schema) {
    return PasswordSchema.class.equals(schema.getClass());
  }
}
