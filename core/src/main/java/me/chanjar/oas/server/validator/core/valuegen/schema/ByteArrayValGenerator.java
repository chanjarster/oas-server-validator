package me.chanjar.oas.server.validator.core.valuegen.schema;

import io.swagger.v3.oas.models.media.ByteArraySchema;
import me.chanjar.oas.server.validator.core.value.schema.ByteArrayVal;
import me.chanjar.oas.server.validator.core.value.schema.ByteArrayVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.value.schema.StringVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.bytearray.ByteArraySchemaSupport;
import me.chanjar.oas.server.validator.core.valuegen.schema.bytearray.GoodByteArrayValGenerator;

import java.util.Collections;
import java.util.List;

/**
 * {@link ByteArrayVal} generator for {@link ByteArraySchema}<br>
 * doc: <a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5">JSON Schema Validation</a>
 */
public class ByteArrayValGenerator extends SchemaValGeneratorTemplate<ByteArraySchema, ByteArrayVal> {

  private final GoodByteArrayValGenerator goodGenerator = new GoodByteArrayValGenerator();

  @Override
  public boolean supports(ByteArraySchema schema) {
    return ByteArraySchemaSupport.supports(schema);
  }

  @Override
  protected Class<ByteArraySchema> getSchemaValClass() {
    return ByteArraySchema.class;
  }

  @Override
  protected SchemaVal createDifferentTypeSchemaVal() {
    return new StringVal("string");
  }

  @Override
  protected ByteArrayVal createNullSchemaVal() {
    return new ByteArrayVal(null);
  }

  @Override
  protected List<? extends BadSchemaValGenerator<ByteArraySchema, ByteArrayVal>> getBadGenerators() {
    return Collections.emptyList();
  }

  @Override
  protected List<? extends GoodSchemaValGenerator<ByteArraySchema, ByteArrayVal>> getGoodGenerators() {
    return Collections.singletonList(goodGenerator);
  }

}
