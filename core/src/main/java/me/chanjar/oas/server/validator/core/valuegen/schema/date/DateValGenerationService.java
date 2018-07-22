package me.chanjar.oas.server.validator.core.valuegen.schema.date;

import io.swagger.v3.oas.models.media.DateSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.DateVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.value.schema.StringVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerationServiceTemplate;

/**
 * {@link DateVal} generator for {@link DateSchema}<br>
 * see: <a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#page-14">json-schema-validation</a>
 */
public class DateValGenerationService extends SchemaValGenerationServiceTemplate<DateSchema, DateVal> {

  public DateValGenerationService() {
    // TODO move it to factory
    addGoodGenerator(new GoodDateValGenerator());
    // FIXME lack of bad generators
  }

  @Override
  public boolean supports(Schema schema) {
    return DateSchemaSupport.supports(schema);
  }

  @Override
  protected Class<DateSchema> getSchemaValClass() {
    return DateSchema.class;
  }

  @Override
  protected SchemaVal createDifferentTypeSchemaVal() {
    return new StringVal("string");
  }

  @Override
  protected DateVal createNullSchemaVal() {
    return new DateVal(null);
  }

}
