package me.chanjar.oas.server.validator.core.encoder.schema;

import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import org.slf4j.Logger;

import java.util.function.Predicate;

class PrimitivePredicate implements Predicate<SchemaVal> {

  private final Logger logger;

  public PrimitivePredicate(Logger logger) {
    this.logger = logger;
  }

  @Override
  public boolean test(SchemaVal schemaVal) {
    boolean primitive = SchemaVal.isPrimitive(schemaVal);
    if (!primitive) {
      logger.warn("Array element is not primitive: {}. Skipped.", schemaVal.getClass().getSimpleName());
    }
    return primitive;
  }
}
