package me.chanjar.oas.server.validator.core.valuegen.schema.binary;

import io.swagger.v3.oas.models.media.BinarySchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.BinaryVal;

/**
 * A {@link BinaryValGenerator} which returns a fixed value
 */
public class FixedBinaryValGenerator implements BinaryValGenerator {

  private final BinaryVal fixedVal;

  public FixedBinaryValGenerator(String binary) {
    this.fixedVal = new BinaryVal(binary);
  }

  @Override
  public boolean supports(Schema schema) {
    return BinarySchemaSupport.supports(schema);
  }

  @Override
  public BinaryVal generate(BinarySchema schema) {
    return fixedVal;
  }
}
