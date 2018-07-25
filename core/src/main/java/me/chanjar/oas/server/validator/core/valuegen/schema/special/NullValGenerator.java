package me.chanjar.oas.server.validator.core.valuegen.schema.special;

import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.value.schema.StringVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValCons;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerator;

/**
 * A null {@link SchemaVal} generator
 */
public class NullValGenerator implements SchemaValGenerator<Schema, SchemaVal> {

  private static final StringVal NULL_VAL = new StringVal(null);

  private final boolean goodMode;

  /**
   * @param goodMode if true, in good mode;
   *                 if false, in bad mode.
   */
  public NullValGenerator(boolean goodMode) {
    this.goodMode = goodMode;
  }

  /**
   * @param schema
   * @param cons
   * @return if goodMode == true && {@link SchemaValCons#isNullable()} == true, return true<br>
   * if goodMode == false && {@link SchemaValCons#isNullable()} == false, return true
   */
  @Override
  public boolean supports(Schema schema, SchemaValCons cons) {
    if (goodMode) {
      return cons.isNullable();
    }
    return !cons.isNullable();
  }

  /**
   * @param schema
   * @param cons
   * @return a StringVal(null)
   */
  @Override
  public SchemaVal generate(Schema schema, SchemaValCons cons) {
    return NULL_VAL;
  }

}
