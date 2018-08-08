package me.chanjar.oas.server.validator.core.valuegen.schema.uuid;

import me.chanjar.oas.server.validator.core.value.schema.StringVal;
import me.chanjar.oas.server.validator.core.value.schema.UUIDVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerator;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGeneratorHolderHelper;
import me.chanjar.oas.server.validator.core.valuegen.schema.special.IgnoredValGenerator;
import me.chanjar.oas.server.validator.core.valuegen.schema.special.NullValGenerator;

import static me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGeneratorHolderHelper.addGeneratorsTo;

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
  public static UUIDValGenerationService uuid(SchemaValGenerator generator,
      SchemaValGenerator... generators) {
    UUIDValGenerationService service = new UUIDValGenerationService();
    SchemaValGeneratorHolderHelper.addGeneratorsTo(service, generator, generators);
    return service;
  }

  /**
   * Create a default good {@link UUIDValGenerationService}.
   * <p>
   * Generators:
   * <ol>
   * <li>{@link GoodUUIDValGenerator}</li>
   * <li>{@link NullValGenerator}, good mode</li>
   * <li>{@link IgnoredValGenerator}, good mode</li>
   * </ol>
   * </p>
   *
   * @return
   */
  public static UUIDValGenerationService goodUUID() {
    return uuid(new GoodUUIDValGenerator(), new NullValGenerator(true), new IgnoredValGenerator(true));
  }

  /**
   * Create a default bad {@link UUIDValGenerationService}.
   * <p>
   * Generators:
   * <ol>
   * <li>hijklm</li>
   * <li>uvwxyz</li>
   * <li>{@link NullValGenerator}, bad mode</li>
   * <li>{@link IgnoredValGenerator}, bad mode</li>
   * </ol>
   * </p>
   *
   * @return
   */
  public static UUIDValGenerationService badUUID() {
    UUIDValGenerationService service = new UUIDValGenerationService();
    SchemaValGeneratorHolderHelper.addGeneratorsTo(service, d -> new StringVal(d), "hijklm", "uvwxyz");
    addGeneratorsTo(service, new NullValGenerator(false), new IgnoredValGenerator(false));
    return service;
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
    SchemaValGeneratorHolderHelper.addGeneratorsTo(service, d -> new UUIDVal(d), value, values);
    return service;
  }

}
