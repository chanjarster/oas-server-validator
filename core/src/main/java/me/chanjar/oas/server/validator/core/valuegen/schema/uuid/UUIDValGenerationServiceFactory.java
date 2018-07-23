package me.chanjar.oas.server.validator.core.valuegen.schema.uuid;

public abstract class UUIDValGenerationServiceFactory {

  private UUIDValGenerationServiceFactory() {
    // singleton
  }

  /**
   * Create a default {@link UUIDValGenerationService}.
   * <p>
   * Good Generators:
   * <ol>
   * <li>{@link GoodUUIDValGenerator}</li>
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
  public static UUIDValGenerationService uuid() {
    UUIDValGenerationService service = new UUIDValGenerationService();
    service.addGoodGenerator(new GoodUUIDValGenerator());
    return service;
  }

  /**
   * Create a {@link UUIDValGenerationService} with good {@link FixedUUIDValGenerator}s
   *
   * @param value
   * @param values
   * @return
   */
  public static UUIDValGenerationService stringWithGood(String value, String... values) {
    UUIDValGenerationService service = new UUIDValGenerationService();
    service.addGoodGenerator(new FixedUUIDValGenerator(value));
    for (String v : values) {
      service.addGoodGenerator(new FixedUUIDValGenerator(v));
    }
    return service;
  }

  /**
   * Create a {@link UUIDValGenerationService} with bad {@link FixedUUIDValGenerator}s
   *
   * @param value
   * @param values
   * @return
   */
  public static UUIDValGenerationService stringWithBad(String value, String... values) {
    UUIDValGenerationService service = new UUIDValGenerationService();
    service.addBadGenerator(new FixedUUIDValGenerator(value));
    for (String v : values) {
      service.addBadGenerator(new FixedUUIDValGenerator(v));
    }
    return service;
  }

}
