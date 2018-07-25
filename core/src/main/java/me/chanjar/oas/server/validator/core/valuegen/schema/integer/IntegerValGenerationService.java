package me.chanjar.oas.server.validator.core.valuegen.schema.integer;

import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.IntegerVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.PrimitiveSchemaValGenerationService;

/**
 * {@link IntegerVal} generator for {@link IntegerSchema}<br>
 * doc: <a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5">JSON Schema Validation</a>
 */
public class IntegerValGenerationService extends PrimitiveSchemaValGenerationService<IntegerSchema> {

  @Override
  public boolean supports(Schema schema) {
    return IntegerSchemaSupport.supports(schema);
  }

}
