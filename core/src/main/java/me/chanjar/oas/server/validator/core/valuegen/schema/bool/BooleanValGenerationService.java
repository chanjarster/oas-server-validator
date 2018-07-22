package me.chanjar.oas.server.validator.core.valuegen.schema.bool;

import io.swagger.v3.oas.models.media.BooleanSchema;
import me.chanjar.oas.server.validator.core.value.schema.BooleanVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.value.schema.StringVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerationServiceTemplate;

/**
 * {@link BooleanVal} generator for {@link BooleanSchema}<br>
 * Note: pattern not supported.<br>
 * doc: <a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5">JSON Schema Validation</a>
 */
public class BooleanValGenerationService extends SchemaValGenerationServiceTemplate<BooleanSchema, BooleanVal> {

  public BooleanValGenerationService() {

    // TODO move it to factory
    addGoodGenerator(new GoodBooleanValGenerator());

  }

  @Override
  public boolean supports(BooleanSchema schema) {
    return BooleanSchemaSupport.supports(schema);
  }

  @Override
  protected Class<BooleanSchema> getSchemaValClass() {
    return BooleanSchema.class;
  }

  @Override
  protected BooleanVal createNullSchemaVal() {
    return new BooleanVal(null);
  }

  @Override
  protected SchemaVal createDifferentTypeSchemaVal() {
    return new StringVal("string");
  }

}
