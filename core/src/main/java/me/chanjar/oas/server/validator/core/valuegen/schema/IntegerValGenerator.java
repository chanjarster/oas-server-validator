package me.chanjar.oas.server.validator.core.valuegen.schema;

import io.swagger.v3.oas.models.media.IntegerSchema;
import me.chanjar.oas.server.validator.core.value.schema.IntegerVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.value.schema.StringVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.integer.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * {@link IntegerVal} generator for {@link IntegerSchema}<br>
 * doc: <a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5">JSON Schema Validation</a>
 */
public class IntegerValGenerator extends SchemaValGeneratorTemplate<IntegerSchema, IntegerVal> {

  private final List<GoodIntegerValGenerator> goodGenerators;

  private final List<BadIntegerValGenerator> badGenerators;

  public IntegerValGenerator() {

    // TODO
    goodGenerators = Collections.unmodifiableList(Arrays.asList(
        new GoodIntegerGenerator1(),
        new GoodIntegerGenerator2(),
        new GoodIntegerGenerator3()
    ));

    badGenerators = Collections.unmodifiableList(Arrays.asList(
        new BadIntegerValGenerator1(),
        new BadIntegerValGenerator2()
    ));

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
  protected SchemaVal getDifferentTypeSchemaVal() {
    return new StringVal("string");
  }

  @Override
  protected List<? extends BadSchemaValGenerator<IntegerSchema, IntegerVal>> getBadGenerators() {
    return badGenerators;
  }

  @Override
  protected List<? extends GoodSchemaValGenerator<IntegerSchema, IntegerVal>> getGoodGenerators() {
    return goodGenerators;
  }
}
