package me.chanjar.oas.server.validator.core.valuegen.schema.date;

import io.swagger.v3.oas.models.media.DateSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.DateVal;

import java.util.Date;

/**
 * A {@link DateValGenerator} which returns a fixed value
 */
public class FixedDateValGenerator implements DateValGenerator {

  private final DateVal fixedVal;

  public FixedDateValGenerator(Date date) {
    this.fixedVal = new DateVal(date);
  }

  @Override
  public boolean supports(Schema schema) {
    return DateSchemaSupport.supports(schema);
  }

  @Override
  public DateVal generate(DateSchema schema) {
    return fixedVal;
  }
}
