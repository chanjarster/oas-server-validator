package me.chanjar.oas.server.validator.core.valuegen.schema.binary;

import me.chanjar.oas.server.validator.core.value.schema.BinaryVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerator;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGeneratorHolderHelper;
import me.chanjar.oas.server.validator.core.valuegen.schema.special.IgnoredValGenerator;
import me.chanjar.oas.server.validator.core.valuegen.schema.special.NullValGenerator;

import static me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGeneratorHolderHelper.addGeneratorsFor;

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
    SchemaValGeneratorHolderHelper.addGeneratorsFor(service, generator, generators);
    return service;
  }

  /**
   * Create a default good {@link BinaryValGenerationService}.
   * <p>
   * Generators:
   * <ol>
   * <li>{@link GoodBinaryValGenerator}</li>
   * <li>{@link NullValGenerator}, good mode</li>
   * <li>{@link IgnoredValGenerator}, good mode</li>
   * </ol>
   * </p>
   *
   * @return
   */
  public static BinaryValGenerationService goodBinary() {
    return binary(new GoodBinaryValGenerator(), new NullValGenerator(true), new IgnoredValGenerator(true));
  }

  /**
   * Create a default bad {@link BinaryValGenerationService}.
   * <p>
   * Generates:
   * <ol>
   * <li>abcdefg</li>
   * <li>uvwxyz</li>
   * <li>{@link NullValGenerator}, bad mode</li>
   * <li>{@link IgnoredValGenerator}, bad mode</li>
   * </ol>
   * </p>
   *
   * @return
   */
  public static BinaryValGenerationService badBinary() {
    BinaryValGenerationService service = fixedBinary("abcdefg", "uvwxyz");
    addGeneratorsFor(service, new NullValGenerator(false), new IgnoredValGenerator(false));
    return service;
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
    addGeneratorsFor(service, d -> new BinaryVal(d), value, values);
    return service;
  }

}
