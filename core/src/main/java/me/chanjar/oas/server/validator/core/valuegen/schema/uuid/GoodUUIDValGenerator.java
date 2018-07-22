package me.chanjar.oas.server.validator.core.valuegen.schema.uuid;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.UUIDSchema;
import me.chanjar.oas.server.validator.core.value.schema.UUIDVal;

import java.util.UUID;

public class GoodUUIDValGenerator implements UUIDValGenerator {

  @Override
  public boolean supports(Schema schema) {
    return UUIDSchemaSupport.supports(schema) && schema.getMinLength() == null & schema.getMaxLength() == null;
  }

  @Override
  public UUIDVal generate(UUIDSchema schema) {
    return new UUIDVal(UUID.randomUUID().toString());
  }

}
