package me.chanjar.oas.server.validator.core.valuegen.schema;

import io.swagger.v3.oas.models.media.EmailSchema;
import me.chanjar.oas.server.validator.core.value.schema.EmailVal;
import me.chanjar.oas.server.validator.core.value.schema.IntegerVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.email.BadEmailValGenerator;
import me.chanjar.oas.server.validator.core.valuegen.schema.email.EmailSchemaSupport;
import me.chanjar.oas.server.validator.core.valuegen.schema.email.GoodEmailValGenerator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * {@link EmailVal} generator for {@link EmailSchema}<br>
 * doc: <a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-7.3.2">JSON Schema Validation</a>
 * <p>
 * test case reference: <a href="https://blogs.msdn.microsoft.com/testing123/2009/02/06/email-address-test-cases/">Email address test cases</a>
 * </p>
 */
public class EmailValGenerator extends SchemaValGeneratorTemplate<EmailSchema, EmailVal> {

  private final List<GoodEmailValGenerator> goodGenerators;

  private final List<BadEmailValGenerator> badGenerators;

  public EmailValGenerator() {

    goodGenerators = Collections.unmodifiableList(
        batchCreateGood(
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
        ));

    badGenerators = Collections.unmodifiableList(
        batchCreateBad(
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
        )
    );

  }

  @Override
  public boolean supports(EmailSchema schema) {
    return EmailSchemaSupport.supports(schema);
  }

  @Override
  protected Class<EmailSchema> getSchemaValClass() {
    return EmailSchema.class;
  }

  @Override
  protected EmailVal createNullSchemaVal() {
    return new EmailVal(null);
  }

  @Override
  protected SchemaVal createDifferentTypeSchemaVal() {
    return new IntegerVal(0);
  }

  @Override
  protected List<? extends BadSchemaValGenerator<EmailSchema, EmailVal>> getBadGenerators() {
    return badGenerators;
  }

  @Override
  protected List<? extends GoodSchemaValGenerator<EmailSchema, EmailVal>> getGoodGenerators() {
    return goodGenerators;
  }

  private List<GoodEmailValGenerator> batchCreateGood(String... emails) {
    return Arrays.stream(emails).map(email -> new GoodEmailValGenerator(email)).collect(toList());
  }

  private List<BadEmailValGenerator> batchCreateBad(String... emails) {
    return Arrays.stream(emails).map(email -> new BadEmailValGenerator(email)).collect(toList());
  }

}
