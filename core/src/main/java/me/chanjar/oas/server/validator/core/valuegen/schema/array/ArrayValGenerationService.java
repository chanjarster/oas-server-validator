package me.chanjar.oas.server.validator.core.valuegen.schema.array;

import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.ArrayVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValCons;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerationService;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerator;

import java.util.ArrayList;
import java.util.List;

public class ArrayValGenerationService implements SchemaValGenerationService<ArraySchema> {

  private List<SchemaValGenerator> generators = new ArrayList<>();

  @Override
  public boolean supports(Schema schema) {
    return false;
  }

  @Override
  public ArrayVal generateOne(ArraySchema schema, SchemaValCons cons) {
    return null;
  }

  @Override
  public List<SchemaVal> generateAll(ArraySchema schema, SchemaValCons cons) {
    return null;
  }

  public void addGenerator(SchemaValGenerator schemaValGenerator) {
    generators.add(schemaValGenerator);
  }

  public void addGenerators(List<? extends SchemaValGenerator> schemaValGenerators) {
    generators.addAll(schemaValGenerators);
  }

}
