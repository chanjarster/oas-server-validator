package me.chanjar.oas.server.validator.core.valuegen.schema.integer;

import io.swagger.v3.oas.models.media.IntegerSchema;
import me.chanjar.oas.server.validator.core.value.schema.IntegerVal;

/**
 * Supports:
 * <ol>
 * <li><a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5.2">maximum</a> != null</li>
 * <li><a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5.3">exclusiveMaximum</a></li>
 * <li><a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5.1">multipleOf</a></li>
 * </ol>
 */
public class GoodIntegerGenerator3 implements GoodIntegerValGenerator {
  @Override
  public boolean supports(IntegerSchema schema) {

    return IntegerSchemaSupport.supports(schema) && schema.getMaximum() != null;
  }

  @Override
  public IntegerVal generate(IntegerSchema schema) {

    if (Boolean.TRUE.equals(schema.getExclusiveMaximum())) {
      if (schema.getMultipleOf() != null) {

      } else {
        return new IntegerVal(schema.getMaximum().intValue() - 1);
      }

    }

    return new IntegerVal(schema.getMaximum().intValue());

  }
}
