package me.chanjar.oas.server.validator.core.valuegen.schema.number;

import io.swagger.v3.oas.models.media.NumberSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.NumberVal;

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
public class GoodNumberValGenerator2 implements NumberValGenerator {
  @Override
  public boolean supports(Schema schema) {

    return NumberSchemaSupport.supports(schema) && schema.getMinimum() != null;
  }

  @Override
  public NumberVal generate(NumberSchema schema) {

    BigDecimal min = schema.getMinimum();
    BigDecimal result;

    boolean exclusiveMin = Boolean.TRUE.equals(schema.getExclusiveMinimum());

    BigDecimal multipleOf = schema.getMultipleOf();
    if (multipleOf != null) {

      int startMultiplier = min.divide(multipleOf, 0, RoundingMode.CEILING).intValue();

      do {
        result = multipleOf.multiply(new BigDecimal(startMultiplier));
        startMultiplier++;
      } while (!satisfy(min, result, exclusiveMin));

    } else {

      if (exclusiveMin) {
        result = min.add(BigDecimal.valueOf(0.1D));
      } else {
        result = min;
      }

    }

    return new NumberVal(result);

  }

  private boolean satisfy(BigDecimal min, BigDecimal result, boolean exclusiveMin) {

    if (exclusiveMin) {
      return result.compareTo(min) > 0;
    }

    return result.compareTo(min) >= 0;

  }
}
