package me.chanjar.oas.server.validator.core.valuegen.schema.string;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import me.chanjar.oas.server.validator.core.value.schema.StringVal;

/**
 * A {@link StringValGenerator} which returns a fixed value
 */
public class FixedStringValGenerator implements StringValGenerator {

  private final StringVal fixedVal;

  public FixedStringValGenerator(String string) {
    this.fixedVal = new StringVal(string);
  }

  @Override
  public boolean supports(Schema schema) {
    return StringSchema.class.equals(schema.getClass());
  }

  @Override
  public StringVal generate(StringSchema schema) {
    return fixedVal;
  }
}
