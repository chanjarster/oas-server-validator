package me.chanjar.oas.server.validator.core.value.schema;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

/**
 * property value of {@link io.swagger.v3.oas.models.media.Schema}<br>
 * doc: <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#schemaObject">Schema Object</a><br>
 * doc: <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#dataTypeFormat">Data Types</a>
 */
public abstract class SchemaVal<T> {

  /**
   * <ol>
   *   <li>{@link BinaryVal}</li>
   *   <li>{@link BooleanVal}</li>
   *   <li>{@link ByteArrayVal}</li>
   *   <li>{@link DateTimeVal}</li>
   *   <li>{@link DateVal}</li>
   *   <li>{@link EmailVal}</li>
   *   <li>{@link IntegerVal}</li>
   *   <li>{@link NumberVal}</li>
   *   <li>{@link PasswordVal}</li>
   *   <li>{@link StringVal}</li>
   *   <li>{@link UUIDVal}</li>
   * </ol>
   * @return
   */
  public static final Class<? extends SchemaVal>[] getPrimitives() {
    return new Class[] {
        BinaryVal.class,
        BooleanVal.class,
        ByteArrayVal.class,
        DateTimeVal.class,
        DateVal.class,
        EmailVal.class,
        IntegerVal.class,
        NumberVal.class,
        PasswordVal.class,
        StringVal.class,
        UUIDVal.class
    };
  }

  private final T value;

  public SchemaVal(T value) {
    this.value = value;
  }

  public T getValue() {
    return value;
  }

  public boolean isNull() {
    return value == null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SchemaVal<?> schemaVal = (SchemaVal<?>) o;
    return Objects.equals(value, schemaVal.value);
  }

  @Override
  public int hashCode() {

    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("value", value)
        .toString();
  }
}
