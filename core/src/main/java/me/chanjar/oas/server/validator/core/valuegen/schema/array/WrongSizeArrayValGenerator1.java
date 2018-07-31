package me.chanjar.oas.server.validator.core.valuegen.schema.array;

import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValCons;

/**
 * Supports:
 * <ol>
 * <li><a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5.11">minimum</a> != null and != 0</li>
 * </ol>
 */
public class WrongSizeArrayValGenerator1 extends ArrayValGeneratorTemplate {

  @Override
  public boolean supports(Schema schema, SchemaValCons cons) {
    return ArraySchemaSupport.supports(schema) && schema.getMinItems() != null && !schema.getMinItems().equals(0);
  }

  @Override
  protected int calcItemSize(ArraySchema schema) {
    return schema.getMinItems() - 1;
  }

}
