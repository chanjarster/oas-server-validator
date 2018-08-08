package me.chanjar.oas.server.validator.core.valuegen.schema.number;

import me.chanjar.oas.server.validator.core.value.schema.NumberVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGeneratorHolderHelper;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerator;
import me.chanjar.oas.server.validator.core.valuegen.schema.special.IgnoredValGenerator;
import me.chanjar.oas.server.validator.core.valuegen.schema.special.NullValGenerator;

import java.math.BigDecimal;

import static me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGeneratorHolderHelper.addGeneratorsTo;

public abstract class NumberValGenerationServiceFactory {

  private NumberValGenerationServiceFactory() {
    // singleton
  }

  /**
   * Create a {@link NumberValGenerationService} with {@link SchemaValGenerator}s
   *
   * @param generator
   * @param generators
   * @return
   */
  public static NumberValGenerationService number(SchemaValGenerator generator,
      SchemaValGenerator... generators) {
    NumberValGenerationService service = new NumberValGenerationService();
    SchemaValGeneratorHolderHelper.addGeneratorsTo(service, generator, generators);
    return service;
  }

  /**
   * Create a default good {@link NumberValGenerationService}.
   * <p>
   * Generators:
   * <ol>
   * <li>{@link GoodNumberValGenerator1}</li>
   * <li>{@link GoodNumberValGenerator2}</li>
   * <li>{@link GoodNumberValGenerator3}</li>
   * <li>{@link IgnoredValGenerator} in good mode</li>
   * <li>{@link NullValGenerator} in good mode</li>
   * </ol>
   * </p>
   *
   * @return
   */
  public static NumberValGenerationService goodNumber() {

    return number(
        new GoodNumberValGenerator1(),
        new GoodNumberValGenerator2(),
        new GoodNumberValGenerator3(),
        new IgnoredValGenerator(true),
        new NullValGenerator(true)
    );

  }

  /**
   * Create a default bad {@link NumberValGenerationService}.
   * <p>
   * Generators:
   * <ol>
   * <li>{@link BadNumberValGenerator1}</li>
   * <li>{@link BadNumberValGenerator2}</li>
   * <li>{@link IgnoredValGenerator} in bad mode</li>
   * <li>{@link NullValGenerator} in bad mode</li>
   * </ol>
   * </p>
   *
   * @return
   */
  public static NumberValGenerationService badNumber() {

    return number(
        new BadNumberValGenerator1(),
        new BadNumberValGenerator2(),
        new IgnoredValGenerator(false),
        new NullValGenerator(false)
    );

  }

  /**
   * Create a {@link NumberValGenerationService} with fixed values
   *
   * @param value
   * @param values
   * @return
   */
  public static NumberValGenerationService fixedNumber(BigDecimal value, BigDecimal... values) {

    NumberValGenerationService service = new NumberValGenerationService();
    SchemaValGeneratorHolderHelper.addGeneratorsTo(service, v -> new NumberVal(v), value, values);
    return service;
  }

}
