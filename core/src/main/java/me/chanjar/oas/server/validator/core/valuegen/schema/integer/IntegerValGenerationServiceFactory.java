package me.chanjar.oas.server.validator.core.valuegen.schema.integer;

public abstract class IntegerValGenerationServiceFactory {

  private IntegerValGenerationServiceFactory() {
    // singleton
  }

  /**
   * Create a default {@link IntegerValGenerationService}.
   * <p>
   * Good Generators:
   * <ol>
   * <li>{@link GoodIntegerValGenerator1}</li>
   * <li>{@link GoodIntegerValGenerator2}</li>
   * <li>{@link GoodIntegerValGenerator3}</li>
   * </ol>
   * </p>
   * <p>
   * Bad Generators:
   * <ol>
   * <li>{@link BadIntegerValGenerator1}</li>
   * <li>{@link BadIntegerValGenerator2}</li>
   * </ol>
   * </p>
   *
   * @return
   */
  public static IntegerValGenerationService integer() {
    IntegerValGenerationService service = new IntegerValGenerationService();

    service.addGoodGenerator(new GoodIntegerValGenerator1());
    service.addGoodGenerator(new GoodIntegerValGenerator2());
    service.addGoodGenerator(new GoodIntegerValGenerator3());

    service.addBadGenerator(new BadIntegerValGenerator1());
    service.addBadGenerator(new BadIntegerValGenerator2());

    return service;
  }

  /**
   * Create a {@link IntegerValGenerationService} with good {@link FixedIntegerValGenerator}s
   *
   * @param value
   * @param values
   * @return
   */
  public static IntegerValGenerationService integerWithGood(Integer value, Integer... values) {
    IntegerValGenerationService service = new IntegerValGenerationService();
    service.addGoodGenerator(new FixedIntegerValGenerator(value));
    for (Integer v : values) {
      service.addGoodGenerator(new FixedIntegerValGenerator(v));
    }
    return service;
  }

  /**
   * Create a {@link IntegerValGenerationService} with bad {@link FixedIntegerValGenerator}s
   *
   * @param value
   * @param values
   * @return
   */
  public static IntegerValGenerationService integerWithBad(Integer value, Integer... values) {
    IntegerValGenerationService service = new IntegerValGenerationService();
    service.addBadGenerator(new FixedIntegerValGenerator(value));
    for (Integer v : values) {
      service.addBadGenerator(new FixedIntegerValGenerator(v));
    }
    return service;
  }

}
