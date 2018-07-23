package me.chanjar.oas.server.validator.core.valuegen.schema.bool;

import io.swagger.v3.oas.models.media.BooleanSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.BooleanVal;

/**
 * A {@link BooleanValGenerator} which returns a fixed value
 */
public class FixedBooleanValGenerator implements BooleanValGenerator {

  private final BooleanVal fixedVal;

  public FixedBooleanValGenerator(Boolean bool) {
    this.fixedVal = new BooleanVal(bool);
  }

  @Override
  public boolean supports(Schema schema) {
    return BooleanSchemaSupport.supports(schema);
  }

  @Override
  public BooleanVal generate(BooleanSchema schema) {
    return fixedVal;
  }
}
