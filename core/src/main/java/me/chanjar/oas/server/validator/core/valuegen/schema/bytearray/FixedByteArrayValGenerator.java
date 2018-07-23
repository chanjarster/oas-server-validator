package me.chanjar.oas.server.validator.core.valuegen.schema.bytearray;

import io.swagger.v3.oas.models.media.ByteArraySchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.ByteArrayVal;
import org.apache.commons.lang3.ArrayUtils;

/**
 * A {@link ByteArrayValGenerator} which returns a fixed value
 */
public class FixedByteArrayValGenerator implements ByteArrayValGenerator {

  private final ByteArrayVal fixedVal;

  public FixedByteArrayValGenerator(Byte[] bytes) {
    this.fixedVal = new ByteArrayVal(ArrayUtils.addAll(null, bytes));
  }

  @Override
  public boolean supports(Schema schema) {
    return ByteArraySchemaSupport.supports(schema);
  }

  @Override
  public ByteArrayVal generate(ByteArraySchema schema) {
    return fixedVal;
  }
}
