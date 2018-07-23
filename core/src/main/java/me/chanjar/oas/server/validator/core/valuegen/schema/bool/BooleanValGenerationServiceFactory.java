package me.chanjar.oas.server.validator.core.valuegen.schema.bool;

public abstract class BooleanValGenerationServiceFactory {

  private BooleanValGenerationServiceFactory() {
    // singleton
  }

  /**
   * Create a default {@link BooleanValGenerationService}.
   * <p>
   * Good Generators:
   * <ol>
   * <li>{@link GoodBooleanValGenerator}</li>
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
  public static BooleanValGenerationService bool() {
    BooleanValGenerationService service = new BooleanValGenerationService();
    service.addGoodGenerator(new GoodBooleanValGenerator());
    return service;
  }

  /**
   * Create a {@link BooleanValGenerationService} with good {@link FixedBooleanValGenerator}s
   *
   * @param value
   * @param values
   * @return
   */
  public static BooleanValGenerationService boolWithGood(Boolean value, Boolean... values) {
    BooleanValGenerationService service = new BooleanValGenerationService();
    service.addGoodGenerator(new FixedBooleanValGenerator(value));
    for (Boolean v : values) {
      service.addGoodGenerator(new FixedBooleanValGenerator(v));
    }
    return service;
  }

  /**
   * Create a {@link BooleanValGenerationService} with bad {@link FixedBooleanValGenerator}s
   *
   * @param value
   * @param values
   * @return
   */
  public static BooleanValGenerationService boolWithBad(Boolean value, Boolean... values) {
    BooleanValGenerationService service = new BooleanValGenerationService();
    service.addBadGenerator(new FixedBooleanValGenerator(value));
    for (Boolean v : values) {
      service.addBadGenerator(new FixedBooleanValGenerator(v));
    }
    return service;
  }

}
