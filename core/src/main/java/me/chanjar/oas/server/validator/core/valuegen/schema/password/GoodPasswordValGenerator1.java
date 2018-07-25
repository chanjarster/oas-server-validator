package me.chanjar.oas.server.validator.core.valuegen.schema.password;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.PasswordSchema;
import me.chanjar.oas.server.validator.core.value.schema.PasswordVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValCons;

/**
 * Support minLength == null && maxLength == null
 */
public class GoodPasswordValGenerator1 implements PasswordValGenerator {

  @Override
  public boolean supports(Schema schema, SchemaValCons cons) {
    return PasswordSchemaSupport.supports(schema) && schema.getMinLength() == null & schema.getMaxLength() == null;
  }

  @Override
  public PasswordVal generate(PasswordSchema schema,
      SchemaValCons cons) {
    return new PasswordVal("password");
  }

}
