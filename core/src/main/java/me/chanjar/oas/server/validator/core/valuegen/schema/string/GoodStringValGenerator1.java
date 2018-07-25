package me.chanjar.oas.server.validator.core.valuegen.schema.string;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import me.chanjar.oas.server.validator.core.value.schema.StringVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValCons;

/**
 * Support minLength == null && maxLength == null
 */
public class GoodStringValGenerator1 implements StringValGenerator {

  @Override
  public boolean supports(Schema schema, SchemaValCons cons) {
    return StringSchemaSupport.supports(schema) && schema.getMinLength() == null & schema.getMaxLength() == null;
  }

  @Override
  public StringVal generate(StringSchema schema,
      SchemaValCons cons) {
    return new StringVal("string");
  }

}
