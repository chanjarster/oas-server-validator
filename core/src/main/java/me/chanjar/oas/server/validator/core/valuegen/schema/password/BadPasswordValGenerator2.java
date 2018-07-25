package me.chanjar.oas.server.validator.core.valuegen.schema.password;

import io.swagger.v3.oas.models.media.PasswordSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.PasswordVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValCons;
import org.apache.commons.lang3.StringUtils;

/**
 * Support maxLength != null
 */
public class BadPasswordValGenerator2 implements PasswordValGenerator {

  @Override
  public boolean supports(Schema schema, SchemaValCons cons) {
    return PasswordSchemaSupport.supports(schema) && schema.getMaxLength() != null;
  }

  @Override
  public PasswordVal generate(PasswordSchema schema,
      SchemaValCons cons) {
    return new PasswordVal(StringUtils.repeat('p', schema.getMaxLength() + 1));
  }

}
