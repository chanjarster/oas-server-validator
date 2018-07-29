package me.chanjar.oas.server.validator.core.valuegen.schema.special;

import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValCons;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerator;

public class IgnoredValGenerator implements SchemaValGenerator<Schema, SchemaVal> {

  private final boolean goodMode;

  /**
   * @param goodMode if true, in good mode;
   *                 if false, in bad mode.
   */
  public IgnoredValGenerator(boolean goodMode) {
    this.goodMode = goodMode;
  }

  /**
   * @param schema
   * @param cons
   * @return if goodMode == true && {@link SchemaValCons#isRequired()} == false, return true<br>
   * if goodMode == false && {@link SchemaValCons#isRequired()} == true, return true
   */
  @Override
  public boolean supports(Schema schema, SchemaValCons cons) {
    if (goodMode) {
      return !cons.isRequired();
    }
    return cons.isRequired();
  }

  /**
   * @param schema
   * @param cons
   * @return {@link SchemaVal#IGNORED_VAL}
   */
  @Override
  public SchemaVal generate(Schema schema, SchemaValCons cons) {
    return SchemaVal.IGNORED_VAL;
  }

}
