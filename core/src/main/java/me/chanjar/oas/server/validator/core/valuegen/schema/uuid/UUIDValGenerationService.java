package me.chanjar.oas.server.validator.core.valuegen.schema.uuid;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.UUIDSchema;
import me.chanjar.oas.server.validator.core.value.schema.UUIDVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.PrimitiveSchemaValGenerationService;

/**
 * {@link UUIDVal} generator for {@link UUIDSchema}<br>
 * Note: pattern not supported.<br>
 * doc: <a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5">JSON Schema Validation</a>
 */
public class UUIDValGenerationService extends PrimitiveSchemaValGenerationService<UUIDSchema> {

  @Override
  public boolean supports(Schema schema) {
    return UUIDSchemaSupport.supports(schema);
  }

}
