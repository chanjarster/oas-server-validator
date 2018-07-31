package me.chanjar.oas.server.validator.core.valuegen.schema.datetime;

import me.chanjar.oas.server.validator.core.value.schema.DateTimeVal;
import me.chanjar.oas.server.validator.core.value.schema.StringVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerator;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGeneratorHolderHelper;
import me.chanjar.oas.server.validator.core.valuegen.schema.special.IgnoredValGenerator;
import me.chanjar.oas.server.validator.core.valuegen.schema.special.NullValGenerator;

import java.util.Date;

import static me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGeneratorHolderHelper.addGeneratorsFor;

public abstract class DateTimeValGenerationServiceFactory {

  private DateTimeValGenerationServiceFactory() {
    // singleton
  }

  /**
   * Create a {@link DateTimeValGenerationService} with {@link SchemaValGenerator}s
   *
   * @param generator
   * @param generators
   * @return
   */
  public static DateTimeValGenerationService dateTime(SchemaValGenerator generator,
      SchemaValGenerator... generators) {
    DateTimeValGenerationService service = new DateTimeValGenerationService();
    SchemaValGeneratorHolderHelper.addGeneratorsFor(service, generator, generators);
    return service;
  }

  /**
   * Create a default good {@link DateTimeValGenerationService}.
   * <p>
   * Generators:
   * <ol>
   * <li>{@link GoodDateTimeValGenerator}</li>
   * <li>{@link NullValGenerator}, good mode</li>
   * <li>{@link IgnoredValGenerator}, good mode</li>
   * </ol>
   * </p>
   *
   * @return
   */
  public static DateTimeValGenerationService goodDateTime() {
    return dateTime(new GoodDateTimeValGenerator(), new NullValGenerator(true), new IgnoredValGenerator(true));
  }

  /**
   * Create a default bad {@link DateTimeValGenerationService}.
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
  public static DateTimeValGenerationService badDateTime() {
    DateTimeValGenerationService service = new DateTimeValGenerationService();
    addGeneratorsFor(service, d -> new StringVal(d), "hijklm", "uvwxyz");
    addGeneratorsFor(service, new NullValGenerator(false), new IgnoredValGenerator(false));
    return service;
  }

  /**
   * Create a {@link DateTimeValGenerationService} with fixed values
   *
   * @param value
   * @param values
   * @return
   */
  public static DateTimeValGenerationService fixedDateTime(Date value, Date... values) {
    DateTimeValGenerationService service = new DateTimeValGenerationService();
    addGeneratorsFor(service, d -> new DateTimeVal(d), value, values);
    return service;
  }

}
