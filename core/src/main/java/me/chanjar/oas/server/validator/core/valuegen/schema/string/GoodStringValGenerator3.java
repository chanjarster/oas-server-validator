package me.chanjar.oas.server.validator.core.valuegen.schema.string;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import me.chanjar.oas.server.validator.core.value.schema.StringVal;
import org.apache.commons.lang3.StringUtils;

/**
 * Support maxLength != null
 */
public class GoodStringValGenerator3 implements GoodStringValGenerator {

  @Override
  public boolean supports(Schema schema) {
    return StringSchemaSupport.supports(schema) && schema.getMaxLength() != null;
  }

  @Override
  public StringVal generate(StringSchema schema) {
    return new StringVal(StringUtils.repeat('s', schema.getMaxLength()));
  }

}
