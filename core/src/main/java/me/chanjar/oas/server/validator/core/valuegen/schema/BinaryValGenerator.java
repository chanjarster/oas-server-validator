package me.chanjar.oas.server.validator.core.valuegen.schema;

import io.swagger.v3.oas.models.media.BinarySchema;
import me.chanjar.oas.server.validator.core.value.schema.BinaryVal;
import me.chanjar.oas.server.validator.core.value.schema.IntegerVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.binary.BinarySchemaSupport;
import me.chanjar.oas.server.validator.core.valuegen.schema.binary.GoodBinaryValGenerator;

import java.util.Collections;
import java.util.List;

/**
 * {@link BinaryVal} generator for {@link BinarySchema}<br>
 * doc: <a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5">JSON Schema Validation</a>
 */
public class BinaryValGenerator extends SchemaValGeneratorTemplate<BinarySchema, BinaryVal> {

  private final GoodBinaryValGenerator goodGenerator = new GoodBinaryValGenerator();

  @Override
  public boolean supports(BinarySchema schema) {
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

  @Override
  protected List<? extends BadSchemaValGenerator<BinarySchema, BinaryVal>> getBadGenerators() {
    return Collections.emptyList();
  }

  @Override
  protected List<? extends GoodSchemaValGenerator<BinarySchema, BinaryVal>> getGoodGenerators() {
    return Collections.singletonList(goodGenerator);
  }

}
