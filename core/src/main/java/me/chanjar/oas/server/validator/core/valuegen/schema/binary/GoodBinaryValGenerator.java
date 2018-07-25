package me.chanjar.oas.server.validator.core.valuegen.schema.binary;

import io.swagger.v3.oas.models.media.BinarySchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.BinaryVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValCons;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Base64;

/**
 * Related json schema validation properties: none
 * see: <a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5">json-schema-validation</a>
 */
public class GoodBinaryValGenerator implements BinaryValGenerator {

  @Override
  public boolean supports(Schema schema, SchemaValCons cons) {
    return BinarySchemaSupport.supports(schema);
  }

  @Override
  public BinaryVal generate(BinarySchema schema,
      SchemaValCons cons) {
    return new BinaryVal(Base64.getEncoder().encodeToString(RandomStringUtils.random(100).getBytes()));
  }
}
