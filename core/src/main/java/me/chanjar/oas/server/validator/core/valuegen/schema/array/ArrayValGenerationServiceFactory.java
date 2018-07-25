package me.chanjar.oas.server.validator.core.valuegen.schema.array;

public abstract class ArrayValGenerationServiceFactory {
  private ArrayValGenerationServiceFactory() {
    // singleton
  }

  public static ArrayValGenerationService goodArray() {
    ArrayValGenerationService service = new ArrayValGenerationService();
    // TODO
    return service;
  }

  public static ArrayValGenerationService badArray() {

    // TODO
    return null;
  }
}
