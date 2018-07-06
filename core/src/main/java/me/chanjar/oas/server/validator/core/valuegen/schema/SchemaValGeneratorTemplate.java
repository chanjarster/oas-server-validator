package me.chanjar.oas.server.validator.core.valuegen.schema;

import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.valuegen.SchemaValGenerationException;

import java.util.ArrayList;
import java.util.List;

public abstract class SchemaValGeneratorTemplate<S extends Schema, V extends SchemaVal>
    implements SchemaValGenerator<S, V> {

  @Override
  public V generateGood(S schema) {

    for (GoodSchemaValGenerator<S, V> goodSchemaValGenerator : getGoodGenerators()) {
      if (goodSchemaValGenerator.supports(schema)) {
        return goodSchemaValGenerator.generate(schema);
      }
    }

    throw new SchemaValGenerationException(
        "Cannot generate good mock " + getSchemaValClass().getSimpleName() + " for schema: " + schema.toString());

  }

  @Override
  public List<V> generateGoods(S schema) {

    List<V> result = new ArrayList<>();

    for (GoodSchemaValGenerator<S, V> goodSchemaValGenerator : getGoodGenerators()) {
      if (goodSchemaValGenerator.supports(schema)) {
        result.add(goodSchemaValGenerator.generate(schema));
      }
    }

    if (result.isEmpty()) {
      throw new SchemaValGenerationException(
          "Cannot generate good mock " + getSchemaValClass().getSimpleName() + "s for schema: " + schema.toString());
    }
    return result;

  }

  @Override
  public SchemaVal generateBad(S schema, boolean allowChangeType) {

    for (BadSchemaValGenerator<S, V> badSchemaValGenerator : getBadGenerators()) {
      if (badSchemaValGenerator.supports(schema)) {
        return badSchemaValGenerator.generate(schema);
      }
    }

    if (allowChangeType) {
      return getDifferentTypeSchemaVal();
    }

    throw null;

  }

  @Override
  public List<SchemaVal> generateBads(S schema, boolean allowChangeType) {

    List<SchemaVal> result = new ArrayList<>();

    for (BadSchemaValGenerator<S, V> badSchemaValGenerator : getBadGenerators()) {
      if (badSchemaValGenerator.supports(schema)) {
        result.add(badSchemaValGenerator.generate(schema));
      }
    }

    if (allowChangeType) {
      result.add(getDifferentTypeSchemaVal());
    }

    return result;

  }

  protected abstract SchemaVal getDifferentTypeSchemaVal();

  protected abstract Class<S> getSchemaValClass();

  protected abstract List<? extends BadSchemaValGenerator<S, V>> getBadGenerators();

  protected abstract List<? extends GoodSchemaValGenerator<S, V>> getGoodGenerators();
}
