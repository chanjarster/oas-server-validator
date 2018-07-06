package me.chanjar.oas.server.validator.core.value.schema;

import java.util.Map;

/**
 * doc: <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#dataTypeFormat">Date Types</a>
 *
 * @see io.swagger.v3.oas.models.media.ObjectSchema
 */
public class ObjectVal extends SchemaVal<Map<String, SchemaVal>> {

  /**
   * @param value [property name] -> {@link SchemaVal}
   */
  public ObjectVal(Map<String, SchemaVal> value) {
    super(value);
  }

}
