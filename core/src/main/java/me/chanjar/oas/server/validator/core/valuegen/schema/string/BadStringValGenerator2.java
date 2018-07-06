package me.chanjar.oas.server.validator.core.valuegen.schema.string;

import io.swagger.v3.oas.models.media.StringSchema;
import me.chanjar.oas.server.validator.core.value.schema.StringVal;
import org.apache.commons.lang3.StringUtils;

/**
 * Support maxLength != null
 */
public class BadStringValGenerator2 implements BadStringValGenerator {

  @Override
  public boolean supports(StringSchema schema) {
    return StringSchemaSupport.supports(schema) && schema.getMaxLength() != null;
  }

  @Override
  public StringVal generate(StringSchema schema) {
    return new StringVal(StringUtils.repeat('s', schema.getMaxLength() + 1));
  }

}
