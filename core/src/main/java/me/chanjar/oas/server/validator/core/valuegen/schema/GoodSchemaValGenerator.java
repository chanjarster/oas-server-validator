package me.chanjar.oas.server.validator.core.valuegen.schema;

import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;

public interface GoodSchemaValGenerator<S extends Schema, V extends SchemaVal> {

  boolean supports(S schema);

  V generate(S schema);

}
