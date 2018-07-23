package me.chanjar.oas.server.validator.core.valuegen.schema.number;

import java.math.BigDecimal;

public abstract class NumberValGenerationServiceFactory {

  private NumberValGenerationServiceFactory() {
    // singleton
  }

  /**
   * Create a default {@link NumberValGenerationService}.
   * <p>
   * Good Generators:
   * <ol>
   * <li>{@link GoodNumberValGenerator1}</li>
   * <li>{@link GoodNumberValGenerator2}</li>
   * <li>{@link GoodNumberValGenerator3}</li>
   * </ol>
   * </p>
   * <p>
   * Bad Generators:
   * <ol>
   * <li>{@link BadNumberValGenerator1}</li>
   * <li>{@link BadNumberValGenerator2}</li>
   * </ol>
   * </p>
   *
   * @return
   */
  public static NumberValGenerationService number() {
    NumberValGenerationService service = new NumberValGenerationService();

    service.addGoodGenerator(new GoodNumberValGenerator1());
    service.addGoodGenerator(new GoodNumberValGenerator2());
    service.addGoodGenerator(new GoodNumberValGenerator3());

    service.addBadGenerator(new BadNumberValGenerator1());
    service.addBadGenerator(new BadNumberValGenerator2());

    return service;
  }

  /**
   * Create a {@link NumberValGenerationService} with good {@link FixedNumberValGenerator}s
   *
   * @param value
   * @param values
   * @return
   */
  public static NumberValGenerationService numberWithGood(BigDecimal value, BigDecimal... values) {
    NumberValGenerationService service = new NumberValGenerationService();
    service.addGoodGenerator(new FixedNumberValGenerator(value));
    for (BigDecimal v : values) {
      service.addGoodGenerator(new FixedNumberValGenerator(v));
    }
    return service;
  }

  /**
   * Create a {@link NumberValGenerationService} with bad {@link FixedNumberValGenerator}s
   *
   * @param value
   * @param values
   * @return
   */
  public static NumberValGenerationService numberWithBad(BigDecimal value, BigDecimal... values) {
    NumberValGenerationService service = new NumberValGenerationService();
    service.addBadGenerator(new FixedNumberValGenerator(value));
    for (BigDecimal v : values) {
      service.addBadGenerator(new FixedNumberValGenerator(v));
    }
    return service;
  }

}
