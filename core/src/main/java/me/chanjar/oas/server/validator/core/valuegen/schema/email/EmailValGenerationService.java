package me.chanjar.oas.server.validator.core.valuegen.schema.email;

import io.swagger.v3.oas.models.media.EmailSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.EmailVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.GeneratorDependingGenerationService;

/**
 * {@link EmailVal} generator for {@link EmailSchema}<br>
 * doc: <a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-7.3.2">JSON Schema Validation</a>
 * <p>
 * test case reference: <a href="https://blogs.msdn.microsoft.com/testing123/2009/02/06/email-address-test-cases/">Email address test cases</a>
 * </p>
 */
public class EmailValGenerationService extends GeneratorDependingGenerationService<EmailSchema> {

  @Override
  public boolean supports(Schema schema) {
    return EmailSchemaSupport.supports(schema);
  }

}
