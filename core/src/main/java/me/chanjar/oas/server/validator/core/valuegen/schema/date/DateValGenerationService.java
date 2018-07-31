package me.chanjar.oas.server.validator.core.valuegen.schema.date;

import io.swagger.v3.oas.models.media.DateSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.DateVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.GeneratorDependingGenerationService;

/**
 * {@link DateVal} generator for {@link DateSchema}<br>
 * see: <a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#page-14">json-schema-validation</a>
 */
public class DateValGenerationService extends GeneratorDependingGenerationService<DateSchema> {

  @Override
  public boolean supports(Schema schema) {
    return DateSchemaSupport.supports(schema);
  }

}
