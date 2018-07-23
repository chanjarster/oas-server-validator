package me.chanjar.oas.server.validator.core.valuegen.schema.password;

import io.swagger.v3.oas.models.media.PasswordSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.IntegerVal;
import me.chanjar.oas.server.validator.core.value.schema.PasswordVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.PrimitiveSchemaValGenerationService;

/**
 * {@link PasswordVal} generator for {@link PasswordSchema}<br>
 * Note: pattern not supported.<br>
 * doc: <a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5">JSON Schema Validation</a>
 */
public class PasswordValGenerationService extends PrimitiveSchemaValGenerationService<PasswordSchema, PasswordVal> {

  @Override
  public boolean supports(Schema schema) {
    return PasswordSchemaSupport.supports(schema);
  }

  @Override
  protected Class<PasswordSchema> getSchemaValClass() {
    return PasswordSchema.class;
  }

  @Override
  protected PasswordVal createNullSchemaVal() {
    return new PasswordVal(null);
  }

  @Override
  protected SchemaVal createDifferentTypeSchemaVal() {
    return new IntegerVal(0);
  }

}
