package me.chanjar.oas.server.validator.core.valuegen.schema.number;

import io.swagger.v3.oas.models.media.NumberSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.NumberVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.value.schema.StringVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.PrimitiveSchemaValGenerationService;

/**
 * {@link NumberVal} generator for {@link NumberSchema}<br>
 * doc: <a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5">JSON Schema Validation</a>
 */
public class NumberValGenerationService extends PrimitiveSchemaValGenerationService<NumberSchema, NumberVal> {

  @Override
  public boolean supports(Schema schema) {
    return NumberSchemaSupport.supports(schema);
  }

  @Override
  protected Class<NumberSchema> getSchemaValClass() {
    return NumberSchema.class;
  }

  @Override
  protected SchemaVal createDifferentTypeSchemaVal() {
    return new StringVal("string");
  }

  @Override
  protected NumberVal createNullSchemaVal() {
    return new NumberVal(null);
  }

}
