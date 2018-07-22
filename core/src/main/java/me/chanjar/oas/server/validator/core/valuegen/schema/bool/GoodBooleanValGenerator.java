package me.chanjar.oas.server.validator.core.valuegen.schema.bool;

import io.swagger.v3.oas.models.media.BooleanSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.BooleanVal;

public class GoodBooleanValGenerator implements BooleanValGenerator {

  @Override
  public boolean supports(Schema schema) {
    return BooleanSchemaSupport.supports(schema);
  }

  @Override
  public BooleanVal generate(BooleanSchema schema) {
    return new BooleanVal(false);
  }

}
