package me.chanjar.oas.server.validator.core.valuegen.schema.bool;

import me.chanjar.oas.server.validator.core.value.schema.BooleanVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerator;
import me.chanjar.oas.server.validator.core.valuegen.schema.special.IgnoredValGenerator;
import me.chanjar.oas.server.validator.core.valuegen.schema.special.NullValGenerator;

import static me.chanjar.oas.server.validator.core.valuegen.schema.PrimitiveSchemaValGenerationServiceFactoryHelper.addGenerators;

public abstract class BooleanValGenerationServiceFactory {

  private BooleanValGenerationServiceFactory() {
    // singleton
  }

  /**
   * Create a {@link BooleanValGenerationService} with {@link SchemaValGenerator}s
   *
   * @param generator
   * @param generators
   * @return
   */
  public static BooleanValGenerationService bool(SchemaValGenerator generator,
      SchemaValGenerator... generators) {
    BooleanValGenerationService service = new BooleanValGenerationService();
    addGenerators(service, generator, generators);
    return service;
  }

  /**
   * Create a default good {@link BooleanValGenerationService}.
   * <p>
   * Generators:
   * <ol>
   * <li>{@link GoodBooleanValGenerator}</li>
   * </ol>
   * </p>
   * </p>
   *
   * @return
   */
  public static BooleanValGenerationService goodBool() {
    return bool(new GoodBooleanValGenerator(), new NullValGenerator(true), new IgnoredValGenerator(true));
  }

  /**
   * Create a {@link BooleanValGenerationService} with fixed values
   *
   * @param value
   * @param values
   * @return
   */
  public static BooleanValGenerationService fixedBool(Boolean value, Boolean... values) {
    BooleanValGenerationService service = new BooleanValGenerationService();
    addGenerators(service, d -> new BooleanVal(d), value, values);
    return service;
  }

}
