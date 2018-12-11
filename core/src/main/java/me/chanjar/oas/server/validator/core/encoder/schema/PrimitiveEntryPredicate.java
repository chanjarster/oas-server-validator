package me.chanjar.oas.server.validator.core.encoder.schema;

import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import org.slf4j.Logger;

import java.util.Map;
import java.util.function.Predicate;

class PrimitiveEntryPredicate implements Predicate<Map.Entry<String, SchemaVal>> {

    private final Logger logger;

    public PrimitiveEntryPredicate(Logger logger) {
      this.logger = logger;
    }

    @Override
    public boolean test(Map.Entry<String, SchemaVal> entry) {
      SchemaVal e = entry.getValue();
      boolean primitive = SchemaVal.isPrimitive(e);
      if (!primitive) {
        logger.warn("Object property: {} is not primitive: {}, Skipped", entry.getKey(),
            e.getClass().getSimpleName());
      }
      return primitive;
    }
    
  }
