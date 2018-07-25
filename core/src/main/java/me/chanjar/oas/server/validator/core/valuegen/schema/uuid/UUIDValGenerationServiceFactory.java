package me.chanjar.oas.server.validator.core.valuegen.schema.uuid;

import me.chanjar.oas.server.validator.core.value.schema.UUIDVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerator;

import static me.chanjar.oas.server.validator.core.valuegen.schema.PrimitiveSchemaValGenerationServiceFactoryHelper.addGenerators;

public abstract class UUIDValGenerationServiceFactory {

  private UUIDValGenerationServiceFactory() {
    // singleton
  }

  /**
   * Create a {@link UUIDValGenerationService} with {@link SchemaValGenerator}s
   *
   * @param generator
   * @param generators
   * @return
   */
  public static UUIDValGenerationService binary(SchemaValGenerator generator,
      SchemaValGenerator... generators) {
    UUIDValGenerationService service = new UUIDValGenerationService();
    addGenerators(service, generator, generators);
    return service;
  }

  /**
   * Create a default good {@link UUIDValGenerationService}.
   * <p>
   * Generators:
   * <ol>
   * <li>{@link GoodUUIDValGenerator}</li>
   * </ol>
   * </p>
   *
   * @return
   */
  public static UUIDValGenerationService goodUUID() {
    return binary(new GoodUUIDValGenerator());
  }

  /**
   * Create a {@link UUIDValGenerationService} with fixed values
   *
   * @param value
   * @param values
   * @return
   */
  public static UUIDValGenerationService fixedUUID(String value, String... values) {
    UUIDValGenerationService service = new UUIDValGenerationService();
    addGenerators(service, d -> new UUIDVal(d), value, values);
    return service;
  }

}
