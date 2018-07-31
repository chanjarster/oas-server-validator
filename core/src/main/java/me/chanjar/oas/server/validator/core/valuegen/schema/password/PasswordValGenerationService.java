package me.chanjar.oas.server.validator.core.valuegen.schema.password;

import io.swagger.v3.oas.models.media.PasswordSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.PasswordVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.GeneratorDependingGenerationService;

/**
 * {@link PasswordVal} generator for {@link PasswordSchema}<br>
 * Note: pattern not supported.<br>
 * doc: <a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5">JSON Schema Validation</a>
 */
public class PasswordValGenerationService extends GeneratorDependingGenerationService<PasswordSchema> {

  @Override
  public boolean supports(Schema schema) {
    return PasswordSchemaSupport.supports(schema);
  }


}
