package me.chanjar.oas.server.validator.core.valuegen.schema;

import io.swagger.v3.oas.models.media.ByteArraySchema;
import me.chanjar.oas.server.validator.core.value.schema.ByteArrayVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.value.schema.StringVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.bytearray.ByteArraySchemaSupport;
import me.chanjar.oas.server.validator.core.valuegen.schema.bytearray.GoodByteArrayValGenerator;

/**
 * {@link ByteArrayVal} generator for {@link ByteArraySchema}<br>
 * doc: <a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5">JSON Schema Validation</a>
 */
public class ByteArrayValGenerationService extends SchemaValGenerationServiceTemplate<ByteArraySchema, ByteArrayVal> {

  public ByteArrayValGenerationService() {
    // TODO move it to factory
    addGoodGenerator(new GoodByteArrayValGenerator());
    // FIXME lack of bad generators
  }

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

}
