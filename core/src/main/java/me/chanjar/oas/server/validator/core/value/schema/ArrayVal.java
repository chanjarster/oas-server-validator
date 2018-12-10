package me.chanjar.oas.server.validator.core.value.schema;

/**
 * doc: <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#dataTypeFormat">Date Types</a>
 *
 * @see io.swagger.v3.oas.models.media.ArraySchema
 */
public class ArrayVal extends SchemaVal<SchemaVal[]> {

  public ArrayVal(SchemaVal[] value) {
    super(value);
  }

}
