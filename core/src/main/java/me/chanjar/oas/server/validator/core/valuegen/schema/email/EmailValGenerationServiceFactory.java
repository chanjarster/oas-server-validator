package me.chanjar.oas.server.validator.core.valuegen.schema.email;

import java.util.Arrays;

public abstract class EmailValGenerationServiceFactory {

  private EmailValGenerationServiceFactory() {
    // singleton
  }

  /**
   * Create a default {@link EmailValGenerationService}.
   * <p>
   * Good Generators:
   * <ol>
   * <li>email@domain.com</li>
   * <li>firstname.lastname@domain.com</li>
   * <li>email@subdomain.domain.com</li>
   * <li>firstname+lastname@domain.com</li>
   * <li>email@123.123.123.123</li>
   * <li>email@[123.123.123.123]</li>
   * <li>"email"@domain.com</li>
   * <li>1234567890@domain.com</li>
   * <li>email@domain-one.com</li>
   * <li>_______@domain.com</li>
   * <li>email@domain.name</li>
   * <li>email@domain.co.jp</li>
   * <li>firstname-lastname@domain.com</li>
   * </ol>
   * </p>
   * <p>
   * Bad Generators:
   * <ol>
   * <li>plainaddress</li>
   * <li>#@%^%#$@#$@#.com</li>
   * <li>@domain.com</li>
   * <li>Joe Smith <email@domain.com></li>
   * <li>email.domain.com</li>
   * <li>email@domain@domain.com</li>
   * <li>.email@domain.com</li>
   * <li>email.@domain.com</li>
   * <li>email..email@domain.com</li>
   * <li>あいうえお@domain.com</li>
   * <li>email@domain.com (Joe Smith)</li>
   * <li>email@domain</li>
   * <li>email@-domain.com</li>
   * <li>email@111.222.333.44444</li>
   * <li>email@domain..com</li>
   * </ol>
   * </p>
   *
   * @return
   */
  public static EmailValGenerationService email() {

    EmailValGenerationService service = new EmailValGenerationService();

    String[] goodEmails = new String[] {
        "email@domain.com",
        "firstname.lastname@domain.com",
        "email@subdomain.domain.com",
        "firstname+lastname@domain.com",
        "email@123.123.123.123",
        "email@[123.123.123.123]",
        "\"email\"@domain.com",
        "1234567890@domain.com",
        "email@domain-one.com",
        "_______@domain.com",
        "email@domain.name",
        "email@domain.co.jp",
        "firstname-lastname@domain.com"
    };
    Arrays.stream(goodEmails).forEach(email -> service.addGoodGenerator(new FixedEmailValGenerator(email)));

    String[] badEmails = new String[] {
        "plainaddress",
        "#@%^%#$@#$@#.com",
        "@domain.com",
        "Joe Smith <email@domain.com>",
        "email.domain.com",
        "email@domain@domain.com",
        ".email@domain.com",
        "email.@domain.com",
        "email..email@domain.com",
        "あいうえお@domain.com",
        "email@domain.com (Joe Smith)",
        "email@domain",
        "email@-domain.com",
        "email@111.222.333.44444",
        "email@domain..com"
    };
    Arrays.stream(badEmails).forEach(email -> service.addBadGenerator(new FixedEmailValGenerator(email)));

    return service;
  }

  /**
   * Create a {@link EmailValGenerationService} with good {@link FixedEmailValGenerator}s
   *
   * @param value
   * @param values
   * @return
   */
  public static EmailValGenerationService eamilWithGood(String value, String... values) {
    EmailValGenerationService service = new EmailValGenerationService();
    service.addGoodGenerator(new FixedEmailValGenerator(value));
    for (String v : values) {
      service.addGoodGenerator(new FixedEmailValGenerator(v));
    }
    return service;
  }

  /**
   * Create a {@link EmailValGenerationService} with bad {@link FixedEmailValGenerator}s
   *
   * @param value
   * @param values
   * @return
   */
  public static EmailValGenerationService eamilWithBad(String value, String... values) {
    EmailValGenerationService service = new EmailValGenerationService();
    service.addBadGenerator(new FixedEmailValGenerator(value));
    for (String v : values) {
      service.addBadGenerator(new FixedEmailValGenerator(v));
    }
    return service;
  }

}
