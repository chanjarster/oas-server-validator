package me.chanjar.oas.server.validator.core.valuegen.schema.number;

import io.swagger.v3.oas.models.media.NumberSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.NumberVal;

import java.math.BigDecimal;

/**
 * Supports:
 * <ol>
 * <li><a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5.4">minimum</a> == null</li>
 * <li><a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5.2">maximum</a> == null</li>
 * <li><a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5.1">multipleOf</a></li>
 * </ol>
 */
public class GoodNumberValGenerator1 implements NumberValGenerator {
  @Override
  public boolean supports(Schema schema) {

    return NumberSchemaSupport.supports(schema)
        && schema.getMaximum() == null
        && schema.getMinimum() == null;
  }

  @Override
  public NumberVal generate(NumberSchema schema) {
    return new NumberVal(BigDecimal.ZERO);
  }
}
