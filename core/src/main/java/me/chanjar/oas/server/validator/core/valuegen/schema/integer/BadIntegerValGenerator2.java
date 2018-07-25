package me.chanjar.oas.server.validator.core.valuegen.schema.integer;

import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.utils.BigDecimalUtils;
import me.chanjar.oas.server.validator.core.value.schema.IntegerVal;
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
public class BadIntegerValGenerator2 implements IntegerValGenerator {

  @Override
  public boolean supports(Schema schema, SchemaValCons cons) {
    return IntegerSchemaSupport.supports(schema) && schema.getMaximum() != null;
  }

  @Override
  public IntegerVal generate(IntegerSchema schema,
      SchemaValCons cons) {

    BigDecimal max = schema.getMaximum();
    BigDecimal result;

    boolean exclusiveMax = Boolean.TRUE.equals(schema.getExclusiveMaximum());

    BigDecimal multipleOf = schema.getMultipleOf();
    if (multipleOf != null) {

      int startMultiplier = max.divide(multipleOf, 0, RoundingMode.FLOOR).intValue();

      do {
        result = BigDecimalUtils.findIntegerMultipleOf(multipleOf, startMultiplier, true);
        startMultiplier++;
      } while (!satisfy(max, result, exclusiveMax));

    } else {

      if (exclusiveMax) {
        result = BigDecimalUtils.findIntegerGte(max);
      } else {
        result = BigDecimalUtils.findIntegerGt(max);
      }

    }

    return new IntegerVal(result.intValue());


  }

  private boolean satisfy(BigDecimal max, BigDecimal result, boolean exclusiveMax) {

    if (exclusiveMax) {
      return result.compareTo(max) >= 0;
    }

    return result.compareTo(max) > 0;

  }

}
