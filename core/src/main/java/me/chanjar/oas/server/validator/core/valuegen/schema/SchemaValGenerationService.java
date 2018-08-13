package me.chanjar.oas.server.validator.core.valuegen.schema;

import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;

import java.util.List;

public interface SchemaValGenerationService<S extends Schema> {

  /**
   * Whether this generator supports this schema.
   *
   * @param schema {@link Schema}
   * @return
   */
  boolean supports(Schema schema);

  /**
   * Generate one {@link SchemaVal}
   *
   * @param schema
   * @param cons
   * @return
   */
  SchemaVal generateOne(S schema, SchemaValCons cons);

  /**
   * Generate all {@link SchemaVal}s
   *
   * @param schema
   * @param cons
   * @return
   */
  List<SchemaVal> generateAll(S schema, SchemaValCons cons);

}
