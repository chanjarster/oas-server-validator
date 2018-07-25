package me.chanjar.oas.server.validator.core.valuegen.schema.email;

import me.chanjar.oas.server.validator.core.value.schema.EmailVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerator;
import me.chanjar.oas.server.validator.core.valuegen.schema.string.StringValGenerationService;

import static me.chanjar.oas.server.validator.core.valuegen.schema.PrimitiveSchemaValGenerationServiceFactoryHelper.addGenerators;

public abstract class EmailValGenerationServiceFactory {

  private EmailValGenerationServiceFactory() {
    // singleton
  }

  /**
   * Create a {@link EmailValGenerationService} with {@link SchemaValGenerator}s
   *
   * @param generator
   * @param generators
   * @return
   */
  public static EmailValGenerationService string(SchemaValGenerator generator,
      SchemaValGenerator... generators) {
    EmailValGenerationService service = new EmailValGenerationService();
    addGenerators(service, generator, generators);
    return service;
  }

  /**
   * Create a default good {@link EmailValGenerationService}.
   * <p>
   * Generators:
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
   *
   * @return
   */
  public static EmailValGenerationService goodEmail() {

    return fixedEmail(
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
    );

  }

  /**
   * Create a default bad {@link EmailValGenerationService}.
   * <p>
   * Generators:
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
  public static EmailValGenerationService badEmail() {

    return fixedEmail(
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
    );

  }

  /**
   * Create a {@link StringValGenerationService} with fixed values
   *
   * @param value
   * @param values
   * @return
   */
  public static EmailValGenerationService fixedEmail(String value, String... values) {
    EmailValGenerationService service = new EmailValGenerationService();
    addGenerators(service, d -> new EmailVal(d), value, values);
    return service;
  }

}
