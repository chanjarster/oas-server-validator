package me.chanjar.oas.server.validator.core.valuegen.schema;

import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;

import java.util.List;

public interface SchemaValGenerator<S extends Schema, V extends SchemaVal> {

  /**
   * Whether this generator supports this schema.
   *
   * @param schema {@link Schema}
   * @return
   */
  boolean supports(S schema);

  /**
   * Generate one good {@link SchemaVal}, which satisfies spec
   *
   * @param schema
   * @return
   */
  V generateGood(S schema);

  /**
   * Generate all good {@link SchemaVal}s, which satisfy spec, including all edge cases
   *
   * @param schema
   * @return
   */
  List<V> generateGoods(S schema);

  /**
   * Generate one bad {@link SchemaVal}, which does'nt satisfy spec
   *
   * @param schema
   * @param allowChangeType whether allowing generate a different type {@link SchemaVal}
   * @return
   */
  SchemaVal generateBad(S schema, boolean allowChangeType);

  /**
   * Generate all bad {@link SchemaVal}s, which don't satisfy spec, including all possible cases
   *
   * @param schema
   * @param allowChangeType whether allowing generate a different type {@link SchemaVal}
   * @return
   */
  List<SchemaVal> generateBads(S schema, boolean allowChangeType);

}
