package me.chanjar.oas.server.validator.core.valuegen.schema;

import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;

public interface BadSchemaValGenerator<S extends Schema, V extends SchemaVal> {

  boolean supports(Schema schema);

  V generate(S schema);

}
