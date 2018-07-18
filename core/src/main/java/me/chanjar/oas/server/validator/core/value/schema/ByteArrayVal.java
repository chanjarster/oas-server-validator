package me.chanjar.oas.server.validator.core.value.schema;

/**
 * base64 encoded characters<br>
 * doc: <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#dataTypeFormat">Data Types</a>
 *
 * @see io.swagger.v3.oas.models.media.ByteArraySchema
 */
public class ByteArrayVal extends SchemaVal<Byte[]> {
  public ByteArrayVal(Byte[] value) {
    super(value);
  }
}
