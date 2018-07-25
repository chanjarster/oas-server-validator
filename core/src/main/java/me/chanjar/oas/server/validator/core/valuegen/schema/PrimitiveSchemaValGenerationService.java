package me.chanjar.oas.server.validator.core.valuegen.schema;

import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * SchemaValGenerationService for primitive {@link SchemaVal}s.
 * <ol>
 * <li>{@link BinaryVal}</li>
 * <li>{@link BooleanVal}</li>
 * <li>{@link ByteArrayVal}</li>
 * <li>{@link DateTimeVal}</li>
 * <li>{@link DateVal}</li>
 * <li>{@link EmailVal}</li>
 * <li>{@link IntegerVal}</li>
 * <li>{@link NumberVal}</li>
 * <li>{@link PasswordVal}</li>
 * <li>{@link StringVal}</li>
 * <li>{@link UUIDVal}</li>
 * </ol>
 *
 * @param <S>
 */
public abstract class PrimitiveSchemaValGenerationService<S extends Schema>
    implements SchemaValGenerationService<S> {

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

  public void addGenerator(SchemaValGenerator schemaValGenerator) {
    generators.add(schemaValGenerator);
  }

  public void addGenerators(List<? extends SchemaValGenerator> schemaValGenerators) {
    generators.addAll(schemaValGenerators);
  }

}
