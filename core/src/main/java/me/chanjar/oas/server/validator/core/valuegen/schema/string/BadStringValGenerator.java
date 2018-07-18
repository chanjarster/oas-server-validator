package me.chanjar.oas.server.validator.core.valuegen.schema.string;

import io.swagger.v3.oas.models.media.StringSchema;
import me.chanjar.oas.server.validator.core.value.schema.StringVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.BadSchemaValGenerator;

/**
 * Related json schema validation properties: see {@link GoodStringValGenerator}
 */
public interface BadStringValGenerator extends BadSchemaValGenerator<StringSchema, StringVal> {

}
