package me.chanjar.oas.server.validator.core.valuegen.schema.binary;

import me.chanjar.oas.server.validator.core.value.schema.BinaryVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerator;

import static me.chanjar.oas.server.validator.core.valuegen.schema.PrimitiveSchemaValGenerationServiceFactoryHelper.addGenerators;

public abstract class BinaryValGenerationServiceFactory {

  private BinaryValGenerationServiceFactory() {
    // singleton
  }

  /**
   * Create a {@link BinaryValGenerationService} with {@link SchemaValGenerator}s
   *
   * @param generator
   * @param generators
   * @return
   */
  public static BinaryValGenerationService binary(SchemaValGenerator generator,
      SchemaValGenerator... generators) {
    BinaryValGenerationService service = new BinaryValGenerationService();
    addGenerators(service, generator, generators);
    return service;
  }

  /**
   * Create a default good {@link BinaryValGenerationService}.
   * <p>
   * Generators:
   * <ol>
   * <li>{@link GoodBinaryValGenerator}</li>
   * </ol>
   * </p>
   *
   * @return
   */
  public static BinaryValGenerationService goodBinary() {
    return binary(new GoodBinaryValGenerator());
  }

  /**
   * Create a {@link BinaryValGenerationService} with fixed values
   *
   * @param value
   * @param values
   * @return
   */
  public static BinaryValGenerationService fixedBinary(String value, String... values) {
    BinaryValGenerationService service = new BinaryValGenerationService();
    addGenerators(service, d -> new BinaryVal(d), value, values);
    return service;
  }

}
