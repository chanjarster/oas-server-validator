package me.chanjar.oas.server.validator.core.valuegen.schema.datetime;

import io.swagger.v3.oas.models.media.DateTimeSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.DateTimeVal;

import java.util.Date;

/**
 * A {@link DateTimeValGenerator} which returns a fixed value
 */
public class FixedDateTimeValGenerator implements DateTimeValGenerator {

  private final DateTimeVal fixedVal;

  public FixedDateTimeValGenerator(Date dateTime) {
    this.fixedVal = new DateTimeVal(dateTime);
  }

  @Override
  public boolean supports(Schema schema) {
    return DateTimeSchemaSupport.supports(schema);
  }

  @Override
  public DateTimeVal generate(DateTimeSchema schema) {
    return fixedVal;
  }
}
