package me.chanjar.oas.server.validator.core.value.schema;

/**
 * any sequence of octets<br>
 * doc: <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#dataTypeFormat">Data Types</a>
 *
 * @see io.swagger.v3.oas.models.media.BinarySchema
 */
public class BinaryVal extends SchemaVal<String> {
  public BinaryVal(String value) {
    super(value);
  }

}
