package me.chanjar.oas.server.validator.core.valuegen.schema.datetime;

import java.util.Date;

public abstract class DateTimeValGenerationServiceFactory {

  private DateTimeValGenerationServiceFactory() {
    // singleton
  }

  /**
   * Create a default {@link DateTimeValGenerationService}.
   * <p>
   * Good Generators:
   * <ol>
   * <li>{@link GoodDateTimeValGenerator}</li>
   * </ol>
   * </p>
   * <p>
   * Bad Generators:
   * <ol>
   * <li>None</li>
   * </ol>
   * </p>
   *
   * @return
   */
  public static DateTimeValGenerationService dateTime() {
    DateTimeValGenerationService service = new DateTimeValGenerationService();
    service.addGoodGenerator(new GoodDateTimeValGenerator());
    return service;
  }

  /**
   * Create a {@link DateTimeValGenerationService} with good {@link FixedDateTimeValGenerator}s
   *
   * @param value
   * @param values
   * @return
   */
  public static DateTimeValGenerationService dateWithGood(Date value, Date... values) {
    DateTimeValGenerationService service = new DateTimeValGenerationService();
    service.addGoodGenerator(new FixedDateTimeValGenerator(value));
    for (Date v : values) {
      service.addGoodGenerator(new FixedDateTimeValGenerator(v));
    }
    return service;
  }

  /**
   * Create a {@link DateTimeValGenerationService} with bad {@link FixedDateTimeValGenerator}s
   *
   * @param value
   * @param values
   * @return
   */
  public static DateTimeValGenerationService dateWithBad(Date value, Date... values) {
    DateTimeValGenerationService service = new DateTimeValGenerationService();
    service.addBadGenerator(new FixedDateTimeValGenerator(value));
    for (Date v : values) {
      service.addBadGenerator(new FixedDateTimeValGenerator(v));
    }
    return service;
  }

}
