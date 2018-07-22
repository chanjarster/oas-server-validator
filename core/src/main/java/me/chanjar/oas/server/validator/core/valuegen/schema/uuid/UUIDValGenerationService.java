package me.chanjar.oas.server.validator.core.valuegen.schema.uuid;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.UUIDSchema;
import me.chanjar.oas.server.validator.core.value.schema.IntegerVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.value.schema.UUIDVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerationServiceTemplate;

/**
 * {@link UUIDVal} generator for {@link UUIDSchema}<br>
 * Note: pattern not supported.<br>
 * doc: <a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5">JSON Schema Validation</a>
 */
public class UUIDValGenerationService extends SchemaValGenerationServiceTemplate<UUIDSchema, UUIDVal> {

  public UUIDValGenerationService() {

    // TODO move it to factory
    addGoodGenerator(new GoodUUIDValGenerator());

  }

  @Override
  public boolean supports(Schema schema) {
    return UUIDSchemaSupport.supports(schema);
  }

  @Override
  protected Class<UUIDSchema> getSchemaValClass() {
    return UUIDSchema.class;
  }

  @Override
  protected UUIDVal createNullSchemaVal() {
    return new UUIDVal(null);
  }

  @Override
  protected SchemaVal createDifferentTypeSchemaVal() {
    return new IntegerVal(0);
  }

}
