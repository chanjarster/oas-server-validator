package me.chanjar.oas.server.validator.core.valuegen.schema.password;

import me.chanjar.oas.server.validator.core.value.schema.PasswordVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGeneratorHolderHelper;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerator;
import me.chanjar.oas.server.validator.core.valuegen.schema.special.IgnoredValGenerator;
import me.chanjar.oas.server.validator.core.valuegen.schema.special.NullValGenerator;

import static me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGeneratorHolderHelper.addGeneratorsTo;

public abstract class PasswordValGenerationServiceFactory {

  private PasswordValGenerationServiceFactory() {
    // singleton
  }

  /**
   * Create a {@link PasswordValGenerationService} with {@link SchemaValGenerator}s
   *
   * @param generator
   * @param generators
   * @return
   */
  public static PasswordValGenerationService password(SchemaValGenerator generator,
      SchemaValGenerator... generators) {
    PasswordValGenerationService service = new PasswordValGenerationService();
    SchemaValGeneratorHolderHelper.addGeneratorsTo(service, generator, generators);
    return service;
  }

  /**
   * Create a default good {@link PasswordValGenerationService}.
   * <p>
   * Good Generators:
   * <ol>
   * <li>{@link GoodPasswordValGenerator1}</li>
   * <li>{@link GoodPasswordValGenerator2}</li>
   * <li>{@link GoodPasswordValGenerator3}</li>
   * <li>{@link IgnoredValGenerator} in good mode</li>
   * <li>{@link NullValGenerator} in good mode</li>
   * </ol>
   * </p>
   *
   * @return
   */
  public static PasswordValGenerationService goodPassword() {
    return password(
        new GoodPasswordValGenerator1(),
        new GoodPasswordValGenerator2(),
        new GoodPasswordValGenerator3(),
        new IgnoredValGenerator(true),
        new NullValGenerator(true)
    );
  }

  /**
   * Create a default bad {@link PasswordValGenerationService}.
   * <p>
   * Bad Generators:
   * <ol>
   * <li>{@link BadPasswordValGenerator1}</li>
   * <li>{@link BadPasswordValGenerator2}</li>
   * <li>{@link IgnoredValGenerator} in bad mode</li>
   * <li>{@link NullValGenerator} in bad mode</li>
   * </ol>
   * </p>
   *
   * @return
   */
  public static PasswordValGenerationService badPassword() {
    return password(
        new BadPasswordValGenerator1(),
        new BadPasswordValGenerator2(),
        new IgnoredValGenerator(false),
        new NullValGenerator(false)
    );

  }

  /**
   * Create a {@link PasswordValGenerationService} with fixed values
   *
   * @param value
   * @param values
   * @return
   */
  public static PasswordValGenerationService fixedPassword(String value, String... values) {
    PasswordValGenerationService service = new PasswordValGenerationService();
    SchemaValGeneratorHolderHelper.addGeneratorsTo(service, v -> new PasswordVal(v), value, values);
    return service;
  }

}
