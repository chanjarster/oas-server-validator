package me.chanjar.oas.server.validator.core.valuegen.schema.array;

import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.valuegen.schema.GeneratorDependingGenerationService;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerator;
import me.chanjar.oas.server.validator.core.valuegen.schema.object.ObjectValGenerationService;

public class ArrayValGenerationService extends GeneratorDependingGenerationService<ArraySchema> {

  @Override
  public boolean supports(Schema schema) {
    return ArraySchemaSupport.supports(schema);
  }

  public void registerObjectGenerationService(ObjectValGenerationService objectValGenerationService) {
    for (SchemaValGenerator generator : generators) {

      if (ArrayValGeneratorTemplate.class.isAssignableFrom(generator.getClass())) {
        ((ArrayValGeneratorTemplate) generator).addItemGenerationServices(objectValGenerationService);
      }
    }

  }
}
