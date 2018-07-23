package me.chanjar.oas.server.validator.core.valuegen.schema.password;

public abstract class PasswordValGenerationServiceFactory {

  private PasswordValGenerationServiceFactory() {
    // singleton
  }

  /**
   * Create a default {@link PasswordValGenerationService}.
   * <p>
   * Good Generators:
   * <ol>
   * <li>{@link GoodPasswordValGenerator1}</li>
   * <li>{@link GoodPasswordValGenerator2}</li>
   * <li>{@link GoodPasswordValGenerator3}</li>
   * </ol>
   * </p>
   * <p>
   * Bad Generators:
   * <ol>
   * <li>{@link BadPasswordValGenerator1}</li>
   * <li>{@link BadPasswordValGenerator2}</li>
   * </ol>
   * </p>
   *
   * @return
   */
  public static PasswordValGenerationService password() {
    PasswordValGenerationService service = new PasswordValGenerationService();

    service.addGoodGenerator(new GoodPasswordValGenerator1());
    service.addGoodGenerator(new GoodPasswordValGenerator2());
    service.addGoodGenerator(new GoodPasswordValGenerator3());

    service.addBadGenerator(new BadPasswordValGenerator1());
    service.addBadGenerator(new BadPasswordValGenerator2());

    return service;
  }

  /**
   * Create a {@link PasswordValGenerationService} with good {@link FixedPasswordValGenerator}s
   *
   * @param value
   * @param values
   * @return
   */
  public static PasswordValGenerationService passwordWithGood(String value, String... values) {
    PasswordValGenerationService service = new PasswordValGenerationService();
    service.addGoodGenerator(new FixedPasswordValGenerator(value));
    for (String v : values) {
      service.addGoodGenerator(new FixedPasswordValGenerator(v));
    }
    return service;
  }

  /**
   * Create a {@link PasswordValGenerationService} with bad {@link FixedPasswordValGenerator}s
   *
   * @param value
   * @param values
   * @return
   */
  public static PasswordValGenerationService passwordWithBad(String value, String... values) {
    PasswordValGenerationService service = new PasswordValGenerationService();
    service.addBadGenerator(new FixedPasswordValGenerator(value));
    for (String v : values) {
      service.addBadGenerator(new FixedPasswordValGenerator(v));
    }
    return service;
  }

}
