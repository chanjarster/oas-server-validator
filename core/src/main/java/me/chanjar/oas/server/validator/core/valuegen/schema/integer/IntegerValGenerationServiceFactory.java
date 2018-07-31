package me.chanjar.oas.server.validator.core.valuegen.schema.integer;

import me.chanjar.oas.server.validator.core.value.schema.IntegerVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGeneratorHolderHelper;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerator;
import me.chanjar.oas.server.validator.core.valuegen.schema.special.IgnoredValGenerator;
import me.chanjar.oas.server.validator.core.valuegen.schema.special.NullValGenerator;

import static me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGeneratorHolderHelper.addGeneratorsFor;

public abstract class IntegerValGenerationServiceFactory {

  private IntegerValGenerationServiceFactory() {
    // singleton
  }

  /**
   * Create a {@link IntegerValGenerationService} with {@link SchemaValGenerator}s
   *
   * @param generator
   * @param generators
   * @return
   */
  public static IntegerValGenerationService integer(SchemaValGenerator generator,
      SchemaValGenerator... generators) {
    IntegerValGenerationService service = new IntegerValGenerationService();
    SchemaValGeneratorHolderHelper.addGeneratorsFor(service, generator, generators);
    return service;
  }

  /**
   * Create a default good {@link IntegerValGenerationService}.
   * <p>
   * Generators:
   * <ol>
   * <li>{@link GoodIntegerValGenerator1}</li>
   * <li>{@link GoodIntegerValGenerator2}</li>
   * <li>{@link GoodIntegerValGenerator3}</li>
   * <li>{@link IgnoredValGenerator} in good mode</li>
   * <li>{@link NullValGenerator} in good mode</li>
   * </ol>
   * </p>
   *
   * @return
   */
  public static IntegerValGenerationService goodInteger() {

    return integer(
        new GoodIntegerValGenerator1(),
        new GoodIntegerValGenerator2(),
        new GoodIntegerValGenerator3(),
        new IgnoredValGenerator(true),
        new NullValGenerator(true)
    );
  }

  /**
   * Create a default bad {@link IntegerValGenerationService}.
   * <p>
   * Generators:
   * <ol>
   * <li>{@link BadIntegerValGenerator1}</li>
   * <li>{@link BadIntegerValGenerator2}</li>
   * <li>{@link IgnoredValGenerator} in bad mode</li>
   * <li>{@link NullValGenerator} in bad mode</li>
   * </ol>
   * </p>
   *
   * @return
   */
  public static IntegerValGenerationService badInteger() {
    return integer(
        new BadIntegerValGenerator1(),
        new BadIntegerValGenerator2(),
        new IgnoredValGenerator(false),
        new NullValGenerator(false)
    );
  }

  /**
   * Create a {@link IntegerValGenerationService} with fixed values
   *
   * @param value
   * @param values
   * @return
   */
  public static IntegerValGenerationService fixedInteger(Integer value, Integer... values) {
    IntegerValGenerationService service = new IntegerValGenerationService();
    addGeneratorsFor(service, v -> new IntegerVal(v), value, values);
    return service;
  }

}
