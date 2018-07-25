package me.chanjar.oas.server.validator.core.valuegen.schema.datetime;

import me.chanjar.oas.server.validator.core.value.schema.DateTimeVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerator;

import java.util.Date;

import static me.chanjar.oas.server.validator.core.valuegen.schema.PrimitiveSchemaValGenerationServiceFactoryHelper.addGenerators;

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
  public static DateTimeValGenerationService date(SchemaValGenerator generator,
      SchemaValGenerator... generators) {
    DateTimeValGenerationService service = new DateTimeValGenerationService();
    addGenerators(service, generator, generators);
    return service;
  }

  /**
   * Create a default good {@link DateTimeValGenerationService}.
   * <p>
   * Generators:
   * <ol>
   * <li>{@link GoodDateTimeValGenerator}</li>
   * </ol>
   * </p>
   *
   * @return
   */
  public static DateTimeValGenerationService goodDateTime() {
    return date(new GoodDateTimeValGenerator());
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
    addGenerators(service, d -> new DateTimeVal(d), value, values);
    return service;
  }

}
