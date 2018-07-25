package me.chanjar.oas.server.validator.core.valuegen.schema.number;

import io.swagger.v3.oas.models.media.NumberSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.NumberVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValCons;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Supports:
 * <ol>
 * <li><a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5.2">maximum</a> != null</li>
 * <li><a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5.3">exclusiveMaximum</a></li>
 * <li><a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5.1">multipleOf</a></li>
 * </ol>
 */
public class BadNumberValGenerator2 implements NumberValGenerator {

  @Override
  public boolean supports(Schema schema, SchemaValCons cons) {
    return NumberSchemaSupport.supports(schema) && schema.getMaximum() != null;
  }

  @Override
  public NumberVal generate(NumberSchema schema,
      SchemaValCons cons) {

    BigDecimal max = schema.getMaximum();
    BigDecimal result;

    boolean exclusiveMax = Boolean.TRUE.equals(schema.getExclusiveMaximum());

    BigDecimal multipleOf = schema.getMultipleOf();
    if (multipleOf != null) {

      int startMultiplier = max.divide(multipleOf, 0, RoundingMode.FLOOR).intValue();

      do {
        result = multipleOf.multiply(new BigDecimal(startMultiplier));
        startMultiplier++;
      } while (!satisfy(max, result, exclusiveMax));

    } else {

      if (exclusiveMax) {
        result = max;
      } else {
        result = max.add(BigDecimal.valueOf(0.1D));
      }

    }

    return new NumberVal(result);

  }

  private boolean satisfy(BigDecimal max, BigDecimal result, boolean exclusiveMax) {

    if (exclusiveMax) {
      return result.compareTo(max) >= 0;
    }

    return result.compareTo(max) > 0;

  }

}
