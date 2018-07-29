package me.chanjar.oas.server.validator.core.valuegen.schema.date;

import me.chanjar.oas.server.validator.core.value.schema.DateVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerator;
import me.chanjar.oas.server.validator.core.valuegen.schema.special.IgnoredValGenerator;
import me.chanjar.oas.server.validator.core.valuegen.schema.special.NullValGenerator;

import java.util.Date;

import static me.chanjar.oas.server.validator.core.valuegen.schema.PrimitiveSchemaValGenerationServiceFactoryHelper.addGenerators;

public abstract class DateValGenerationServiceFactory {

  private DateValGenerationServiceFactory() {
    // singleton
  }

  /**
   * Create a {@link DateValGenerationService} with {@link SchemaValGenerator}s
   *
   * @param generator
   * @param generators
   * @return
   */
  public static DateValGenerationService date(SchemaValGenerator generator,
      SchemaValGenerator... generators) {
    DateValGenerationService service = new DateValGenerationService();
    addGenerators(service, generator, generators);
    return service;
  }

  /**
   * Create a default good {@link DateValGenerationService}.
   * <p>
   * Generators:
   * <ol>
   * <li>{@link GoodDateValGenerator}</li>
   * </ol>
   * </p>
   *
   * @return
   */
  public static DateValGenerationService goodDate() {
    return date(new GoodDateValGenerator(), new NullValGenerator(true), new IgnoredValGenerator(true));
  }

  /**
   * Create a {@link DateValGenerationService} with fixed values
   *
   * @param value
   * @param values
   * @return
   */
  public static DateValGenerationService fixedDate(Date value, Date... values) {
    DateValGenerationService service = new DateValGenerationService();
    addGenerators(service, d -> new DateVal(d), value, values);
    return service;
  }

}
