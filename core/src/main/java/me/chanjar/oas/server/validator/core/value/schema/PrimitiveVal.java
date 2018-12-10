package me.chanjar.oas.server.validator.core.value.schema;

public abstract class PrimitiveVal<T> extends SchemaVal<T> {

  public PrimitiveVal(T value) {
    super(value);
  }

  /**
   * Get {@link #value}'s string format
   *
   * @return
   */
  public abstract String getValueString();

}
