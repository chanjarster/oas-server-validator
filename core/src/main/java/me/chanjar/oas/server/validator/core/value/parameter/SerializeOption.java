package me.chanjar.oas.server.validator.core.value.parameter;

import io.swagger.v3.oas.models.parameters.Parameter;

/**
 * see spec: <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#parameter-object">Parameter Object</a>
 */
public class SerializeOption {

  private final Parameter.StyleEnum style;

  private final boolean explode;

  private final boolean allowReserved;

  public SerializeOption(Parameter.StyleEnum style, boolean explode, boolean allowReserved) {
    this.style = style;
    this.explode = explode;
    this.allowReserved = allowReserved;
  }

  /**
   * {@link Parameter#style}
   */
  public Parameter.StyleEnum getStyle() {
    return style;
  }

  /**
   * {@link Parameter#explode}, default is true
   */
  public boolean isExplode() {
    return explode;
  }

  /**
   * {@link Parameter#allowReserved}, default is false
   */
  public boolean isAllowReserved() {
    return allowReserved;
  }

}
