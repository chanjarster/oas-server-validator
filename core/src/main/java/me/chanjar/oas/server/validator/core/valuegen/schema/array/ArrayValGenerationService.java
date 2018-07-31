package me.chanjar.oas.server.validator.core.valuegen.schema.array;

import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.valuegen.schema.GeneratorDependingGenerationService;

public class ArrayValGenerationService extends GeneratorDependingGenerationService<ArraySchema> {

  @Override
  public boolean supports(Schema schema) {
    return ArraySchemaSupport.supports(schema);
  }

}
