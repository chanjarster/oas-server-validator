package me.chanjar.oas.server.validator.core.valuegen.schema.binary;

import io.swagger.v3.oas.models.media.BinarySchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.BinaryVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.GeneratorDependingGenerationService;

/**
 * {@link BinaryVal} generator for {@link BinarySchema}<br>
 * doc: <a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5">JSON Schema Validation</a>
 */
public class BinaryValGenerationService extends GeneratorDependingGenerationService<BinarySchema> {

  @Override
  public boolean supports(Schema schema) {
    return BinarySchemaSupport.supports(schema);
  }

}
