package me.chanjar.oas.server.validator.core.valuegen.schema.integer;

import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.IntegerVal;

/**
 * Supports:
 * <ol>
 * <li><a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5.4">minimum</a> == null</li>
 * <li><a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5.2">maximum</a> == null</li>
 * <li><a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5.1">multipleOf</a></li>
 * </ol>
 */
public class GoodIntegerGenerator1 implements GoodIntegerValGenerator {
  @Override
  public boolean supports(Schema schema) {

    return IntegerSchemaSupport.supports(schema)
        && schema.getMaximum() == null
        && schema.getMinimum() == null;
  }

  @Override
  public IntegerVal generate(IntegerSchema schema) {
    return new IntegerVal(0);
  }
}
