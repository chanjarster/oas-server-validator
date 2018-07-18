package me.chanjar.oas.server.validator.core.valuegen.schema.integer;

import io.swagger.v3.oas.models.media.IntegerSchema;
import me.chanjar.oas.server.validator.core.value.schema.IntegerVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.GoodSchemaValGenerator;

/**
 * Related json schema validation properties:
 * <ol>
 *   <li>maximum</li>
 *   <li>exclusiveMaximum</li>
 *   <li>minimum</li>
 *   <li>exclusiveMinimum</li>
 *   <li>multipleOf</li>
 * </ol>
 * see: <a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5">json-schema-validation</a>
 */
public interface GoodIntegerValGenerator extends GoodSchemaValGenerator<IntegerSchema, IntegerVal> {

}
