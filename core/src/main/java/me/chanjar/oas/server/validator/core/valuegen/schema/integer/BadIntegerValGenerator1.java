package me.chanjar.oas.server.validator.core.valuegen.schema.integer;

import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.IntegerVal;

/**
 * Support minimum != null, multipleOf
 */
public class BadIntegerValGenerator1 implements BadIntegerValGenerator {

  @Override
  public boolean supports(Schema schema) {
    return IntegerSchemaSupport.supports(schema) && schema.getMinimum() != null;
  }

  @Override
  public IntegerVal generate(IntegerSchema schema) {

    if (Boolean.TRUE.equals(schema.getExclusiveMinimum())) {
      return new IntegerVal(schema.getMinimum().intValue());
    }

    return new IntegerVal(schema.getMinimum().intValue() - 1);
  }

}
