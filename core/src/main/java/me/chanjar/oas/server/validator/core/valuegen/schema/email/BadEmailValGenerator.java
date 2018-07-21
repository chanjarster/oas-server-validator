package me.chanjar.oas.server.validator.core.valuegen.schema.email;

import io.swagger.v3.oas.models.media.EmailSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.EmailVal;

public class BadEmailValGenerator implements EmailValGenerator {

  private final String email;

  public BadEmailValGenerator(String email) {
    this.email = email;
  }

  @Override
  public boolean supports(Schema schema) {
    return EmailSchemaSupport.supports(schema);
  }

  @Override
  public EmailVal generate(EmailSchema schema) {
    return new EmailVal(email);
  }
}
