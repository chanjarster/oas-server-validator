package me.chanjar.oas.server.validator.core.valuegen.schema.string;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import me.chanjar.oas.server.validator.core.value.schema.StringVal;

/**
 * Support minLength == null && maxLength == null
 */
public class GoodStringValGenerator1 implements GoodStringValGenerator {

  @Override
  public boolean supports(Schema schema) {
    return StringSchemaSupport.supports(schema) && schema.getMinLength() == null & schema.getMaxLength() == null;
  }

  @Override
  public StringVal generate(StringSchema schema) {
    return new StringVal("string");
  }

}
