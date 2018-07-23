package me.chanjar.oas.server.validator.core.valuegen.schema.string;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import me.chanjar.oas.server.validator.core.value.schema.IntegerVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.value.schema.StringVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.PrimitiveSchemaValGenerationService;

/**
 * {@link StringVal} generator for {@link StringSchema}<br>
 * Note: pattern not supported.<br>
 * doc: <a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5">JSON Schema Validation</a>
 */
public class StringValGenerationService extends PrimitiveSchemaValGenerationService<StringSchema, StringVal> {

  @Override
  public boolean supports(Schema schema) {
    return StringSchemaSupport.supports(schema);
  }

  @Override
  protected Class<StringSchema> getSchemaValClass() {
    return StringSchema.class;
  }

  @Override
  protected StringVal createNullSchemaVal() {
    return new StringVal(null);
  }

  @Override
  protected SchemaVal createDifferentTypeSchemaVal() {
    return new IntegerVal(0);
  }

}
