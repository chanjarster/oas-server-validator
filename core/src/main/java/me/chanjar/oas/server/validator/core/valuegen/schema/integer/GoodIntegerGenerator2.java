package me.chanjar.oas.server.validator.core.valuegen.schema.integer;

import io.swagger.v3.oas.models.media.IntegerSchema;
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
  public boolean supports(IntegerSchema schema) {

    return IntegerSchemaSupport.supports(schema) && schema.getMinimum() != null;
  }

  @Override
  public IntegerVal generate(IntegerSchema schema) {

    BigDecimal min = schema.getMinimum();
    BigDecimal result = schema.getMinimum();

    boolean exclusiveMin = Boolean.TRUE.equals(schema.getExclusiveMinimum());

    BigDecimal multipleOf = schema.getMultipleOf();
    if (multipleOf != null) {

      BigDecimal multiplier = result.divide(multipleOf, 0, RoundingMode.CEILING);

      result = multiplier.multiply(multipleOf);
      while (!satisfy(min, result, exclusiveMin)) {
        // result is not an integer val, add multiplier until result becomes an integer
        multiplier = multiplier.add(BigDecimal.ONE);
        result = multiplier.multiply(multipleOf);
      }
    } else {

      while (!satisfy(min, result, exclusiveMin)) {
        // result is not an integer val, add 1 and ceiling
        result = result.add(BigDecimal.ONE).setScale(0, RoundingMode.FLOOR);
      }

    }

    return new IntegerVal(result.intValue());

  }

  private boolean satisfy(BigDecimal min, BigDecimal result, boolean exclusiveMin) {

    if (new BigDecimal(result.intValue()).compareTo(result) != 0) {
      // result is not an integer
      return false;
    }

    if (exclusiveMin) {
      return result.compareTo(min) > 0;
    }

    return result.compareTo(min) >= 0;

  }
}
