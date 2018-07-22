package me.chanjar.oas.server.validator.core.valuegen.schema.date;

import io.swagger.v3.oas.models.media.DateSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.DateVal;

import java.util.Date;

/**
 * Related json schema validation properties: none
 * see: <a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#page-14">json-schema-validation</a>
 */
public class GoodDateValGenerator implements DateValGenerator {

  @Override
  public boolean supports(Schema schema) {
    return DateSchemaSupport.supports(schema);
  }

  @Override
  public DateVal generate(DateSchema schema) {
    return new DateVal(new Date());
  }
}
