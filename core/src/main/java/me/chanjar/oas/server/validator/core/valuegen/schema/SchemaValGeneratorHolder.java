package me.chanjar.oas.server.validator.core.valuegen.schema;

import java.util.List;

public interface SchemaValGeneratorHolder {

  void addGenerator(SchemaValGenerator schemaValGenerator);

  void addGenerators(SchemaValGenerator schemaValGenerator, SchemaValGenerator... schemaValGenerators);

  void addGenerators(List<? extends SchemaValGenerator> schemaValGenerators);

}
