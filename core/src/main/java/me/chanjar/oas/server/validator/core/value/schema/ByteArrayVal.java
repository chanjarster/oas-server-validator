package me.chanjar.oas.server.validator.core.value.schema;

import java.util.Arrays;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ByteArrayVal that = (ByteArrayVal) o;
    return Arrays.equals(getValue(), that.getValue());
  }

  @Override
  public int hashCode() {

    int result = super.hashCode();
    result = 31 * result + Arrays.hashCode(getValue());
    return result;
  }
}
