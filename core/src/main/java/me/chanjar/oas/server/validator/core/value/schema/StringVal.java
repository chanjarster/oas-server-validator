package me.chanjar.oas.server.validator.core.value.schema;

/**
 * doc: <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#dataTypeFormat">Data Types</a>
 */
public class StringVal extends PrimitiveVal<String> {

  public StringVal(String value) {
    super(value);
  }

  @Override
  public String getValueString() {
    return getValue();
  }

}
