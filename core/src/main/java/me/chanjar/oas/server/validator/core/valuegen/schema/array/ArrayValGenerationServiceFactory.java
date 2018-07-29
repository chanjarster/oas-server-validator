package me.chanjar.oas.server.validator.core.valuegen.schema.array;

import me.chanjar.oas.server.validator.core.value.schema.ArrayVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.special.IgnoredValGenerator;
import me.chanjar.oas.server.validator.core.valuegen.schema.special.NullValGenerator;

import java.util.Arrays;

public abstract class ArrayValGenerationServiceFactory {
  private ArrayValGenerationServiceFactory() {
    // singleton
  }

  public static ArrayValGenerationService goodArray() {
    ArrayValGenerationService service = new ArrayValGenerationService();
    // TODO
    service.addGenerators(Arrays.asList(new NullValGenerator(true), new IgnoredValGenerator(true)));
    return service;
  }

  public static ArrayValGenerationService badArray() {
    ArrayValGenerationService service = new ArrayValGenerationService();
    // TODO
    service.addGenerators(Arrays.asList(new NullValGenerator(false), new IgnoredValGenerator(false)));
    return service;
  }

  public static ArrayValGenerationService fixedArray(ArrayVal value, ArrayVal... values) {
    // TODO
    return null;
  }
}
