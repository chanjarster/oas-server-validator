package me.chanjar.oas.server.validator.core.valuegen.schema.bytearray;

import io.swagger.v3.oas.models.media.ByteArraySchema;
import me.chanjar.oas.server.validator.core.value.schema.ByteArrayVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.GoodSchemaValGenerator;

import java.util.Base64;

/**
 * Related json schema validation properties: none
 * see: <a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5">json-schema-validation</a>
 */
public class GoodByteArrayValGenerator implements GoodSchemaValGenerator<ByteArraySchema, ByteArrayVal> {

  @Override
  public boolean supports(ByteArraySchema schema) {
    return ByteArraySchemaSupport.supports(schema);
  }

  @Override
  public ByteArrayVal generate(ByteArraySchema schema) {
    byte[] bytes = Base64.getEncoder().encode("ByteArrayVal".getBytes());
    Byte[] bbytes = new Byte[bytes.length];
    for (int i = 0; i < bytes.length; i++) {
      bbytes[i] = Byte.valueOf(bytes[i]);
    }
    return new ByteArrayVal(bbytes);
  }
}
