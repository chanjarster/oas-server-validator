package me.chanjar.oas.server.validator.core.valuegen.schema.datetime;

import io.swagger.v3.oas.models.media.DateTimeSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.DateTimeVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.PrimitiveSchemaValGenerationService;

/**
 * {@link DateTimeVal} generator for {@link DateTimeSchema}<br>
 * see: <a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#page-14">json-schema-validation</a>
 */
public class DateTimeValGenerationService extends PrimitiveSchemaValGenerationService<DateTimeSchema> {

  @Override
  public boolean supports(Schema schema) {
    return DateTimeSchemaSupport.supports(schema);
  }

}
