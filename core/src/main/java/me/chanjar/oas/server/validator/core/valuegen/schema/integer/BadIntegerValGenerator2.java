package me.chanjar.oas.server.validator.core.valuegen.schema.integer;

import io.swagger.v3.oas.models.media.IntegerSchema;
import me.chanjar.oas.server.validator.core.value.schema.IntegerVal;

/**
 * Support maxLength != null, multipleOf
 */
public class BadIntegerValGenerator2 implements BadIntegerValGenerator {

  @Override
  public boolean supports(IntegerSchema schema) {
    return IntegerSchemaSupport.supports(schema) && schema.getMaxLength() != null;
  }

  @Override
  public IntegerVal generate(IntegerSchema schema) {

    if (Boolean.TRUE.equals(schema.getExclusiveMaximum())) {
      return new IntegerVal(schema.getMaximum().intValue());
    }

    return new IntegerVal(schema.getMaximum().intValue() + 1);

  }

}
