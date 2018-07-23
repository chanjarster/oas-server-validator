package me.chanjar.oas.server.validator.core.valuegen.schema;

import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.*;

import java.util.ArrayList;
import java.util.List;

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
 * @param <V>
 */
public abstract class PrimitiveSchemaValGenerationService<S extends Schema, V extends SchemaVal>
    implements SchemaValGenerationService<S, V> {

  private List<SchemaValGenerator<S, V>> goodGenerators = new ArrayList<>();

  private List<SchemaValGenerator<S, V>> badGenerators = new ArrayList<>();

  public void addGoodGenerator(SchemaValGenerator<S, V> schemaValGenerator) {
    goodGenerators.add(schemaValGenerator);
  }

  public void addBadGenerator(SchemaValGenerator<S, V> schemaValGenerator) {
    badGenerators.add(schemaValGenerator);
  }

  public void addGoodGenerators(List<? extends SchemaValGenerator<S, V>> schemaValGenerators) {
    goodGenerators.addAll(schemaValGenerators);
  }

  public void addBadGenerators(List<? extends SchemaValGenerator<S, V>> schemaValGenerators) {
    badGenerators.addAll(schemaValGenerators);
  }

  @Override
  public V generateGood(S schema) {

    for (SchemaValGenerator<S, V> goodSchemaValGenerator : goodGenerators) {
      if (goodSchemaValGenerator.supports(schema)) {
        return goodSchemaValGenerator.generate(schema);
      }
    }

    return null;
  }

  @Override
  public List<SchemaVal> generateGoods(S schema, SchemaValCons cons, boolean typeSensitive) {

    List<SchemaVal> result = new ArrayList<>();

    for (SchemaValGenerator<S, V> goodSchemaValGenerator : goodGenerators) {
      if (goodSchemaValGenerator.supports(schema)) {
        result.add(goodSchemaValGenerator.generate(schema));
      }
    }

    if (!cons.isRequired()) {
      result.add(IgnoredVal.INSTANCE);
    }

    if (cons.isNullable()) {
      result.add(createNullSchemaVal());
    }

    if (!typeSensitive) {
      result.add(createDifferentTypeSchemaVal());
    }

    return result;

  }

  @Override
  public SchemaVal generateBad(S schema, SchemaValCons cons, boolean typeSensitive) {

    for (SchemaValGenerator<S, V> badSchemaValGenerator : badGenerators) {
      if (badSchemaValGenerator.supports(schema)) {
        return badSchemaValGenerator.generate(schema);
      }
    }

    if (cons.isRequired()) {
      return IgnoredVal.INSTANCE;
    }

    if (!cons.isNullable()) {
      return createNullSchemaVal();
    }

    if (typeSensitive) {
      return createDifferentTypeSchemaVal();
    }

    return null;
  }

  @Override
  public List<SchemaVal> generateBads(S schema, SchemaValCons cons,
      boolean typeSensitive) {

    List<SchemaVal> result = new ArrayList<>();

    for (SchemaValGenerator<S, V> badSchemaValGenerator : badGenerators) {
      if (badSchemaValGenerator.supports(schema)) {
        result.add(badSchemaValGenerator.generate(schema));
      }
    }

    if (typeSensitive) {
      result.add(createDifferentTypeSchemaVal());
    }

    if (cons.isRequired()) {
      result.add(IgnoredVal.INSTANCE);
    }

    if (!cons.isNullable()) {
      result.add(createNullSchemaVal());
    }

    return result;

  }

  protected abstract SchemaVal createDifferentTypeSchemaVal();

  protected abstract V createNullSchemaVal();

  protected abstract Class<S> getSchemaValClass();

}
