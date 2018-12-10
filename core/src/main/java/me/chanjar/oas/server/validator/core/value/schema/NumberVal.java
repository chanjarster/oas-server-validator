package me.chanjar.oas.server.validator.core.value.schema;

import java.math.BigDecimal;

/**
 * doc: <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#dataTypeFormat">Data Types</a>
 *
 * @see io.swagger.v3.oas.models.media.NumberSchema
 */
public class NumberVal extends PrimitiveVal<BigDecimal> {
  public NumberVal(BigDecimal value) {
    super(value);
  }

  @Override
  public String getValueString() {
    return getValue().toPlainString();
  }
}
