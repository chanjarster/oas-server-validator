package me.chanjar.oas.server.validator.core.value.parameter;

import io.swagger.v3.oas.models.parameters.Parameter;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

/**
 * see spec: <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#parameter-object">Parameter Object</a>
 */
public class ParameterVal {

  private final String in;

  private final String name;

  private final SchemaVal schemaVal;

  private final SerializeOption serializeOption;

  public ParameterVal(Parameter parameter, SchemaVal schemaVal) {
    this.in = parameter.getIn();
    this.name = parameter.getName();
    this.schemaVal = schemaVal;
    // according to https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#parameter-object
    // allowReserved default value is false
    this.serializeOption = new SerializeOption(parameter.getStyle(),
        Boolean.TRUE.equals(parameter.getExplode()),
        Boolean.TRUE.equals(parameter.getAllowReserved()));
  }

  public ParameterVal(String in, String name, SchemaVal schemaVal,
      SerializeOption serializeOption) {
    this.in = in;
    this.name = name;
    this.schemaVal = schemaVal;
    this.serializeOption = serializeOption;
  }

  /**
   * {@link Parameter#in}
   *
   * @return
   */
  public String getIn() {
    return in;
  }

  /**
   * {@link Parameter#name}
   *
   * @return
   */
  public String getName() {
    return name;
  }

  /**
   * @return {@link SchemaVal}
   */
  public SchemaVal getSchemaVal() {
    return schemaVal;
  }

  /**
   * @return {@link SerializeOption}
   */
  public SerializeOption getSerializeOption() {
    return serializeOption;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ParameterVal that = (ParameterVal) o;
    return Objects.equals(in, that.in) &&
        Objects.equals(name, that.name) &&
        Objects.equals(schemaVal, that.schemaVal) &&
        Objects.equals(serializeOption, that.serializeOption);
  }

  @Override
  public int hashCode() {

    return Objects.hash(in, name, schemaVal, serializeOption);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("in", in)
        .append("name", name)
        .append("schemaVal", schemaVal)
        .append("serializeOption", serializeOption)
        .toString();
  }

}
