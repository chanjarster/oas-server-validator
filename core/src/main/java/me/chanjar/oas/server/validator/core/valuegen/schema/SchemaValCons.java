package me.chanjar.oas.server.validator.core.valuegen.schema;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

/**
 * Additional constraints on <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#schemaObject">Schema Object</a>.
 */
public class SchemaValCons {

  /**
   * Appears both in <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#parameterObject">Parameter Object</a>
   * and <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#schemaObject">Schema Object</a>.
   */
  private final boolean required;

  /**
   * Appears as <code>allowEmptyValue</code> in <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#parameterObject">Parameter Object</a>
   * and <code>nullable</code> in <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#schemaObject">Schema Object</a>.
   */
  private final boolean nullable;

  public SchemaValCons(boolean required, boolean nullable) {
    this.required = required;
    this.nullable = nullable;
  }

  public boolean isRequired() {
    return required;
  }

  public boolean isNullable() {
    return nullable;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SchemaValCons that = (SchemaValCons) o;
    return required == that.required &&
        nullable == that.nullable;
  }

  @Override
  public int hashCode() {

    return Objects.hash(required, nullable);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("required", required)
        .append("nullable", nullable)
        .toString();
  }
}
