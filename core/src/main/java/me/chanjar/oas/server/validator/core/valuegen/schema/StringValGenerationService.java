package me.chanjar.oas.server.validator.core.valuegen.schema;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import me.chanjar.oas.server.validator.core.value.schema.IntegerVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.value.schema.StringVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.string.*;

/**
 * {@link StringVal} generator for {@link StringSchema}<br>
 * Note: pattern not supported.<br>
 * doc: <a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5">JSON Schema Validation</a>
 */
public class StringValGenerationService extends SchemaValGenerationServiceTemplate<StringSchema, StringVal> {

  public StringValGenerationService() {

    // TODO move it to factory
    addGoodGenerator(new GoodStringValGenerator1());
    addGoodGenerator(new GoodStringValGenerator2());
    addGoodGenerator(new GoodStringValGenerator3());

    addBadGenerator(new BadStringValGenerator1());
    addBadGenerator(new BadStringValGenerator2());

  }

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
