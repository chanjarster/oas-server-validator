package me.chanjar.oas.server.validator.core.valuegen.schema.special;

import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValCons;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerator;

/**
 * A {@link SchemaValGenerator} always return fixed value.
 */
public class FixedSchemaValGenerator implements SchemaValGenerator {

  private final SchemaVal value;

  public FixedSchemaValGenerator(SchemaVal value) {
    this.value = value;
  }

  @Override
  public boolean supports(Schema schema, SchemaValCons cons) {
    return true;
  }

  @Override
  public SchemaVal generate(Schema schema, SchemaValCons cons) {
    return value;
  }
}
