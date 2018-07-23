package me.chanjar.oas.server.validator.core.valuegen.schema.uuid;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.UUIDSchema;
import me.chanjar.oas.server.validator.core.value.schema.UUIDVal;

/**
 * A {@link UUIDValGenerator} which returns a fixed value
 */
public class FixedUUIDValGenerator implements UUIDValGenerator {

  private final UUIDVal fixedVal;

  public FixedUUIDValGenerator(String uuid) {
    this.fixedVal = new UUIDVal(uuid);
  }

  @Override
  public boolean supports(Schema schema) {
    return UUIDSchemaSupport.supports(schema);
  }

  @Override
  public UUIDVal generate(UUIDSchema schema) {
    return fixedVal;
  }
}
