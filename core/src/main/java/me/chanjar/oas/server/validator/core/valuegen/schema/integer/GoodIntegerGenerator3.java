package me.chanjar.oas.server.validator.core.valuegen.schema.integer;

import io.swagger.v3.oas.models.media.IntegerSchema;
import me.chanjar.oas.server.validator.core.value.schema.IntegerVal;

/**
 * Supports maximum != null
 * https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5.2
 * https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5.3
 */
public class GoodIntegerGenerator3 implements GoodIntegerValGenerator {
  @Override
  public boolean supports(IntegerSchema schema) {

    return IntegerSchemaSupport.supports(schema) && schema.getMaximum() != null;
  }

  @Override
  public IntegerVal generate(IntegerSchema schema) {

    if (Boolean.TRUE.equals(schema.getExclusiveMaximum())) {
      return new IntegerVal(schema.getMaximum().intValue() - 1);
    }

    return new IntegerVal(schema.getMaximum().intValue());
    
  }
}
