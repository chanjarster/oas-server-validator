package me.chanjar.oas.server.validator.core.valuegen.schema.integer;

import io.swagger.v3.oas.models.media.IntegerSchema;
import me.chanjar.oas.server.validator.core.value.schema.IntegerVal;

/**
 * Supports maximum == null && minimum == null
 */
public class GoodIntegerGenerator1 implements GoodIntegerValGenerator {
  @Override
  public boolean supports(IntegerSchema schema) {

    return IntegerSchemaSupport.supports(schema) && schema.getMaximum() == null && schema.getMinimum() == null;
  }

  @Override
  public IntegerVal generate(IntegerSchema schema) {
    return new IntegerVal(0);
  }
}
