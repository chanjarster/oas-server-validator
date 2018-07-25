package me.chanjar.oas.server.validator.core.valuegen.schema.uuid;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.UUIDSchema;
import me.chanjar.oas.server.validator.core.value.schema.UUIDVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValCons;

import java.util.UUID;

public class GoodUUIDValGenerator implements UUIDValGenerator {

  @Override
  public boolean supports(Schema schema, SchemaValCons cons) {
    return UUIDSchemaSupport.supports(schema) && schema.getMinLength() == null & schema.getMaxLength() == null;
  }

  @Override
  public UUIDVal generate(UUIDSchema schema, SchemaValCons cons) {
    return new UUIDVal(UUID.randomUUID().toString());
  }

}
