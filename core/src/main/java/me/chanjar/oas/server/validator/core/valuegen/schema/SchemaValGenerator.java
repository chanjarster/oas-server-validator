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
   * @param cons
   * @param typeSensitive if true, the type of generated good {@link SchemaVal}s and {@link Schema} must match;
   *                      if false, result must contains a {@link SchemaVal} of which type mismatch {@link Schema};
   * @return
   */
  List<SchemaVal> generateGoods(S schema, SchemaValCons cons, boolean typeSensitive);

  /**
   * Generate one bad {@link SchemaVal}, which does'nt satisfy spec
   *
   * @param schema
   * @param cons
   * @param typeSensitive if true, result must contains a {@link SchemaVal} of which type mismatch {@link Schema};
   *                      if false, the type of generated bad {@link SchemaVal}s and {@link Schema} must match;
   * @return
   */
  SchemaVal generateBad(S schema, SchemaValCons cons, boolean typeSensitive);

  /**
   * Generate all bad {@link SchemaVal}s, which don't satisfy spec, including all possible cases
   *
   * @param schema
   * @param cons
   * @param typeSensitive if true, result must contains a {@link SchemaVal} of which type mismatch {@link Schema};
   *                      if false, the type of generated bad {@link SchemaVal}s and {@link Schema} must match;
   * @return
   */
  List<SchemaVal> generateBads(S schema, SchemaValCons cons, boolean typeSensitive);

}
