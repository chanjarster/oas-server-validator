package me.chanjar.oas.server.validator.core.valuegen.schema.string;

import io.swagger.v3.oas.models.media.StringSchema;
import me.chanjar.oas.server.validator.core.value.schema.StringVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.GoodSchemaValGenerator;

/**
 * Related json schema validation properties:
 * <ol>
 *   <li>pattern(not supported)</li>
 *   <li>maxLength</li>
 *   <li>minLength</li>
 * </ol>
 * see: <a href="https://tools.ietf.org/html/draft-wright-json-schema-validation-00#section-5">json-schema-validation</a>
 */
public interface GoodStringValGenerator extends GoodSchemaValGenerator<StringSchema, StringVal> {

}
