package me.chanjar.oas.server.validator.core.valuegen.schema.binary;

import io.swagger.v3.oas.models.media.BinarySchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.BinaryVal;
import me.chanjar.oas.server.validator.core.value.schema.IntegerVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.PrimitiveSchemaValGenerationService;

/**
 * {@link BinaryVal} generator for {@link BinarySchema}<br>
 * doc: <a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5">JSON Schema Validation</a>
 */
public class BinaryValGenerationService extends PrimitiveSchemaValGenerationService<BinarySchema, BinaryVal> {

  @Override
  public boolean supports(Schema schema) {
    return BinarySchemaSupport.supports(schema);
  }

  @Override
  protected Class<BinarySchema> getSchemaValClass() {
    return BinarySchema.class;
  }

  @Override
  protected SchemaVal createDifferentTypeSchemaVal() {
    return new IntegerVal(0);
  }

  @Override
  protected BinaryVal createNullSchemaVal() {
    return new BinaryVal(null);
  }

}
