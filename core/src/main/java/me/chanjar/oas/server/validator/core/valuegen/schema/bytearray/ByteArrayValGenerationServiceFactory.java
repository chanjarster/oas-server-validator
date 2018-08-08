package me.chanjar.oas.server.validator.core.valuegen.schema.bytearray;

import me.chanjar.oas.server.validator.core.value.schema.ByteArrayVal;
import me.chanjar.oas.server.validator.core.value.schema.StringVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerator;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGeneratorHolderHelper;
import me.chanjar.oas.server.validator.core.valuegen.schema.special.IgnoredValGenerator;
import me.chanjar.oas.server.validator.core.valuegen.schema.special.NullValGenerator;

import static me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGeneratorHolderHelper.addGeneratorsTo;

public abstract class ByteArrayValGenerationServiceFactory {

  private ByteArrayValGenerationServiceFactory() {
    // singleton
  }

  /**
   * Create a {@link ByteArrayValGenerationService} with {@link SchemaValGenerator}s
   *
   * @param generator
   * @param generators
   * @return
   */
  public static ByteArrayValGenerationService byteArray(SchemaValGenerator generator,
      SchemaValGenerator... generators) {
    ByteArrayValGenerationService service = new ByteArrayValGenerationService();
    SchemaValGeneratorHolderHelper.addGeneratorsTo(service, generator, generators);
    return service;
  }

  /**
   * Create a default good {@link ByteArrayValGenerationService}.
   * <p>
   * Generators:
   * <ol>
   * <li>{@link GoodByteArrayValGenerator}</li>
   * <li>{@link NullValGenerator}, good mode</li>
   * <li>{@link IgnoredValGenerator}, good mode</li>
   * </ol>
   * </p>
   *
   * @return
   */
  public static ByteArrayValGenerationService goodByteArray() {
    return byteArray(new GoodByteArrayValGenerator(), new NullValGenerator(true), new IgnoredValGenerator(true));
  }

  /**
   * Create a default bad {@link ByteArrayValGenerationService}.
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
  public static ByteArrayValGenerationService badByteArray() {
    ByteArrayValGenerationService service = new ByteArrayValGenerationService();
    SchemaValGeneratorHolderHelper.addGeneratorsTo(service, d -> new StringVal(d), "hijklm", "uvwxyz");
    addGeneratorsTo(service, new NullValGenerator(false), new IgnoredValGenerator(false));
    return service;
  }

  /**
   * Create a {@link ByteArrayValGenerationService} with fixed values
   *
   * @param value
   * @param values
   * @return
   */
  public static ByteArrayValGenerationService fixedByteArray(Byte[] value, Byte[]... values) {
    ByteArrayValGenerationService service = new ByteArrayValGenerationService();
    SchemaValGeneratorHolderHelper.addGeneratorsTo(service, v -> new ByteArrayVal(v), value, values);
    return service;
  }

}
