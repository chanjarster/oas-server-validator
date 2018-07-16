package me.chanjar.oas.server.validator.core.valuegen.schema;

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
}
