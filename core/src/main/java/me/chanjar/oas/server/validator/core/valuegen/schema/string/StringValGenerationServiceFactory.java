package me.chanjar.oas.server.validator.core.valuegen.schema.string;

import me.chanjar.oas.server.validator.core.value.schema.StringVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerator;
import me.chanjar.oas.server.validator.core.valuegen.schema.special.IgnoredValGenerator;
import me.chanjar.oas.server.validator.core.valuegen.schema.special.NullValGenerator;

import static me.chanjar.oas.server.validator.core.valuegen.schema.PrimitiveSchemaValGenerationServiceFactoryHelper.addGenerators;

public abstract class StringValGenerationServiceFactory {

  private StringValGenerationServiceFactory() {
    // singleton
  }

  /**
   * Create a {@link StringValGenerationService} with {@link SchemaValGenerator}s
   *
   * @param generator
   * @param generators
   * @return
   */
  public static StringValGenerationService string(SchemaValGenerator generator,
      SchemaValGenerator... generators) {
    StringValGenerationService service = new StringValGenerationService();
    addGenerators(service, generator, generators);
    return service;
  }

  /**
   * Create a default good {@link StringValGenerationService}.
   * <p>
   * Good Generators:
   * <ol>
   * <li>{@link GoodStringValGenerator1}</li>
   * <li>{@link GoodStringValGenerator2}</li>
   * <li>{@link GoodStringValGenerator3}</li>
   * <li>{@link IgnoredValGenerator} in good mode</li>
   * <li>{@link NullValGenerator} in good mode</li>
   * </ol>
   * </p>
   *
   * @return
   */
  public static StringValGenerationService goodString() {

    return string(
        new GoodStringValGenerator1(),
        new GoodStringValGenerator2(),
        new GoodStringValGenerator3(),
        new IgnoredValGenerator(true),
        new NullValGenerator(true)
    );

  }

  /**
   * Create a default bad {@link StringValGenerationService}.
   * <p>
   * Bad Generators:
   * <ol>
   * <li>{@link BadStringValGenerator1}</li>
   * <li>{@link BadStringValGenerator2}</li>
   * <li>{@link IgnoredValGenerator} in bad mode</li>
   * <li>{@link NullValGenerator} in bad mode</li>
   * </ol>
   * </p>
   *
   * @return
   */

  public static StringValGenerationService badString() {

    return string(
        new BadStringValGenerator1(),
        new BadStringValGenerator2(),
        new IgnoredValGenerator(false),
        new NullValGenerator(false)
    );

  }

  /**
   * Create a {@link StringValGenerationService} with fixed values
   *
   * @param value
   * @param values
   * @return
   */
  public static StringValGenerationService fixedString(String value, String... values) {
    StringValGenerationService service = new StringValGenerationService();
    addGenerators(service, d -> new StringVal(d), value, values);
    return service;
  }

}
