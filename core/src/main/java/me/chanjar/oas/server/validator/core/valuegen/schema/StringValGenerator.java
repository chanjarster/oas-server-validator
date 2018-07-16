package me.chanjar.oas.server.validator.core.valuegen.schema;

import io.swagger.v3.oas.models.media.StringSchema;
import me.chanjar.oas.server.validator.core.value.schema.IntegerVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.value.schema.StringVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.string.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * {@link StringVal} generator for {@link StringSchema}<br>
 * Note: pattern not supported.<br>
 * doc: <a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5">JSON Schema Validation</a>
 */
public class StringValGenerator extends SchemaValGeneratorTemplate<StringSchema, StringVal> {

  private final List<GoodStringValGenerator> goodGenerators;

  private final List<BadStringValGenerator> badGenerators;

  public StringValGenerator() {

    goodGenerators = Collections.unmodifiableList(Arrays.asList(
        new GoodStringValGenerator1(),
        new GoodStringValGenerator2(),
        new GoodStringValGenerator3()
    ));

    badGenerators = Collections.unmodifiableList(Arrays.asList(
        new BadStringValGenerator1(),
        new BadStringValGenerator2()
    ));

  }

  @Override
  public boolean supports(StringSchema schema) {
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

  @Override
  protected List<? extends BadSchemaValGenerator<StringSchema, StringVal>> getBadGenerators() {
    return badGenerators;
  }

  @Override
  protected List<? extends GoodSchemaValGenerator<StringSchema, StringVal>> getGoodGenerators() {
    return goodGenerators;
  }
}
