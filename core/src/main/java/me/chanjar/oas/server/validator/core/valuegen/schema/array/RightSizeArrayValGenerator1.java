package me.chanjar.oas.server.validator.core.valuegen.schema.array;

import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValCons;

/**
 * Supports:
 * <ol>
 * <li><a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5.11">minItems</a> == null</li>
 * <li><a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5.10">maxItems</a> == null</li>
 * </ol>
 * Create a array with size: 1
 */
public class RightSizeArrayValGenerator1 extends ArrayValGeneratorTemplate {

  @Override
  public boolean supports(Schema schema, SchemaValCons cons) {
    return ArraySchemaSupport.supports(schema) && schema.getMaxItems() == null && schema.getMinItems() == null;
  }

  @Override
  protected int calcItemSize(ArraySchema schema) {
    return 1;
  }
}
