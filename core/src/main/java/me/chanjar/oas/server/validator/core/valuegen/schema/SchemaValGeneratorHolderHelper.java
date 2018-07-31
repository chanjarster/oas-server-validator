package me.chanjar.oas.server.validator.core.valuegen.schema;

import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.special.FixedSchemaValGenerator;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.function.Function;

public abstract class SchemaValGeneratorHolderHelper {

  private SchemaValGeneratorHolderHelper() {
    // singleton
  }

  public static void addGeneratorsFor(SchemaValGeneratorHolder service,
      SchemaValGenerator generator,
      SchemaValGenerator... generators) {
    service.addGenerator(generator);
    if (ArrayUtils.isNotEmpty(generators)) {
      service.addGenerators(Arrays.asList(generators));
    }
  }

  public static <T> void addGeneratorsFor(SchemaValGeneratorHolder service,
      Function<T, SchemaVal> schemaValConstructor,
      T value, T... values) {
    service.addGenerator(new FixedSchemaValGenerator(schemaValConstructor.apply(value)));
    if (ArrayUtils.isNotEmpty(values)) {
      Arrays.stream(values)
          .map(schemaValConstructor)
          .forEach(g -> service.addGenerator(new FixedSchemaValGenerator(g)));
    }
  }

}
