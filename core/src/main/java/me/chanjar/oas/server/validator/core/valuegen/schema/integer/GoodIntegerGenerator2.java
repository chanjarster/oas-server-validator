package me.chanjar.oas.server.validator.core.valuegen.schema.integer;

import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.utils.BigDecimalUtils;
import me.chanjar.oas.server.validator.core.value.schema.IntegerVal;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Supports:
 * <ol>
 * <li><a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5.4">minimum</a> != null</li>
 * <li><a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5.5">exclusiveMinimum</a></li>
 * <li><a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5.1">multipleOf</a></li>
 * </ol>
 */
public class GoodIntegerGenerator2 implements GoodIntegerValGenerator {
  @Override
  public boolean supports(Schema schema) {

    return IntegerSchemaSupport.supports(schema) && schema.getMinimum() != null;
  }

  @Override
  public IntegerVal generate(IntegerSchema schema) {

    BigDecimal min = schema.getMinimum();
    BigDecimal result;

    boolean exclusiveMin = Boolean.TRUE.equals(schema.getExclusiveMinimum());

    BigDecimal multipleOf = schema.getMultipleOf();
    if (multipleOf != null) {

      int startMultiplier = min.divide(multipleOf, 0, RoundingMode.CEILING).intValue();

      do {
        result = BigDecimalUtils.findIntegerMultipleOf(multipleOf, startMultiplier, true);
        startMultiplier++;
      } while (!satisfy(min, result, exclusiveMin));

    } else {

      if (exclusiveMin) {
        result = BigDecimalUtils.findIntegerGt(min);
      } else {
        result = BigDecimalUtils.findIntegerGte(min);
      }

    }

    return new IntegerVal(result.intValue());

  }

  private boolean satisfy(BigDecimal min, BigDecimal result, boolean exclusiveMin) {

    if (exclusiveMin) {
      return result.compareTo(min) > 0;
    }

    return result.compareTo(min) >= 0;

  }
}
