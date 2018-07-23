package me.chanjar.oas.server.validator.core.valuegen.schema.string;

public abstract class StringValGenerationServiceFactory {

  private StringValGenerationServiceFactory() {
    // singleton
  }

  /**
   * Create a default {@link StringValGenerationService}.
   * <p>
   * Good Generators:
   * <ol>
   * <li>{@link GoodStringValGenerator1}</li>
   * <li>{@link GoodStringValGenerator2}</li>
   * <li>{@link GoodStringValGenerator3}</li>
   * </ol>
   * </p>
   * <p>
   * Bad Generators:
   * <ol>
   * <li>{@link BadStringValGenerator1}</li>
   * <li>{@link BadStringValGenerator2}</li>
   * </ol>
   * </p>
   *
   * @return
   */
  public static StringValGenerationService string() {
    StringValGenerationService service = new StringValGenerationService();

    service.addGoodGenerator(new GoodStringValGenerator1());
    service.addGoodGenerator(new GoodStringValGenerator2());
    service.addGoodGenerator(new GoodStringValGenerator3());

    service.addBadGenerator(new BadStringValGenerator1());
    service.addBadGenerator(new BadStringValGenerator2());

    return service;
  }

  /**
   * Create a {@link StringValGenerationService} with good {@link FixedStringValGenerator}s
   *
   * @param value
   * @param values
   * @return
   */
  public static StringValGenerationService stringWithGood(String value, String... values) {
    StringValGenerationService service = new StringValGenerationService();
    service.addGoodGenerator(new FixedStringValGenerator(value));
    for (String v : values) {
      service.addGoodGenerator(new FixedStringValGenerator(v));
    }
    return service;
  }

  /**
   * Create a {@link StringValGenerationService} with bad {@link FixedStringValGenerator}s
   *
   * @param value
   * @param values
   * @return
   */
  public static StringValGenerationService stringWithBad(String value, String... values) {
    StringValGenerationService service = new StringValGenerationService();
    service.addBadGenerator(new FixedStringValGenerator(value));
    for (String v : values) {
      service.addBadGenerator(new FixedStringValGenerator(v));
    }
    return service;
  }

}
