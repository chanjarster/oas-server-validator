package me.chanjar.oas.server.validator.core.valuegen.schema.array;

import me.chanjar.oas.server.validator.core.value.schema.ArrayVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerator;
import me.chanjar.oas.server.validator.core.valuegen.schema.special.IgnoredValGenerator;
import me.chanjar.oas.server.validator.core.valuegen.schema.special.NullValGenerator;

import static me.chanjar.oas.server.validator.core.valuegen.schema.PrimitiveSchemaValGenerationServiceFactoryHelper.addGenerators;

public abstract class ArrayValGenerationServiceFactory {

  private ArrayValGenerationServiceFactory() {
    // singleton
  }

  /**
   * Create a {@link ArrayValGenerationService} with {@link SchemaValGenerator}s
   *
   * @param generator
   * @param generators
   * @return
   */
  public static ArrayValGenerationService array(SchemaValGenerator generator,
      SchemaValGenerator... generators) {
    ArrayValGenerationService service = new ArrayValGenerationService();
    addGenerators(service, generator, generators);
    return service;
  }

  /**
   * Create a default good {@link ArrayValGenerationService}.
   * <p>
   * Generators:
   * <ol>
   * <li>{@link GoodArrayValGenerator1}</li>
   * <li>{@link GoodArrayValGenerator2}</li>
   * <li>{@link GoodArrayValGenerator3}</li>
   * <li>{@link IgnoredValGenerator} in good mode</li>
   * <li>{@link NullValGenerator} in good mode</li>
   * </ol>
   * </p>
   *
   * @return
   */
  public static ArrayValGenerationService goodArray() {

    return array(
        new GoodArrayValGenerator1(),
        new GoodArrayValGenerator2(),
        new GoodArrayValGenerator3(),
        new IgnoredValGenerator(true),
        new NullValGenerator(true)
    );
  }

  /**
   * Create a default bad {@link ArrayValGenerationService}.
   * <p>
   * Generators:
   * <ol>
   * <li>{@link BadArrayValGenerator1}</li>
   * <li>{@link BadArrayValGenerator2}</li>
   * <li>{@link IgnoredValGenerator} in bad mode</li>
   * <li>{@link NullValGenerator} in bad mode</li>
   * </ol>
   * </p>
   *
   * @return
   */
  public static ArrayValGenerationService badArray() {
    return array(
        new BadArrayValGenerator1(),
        new BadArrayValGenerator2(),
        new IgnoredValGenerator(false),
        new NullValGenerator(false)
    );
  }

  /**
   * Create a {@link ArrayValGenerationService} with fixed values
   *
   * @param value
   * @param values
   * @return
   */
  public static ArrayValGenerationService fixedArray(SchemaVal[] value, SchemaVal[]... values) {
    ArrayValGenerationService service = new ArrayValGenerationService();
    addGenerators(service, v -> new ArrayVal(v), value, values);
    return service;
  }

}
