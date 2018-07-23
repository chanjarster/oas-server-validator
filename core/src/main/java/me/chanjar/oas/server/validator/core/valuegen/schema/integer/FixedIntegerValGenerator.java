package me.chanjar.oas.server.validator.core.valuegen.schema.integer;

import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.IntegerVal;

import java.math.BigDecimal;

/**
 * A {@link IntegerValGenerator} which returns a fixed value
 */
public class FixedIntegerValGenerator implements IntegerValGenerator {

  private final IntegerVal fixedVal;

  public FixedIntegerValGenerator(Integer number) {
    this.fixedVal = new IntegerVal(number);
  }

  @Override
  public boolean supports(Schema schema) {
    return IntegerSchemaSupport.supports(schema);
  }

  @Override
  public IntegerVal generate(IntegerSchema schema) {
    return fixedVal;
  }
}
