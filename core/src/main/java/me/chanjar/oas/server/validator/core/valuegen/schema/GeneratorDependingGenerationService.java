package me.chanjar.oas.server.validator.core.valuegen.schema;

import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

/**
 * a {@link SchemaValGenerationService} which totally depends on {@link SchemaValGenerator}s
 *
 * @param <S>
 */
public abstract class GeneratorDependingGenerationService<S extends Schema>
    implements SchemaValGenerationService<S>, SchemaValGeneratorHolder {

  private List<SchemaValGenerator> generators = new ArrayList<>();

  @Override
  public SchemaVal generateOne(S schema, SchemaValCons cons) {

    return generators.stream()
        .filter(g -> g.supports(schema, cons))
        .findFirst()
        .map(g -> g.generate(schema, cons))
        .orElse(null);

  }

  @Override
  public List<SchemaVal> generateAll(S schema, SchemaValCons cons) {

    return generators.stream()
        .filter(g -> g.supports(schema, cons))
        .map(g -> g.generate(schema, cons))
        .collect(toList());

  }

  @Override
  public void addGenerator(SchemaValGenerator schemaValGenerator) {
    generators.add(schemaValGenerator);
  }

  @Override
  public void addGenerators(SchemaValGenerator schemaValGenerator, SchemaValGenerator... schemaValGenerators) {

    generators.add(schemaValGenerator);
    if (ArrayUtils.isNotEmpty(schemaValGenerators)) {
      addGenerators(asList(schemaValGenerators));
    }

  }

  @Override
  public void addGenerators(List<? extends SchemaValGenerator> schemaValGenerators) {
    generators.addAll(schemaValGenerators);
  }

}
