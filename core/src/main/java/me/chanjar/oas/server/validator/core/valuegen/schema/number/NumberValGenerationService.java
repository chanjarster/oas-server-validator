package me.chanjar.oas.server.validator.core.valuegen.schema.number;

import io.swagger.v3.oas.models.media.NumberSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.NumberVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.GeneratorDependingGenerationService;

/**
 * {@link NumberVal} generator for {@link NumberSchema}<br>
 * doc: <a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5">JSON Schema Validation</a>
 */
public class NumberValGenerationService extends GeneratorDependingGenerationService<NumberSchema> {

  @Override
  public boolean supports(Schema schema) {
    return NumberSchemaSupport.supports(schema);
  }

}
