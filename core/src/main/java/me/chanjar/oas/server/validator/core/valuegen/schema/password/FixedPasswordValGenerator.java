package me.chanjar.oas.server.validator.core.valuegen.schema.password;

import io.swagger.v3.oas.models.media.PasswordSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.PasswordVal;

/**
 * A {@link PasswordValGenerator} which returns a fixed value
 */
public class FixedPasswordValGenerator implements PasswordValGenerator {

  private final PasswordVal fixedVal;

  public FixedPasswordValGenerator(String password) {
    this.fixedVal = new PasswordVal(password);
  }

  @Override
  public boolean supports(Schema schema) {
    return PasswordSchemaSupport.supports(schema);
  }

  @Override
  public PasswordVal generate(PasswordSchema schema) {
    return fixedVal;
  }
}
