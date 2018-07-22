package me.chanjar.oas.server.validator.core.valuegen.schema;

import io.swagger.v3.oas.models.media.IntegerSchema;
import me.chanjar.oas.server.validator.core.value.schema.IntegerVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.value.schema.StringVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.integer.*;

/**
 * {@link IntegerVal} generator for {@link IntegerSchema}<br>
 * doc: <a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5">JSON Schema Validation</a>
 */
public class IntegerValGenerationService extends SchemaValGenerationServiceTemplate<IntegerSchema, IntegerVal> {

  public IntegerValGenerationService() {

    // TODO move it to factory
    addGoodGenerator(new GoodIntegerValGenerator1());
    addGoodGenerator(new GoodIntegerValGenerator2());
    addGoodGenerator(new GoodIntegerValGenerator3());

    addBadGenerator(new BadIntegerValGenerator1());
    addBadGenerator(new BadIntegerValGenerator2());

  }

  @Override
  public boolean supports(IntegerSchema schema) {
    return IntegerSchemaSupport.supports(schema);
  }

  @Override
  protected Class<IntegerSchema> getSchemaValClass() {
    return IntegerSchema.class;
  }

  @Override
  protected SchemaVal createDifferentTypeSchemaVal() {
    return new StringVal("string");
  }

  @Override
  protected IntegerVal createNullSchemaVal() {
    return new IntegerVal(null);
  }

}
