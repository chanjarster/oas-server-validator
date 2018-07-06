package me.chanjar.oas.server.validator.core.value.schema;

/**
 * doc: <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#dataTypeFormat">Data Types</a>
 *
 * @see io.swagger.v3.oas.models.media.IntegerSchema
 */
public class IntegerVal extends SchemaVal<Integer> {
  public IntegerVal(Integer value) {
    super(value);
  }
}
