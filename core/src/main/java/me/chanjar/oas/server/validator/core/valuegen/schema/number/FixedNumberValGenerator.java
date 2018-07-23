package me.chanjar.oas.server.validator.core.valuegen.schema.number;

import io.swagger.v3.oas.models.media.NumberSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.NumberVal;

import java.math.BigDecimal;

/**
 * A {@link NumberValGenerator} which returns a fixed value
 */
public class FixedNumberValGenerator implements NumberValGenerator {

  private final NumberVal fixedVal;

  public FixedNumberValGenerator(BigDecimal number) {
    this.fixedVal = new NumberVal(number);
  }

  @Override
  public boolean supports(Schema schema) {
    return NumberSchemaSupport.supports(schema);
  }

  @Override
  public NumberVal generate(NumberSchema schema) {
    return fixedVal;
  }
}
