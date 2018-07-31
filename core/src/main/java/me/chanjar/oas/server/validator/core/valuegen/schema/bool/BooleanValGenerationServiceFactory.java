package me.chanjar.oas.server.validator.core.valuegen.schema.bool;

import me.chanjar.oas.server.validator.core.value.schema.BooleanVal;
import me.chanjar.oas.server.validator.core.value.schema.StringVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerator;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGeneratorHolderHelper;
import me.chanjar.oas.server.validator.core.valuegen.schema.special.IgnoredValGenerator;
import me.chanjar.oas.server.validator.core.valuegen.schema.special.NullValGenerator;

import static me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGeneratorHolderHelper.addGeneratorsFor;

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
    SchemaValGeneratorHolderHelper.addGeneratorsFor(service, generator, generators);
    return service;
  }

  /**
   * Create a default good {@link BooleanValGenerationService}.
   * <p>
   * Generators:
   * <ol>
   * <li>{@link GoodBooleanValGenerator}</li>
   * <li>{@link NullValGenerator}, good mode</li>
   * <li>{@link IgnoredValGenerator}, good mode</li>
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
   * Create a default bad {@link BooleanValGenerationService}.
   * <p>
   * Generators:
   * <ol>
   * <li>abcdefg</li>
   * <li>uvwxyz</li>
   * <li>{@link NullValGenerator}, bad mode</li>
   * <li>{@link IgnoredValGenerator}, bad mode</li>
   * </ol>
   * </p>
   * </p>
   *
   * @return
   */
  public static BooleanValGenerationService badBool() {
    BooleanValGenerationService service = new BooleanValGenerationService();
    addGeneratorsFor(service, d -> new StringVal(d), "abcdefg", "uvwxyz");
    addGeneratorsFor(service, new NullValGenerator(false), new IgnoredValGenerator(false));
    return service;
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
    addGeneratorsFor(service, d -> new BooleanVal(d), value, values);
    return service;
  }

}
