package me.chanjar.oas.server.validator.core.value.schema;

/**
 * A hint to UIs to obscure input<br>
 * doc: <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#dataTypeFormat">Date Types</a>
 *
 * @see io.swagger.v3.oas.models.media.PasswordSchema
 */
public class UUIDVal extends SchemaVal<String> {
  public UUIDVal(String value) {
    super(value);
  }
}
