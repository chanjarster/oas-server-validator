package me.chanjar.oas.server.validator.core.valuegen.schema.date;

import java.util.Date;

public abstract class DateValGenerationServiceFactory {

  private DateValGenerationServiceFactory() {
    // singleton
  }


  /**
   * Create a default {@link DateValGenerationService}.
   * <p>
   * Good Generators:
   * <ol>
   * <li>{@link GoodDateValGenerator}</li>
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
  public static DateValGenerationService date() {
    DateValGenerationService service = new DateValGenerationService();
    service.addGoodGenerator(new GoodDateValGenerator());
    return service;
  }

  /**
   * Create a {@link DateValGenerationService} with good {@link FixedDateValGenerator}s
   *
   * @param value
   * @param values
   * @return
   */
  public static DateValGenerationService dateWithGood(Date value, Date... values) {
    DateValGenerationService service = new DateValGenerationService();
    service.addGoodGenerator(new FixedDateValGenerator(value));
    for (Date v : values) {
      service.addGoodGenerator(new FixedDateValGenerator(v));
    }
    return service;
  }

  /**
   * Create a {@link DateValGenerationService} with bad {@link FixedDateValGenerator}s
   *
   * @param value
   * @param values
   * @return
   */
  public static DateValGenerationService dateWithBad(Date value, Date... values) {
    DateValGenerationService service = new DateValGenerationService();
    service.addBadGenerator(new FixedDateValGenerator(value));
    for (Date v : values) {
      service.addBadGenerator(new FixedDateValGenerator(v));
    }
    return service;
  }


}
