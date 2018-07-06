package me.chanjar.oas.server.validator.core.valuegen.schema.integer;

import io.swagger.v3.oas.models.media.IntegerSchema;
import me.chanjar.oas.server.validator.core.value.schema.IntegerVal;

/**
 * Supports minimum != null
 * https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5.4
 * https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5.5
 */
public class GoodIntegerGenerator2 implements GoodIntegerValGenerator {
  @Override
  public boolean supports(IntegerSchema schema) {

    return IntegerSchemaSupport.supports(schema) && schema.getMinimum() != null;
  }

  @Override
  public IntegerVal generate(IntegerSchema schema) {

    if (Boolean.TRUE.equals(schema.getExclusiveMinimum())) {
      return new IntegerVal(schema.getMinimum().intValue() + 1);
    }

    return new IntegerVal(schema.getMinimum().intValue());

  }
}
