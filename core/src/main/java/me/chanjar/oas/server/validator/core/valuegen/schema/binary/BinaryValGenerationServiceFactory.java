package me.chanjar.oas.server.validator.core.valuegen.schema.binary;

public abstract class BinaryValGenerationServiceFactory {

  private BinaryValGenerationServiceFactory() {
    // singleton
  }

  /**
   * Create a default {@link BinaryValGenerationService}.
   * <p>
   * Good Generators:
   * <ol>
   * <li>{@link GoodBinaryValGenerator}</li>
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
  public static BinaryValGenerationService binary() {
    BinaryValGenerationService service = new BinaryValGenerationService();
    service.addGoodGenerator(new GoodBinaryValGenerator());
    return service;
  }

  /**
   * Create a {@link BinaryValGenerationService} with good {@link FixedBinaryValGenerator}s
   *
   * @param value
   * @param values
   * @return
   */
  public static BinaryValGenerationService binaryWithGood(String value, String... values) {
    BinaryValGenerationService service = new BinaryValGenerationService();
    service.addGoodGenerator(new FixedBinaryValGenerator(value));
    for (String v : values) {
      service.addGoodGenerator(new FixedBinaryValGenerator(v));
    }
    return service;
  }

  /**
   * Create a {@link BinaryValGenerationService} with bad {@link FixedBinaryValGenerator}s
   *
   * @param value
   * @param values
   * @return
   */
  public static BinaryValGenerationService binaryWithBad(String value, String... values) {
    BinaryValGenerationService service = new BinaryValGenerationService();
    service.addBadGenerator(new FixedBinaryValGenerator(value));
    for (String v : values) {
      service.addBadGenerator(new FixedBinaryValGenerator(v));
    }
    return service;
  }

}
