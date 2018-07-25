package me.chanjar.oas.server.validator.core.valuegen.schema.bool;

import io.swagger.v3.oas.models.media.BooleanSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.BooleanVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValCons;

public class GoodBooleanValGenerator implements BooleanValGenerator {

  @Override
  public boolean supports(Schema schema, SchemaValCons cons) {
    return BooleanSchemaSupport.supports(schema);
  }

  @Override
  public BooleanVal generate(BooleanSchema schema,
      SchemaValCons cons) {
    return new BooleanVal(false);
  }

}
