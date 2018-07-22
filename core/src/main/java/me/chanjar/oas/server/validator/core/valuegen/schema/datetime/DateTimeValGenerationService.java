package me.chanjar.oas.server.validator.core.valuegen.schema.datetime;

import io.swagger.v3.oas.models.media.DateTimeSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.DateTimeVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.value.schema.StringVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerationServiceTemplate;

/**
 * {@link DateTimeVal} generator for {@link DateTimeSchema}<br>
 * see: <a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#page-14">json-schema-validation</a>
 */
public class DateTimeValGenerationService extends SchemaValGenerationServiceTemplate<DateTimeSchema, DateTimeVal> {

  public DateTimeValGenerationService() {
    // TODO move it to factory
    addGoodGenerator(new GoodDateTimeValGenerator());
    // FIXME lack of bad generators
  }

  @Override
  public boolean supports(Schema schema) {
    return DateTimeSchemaSupport.supports(schema);
  }

  @Override
  protected Class<DateTimeSchema> getSchemaValClass() {
    return DateTimeSchema.class;
  }

  @Override
  protected SchemaVal createDifferentTypeSchemaVal() {
    return new StringVal("string");
  }

  @Override
  protected DateTimeVal createNullSchemaVal() {
    return new DateTimeVal(null);
  }

}
