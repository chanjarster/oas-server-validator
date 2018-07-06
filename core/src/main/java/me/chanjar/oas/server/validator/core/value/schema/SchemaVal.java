package me.chanjar.oas.server.validator.core.value.schema;

/**
 * property value of {@link io.swagger.v3.oas.models.media.Schema}<br>
 * doc: <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#schemaObject">Schema Object</a><br>
 * doc: <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#dataTypeFormat">Data Types</a>
 */
public abstract class SchemaVal<T> {

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

}
