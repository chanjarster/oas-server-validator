package me.chanjar.oas.server.validator.core.valuegen.schema.object;

import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.valuegen.schema.GeneratorDependingGenerationService;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerator;

import java.util.ArrayList;
import java.util.List;

public class SimpleObjectValGenerationService extends GeneratorDependingGenerationService<ObjectSchema>
    implements ObjectValGenerationService {

  private final List<SchemaValGenerator> generators = new ArrayList<>();

  @Override
  public boolean supports(Schema schema) {
    return schema.getClass().equals(ObjectSchema.class);
  }

}
