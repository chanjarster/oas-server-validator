package me.chanjar.oas.server.validator.core.valuegen.schema.datetime;

import io.swagger.v3.oas.models.media.DateTimeSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.DateTimeVal;

import java.util.Date;

/**
 * Related json schema validation properties: none
 * see: <a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#page-14">json-schema-validation</a>
 */
public class GoodDateTimeValGenerator implements DateTimeValGenerator {

  @Override
  public boolean supports(Schema schema) {
    return DateTimeSchemaSupport.supports(schema);
  }

  @Override
  public DateTimeVal generate(DateTimeSchema schema) {
    return new DateTimeVal(new Date());
  }
}
