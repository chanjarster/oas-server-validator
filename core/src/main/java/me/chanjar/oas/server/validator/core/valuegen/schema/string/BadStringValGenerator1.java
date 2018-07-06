package me.chanjar.oas.server.validator.core.valuegen.schema.string;

import io.swagger.v3.oas.models.media.StringSchema;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.value.schema.StringVal;
import org.apache.commons.lang3.StringUtils;

/**
 * Support minLength != null
 */
public class BadStringValGenerator1 implements BadStringValGenerator {

  @Override
  public boolean supports(StringSchema schema) {
    return StringSchemaSupport.supports(schema) && schema.getMinLength() != null;
  }

  @Override
  public StringVal generate(StringSchema schema) {
    return new StringVal(StringUtils.repeat('s', schema.getMinLength() - 1));
  }

}
