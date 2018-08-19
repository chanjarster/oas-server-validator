package me.chanjar.oas.server.validator.core.value.parameter;

import io.swagger.v3.oas.models.parameters.Parameter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SerializeOption that = (SerializeOption) o;
    return explode == that.explode &&
        allowReserved == that.allowReserved &&
        style == that.style;
  }

  @Override
  public int hashCode() {

    return Objects.hash(style, explode, allowReserved);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("style", style)
        .append("explode", explode)
        .append("allowReserved", allowReserved)
        .toString();
  }
}
