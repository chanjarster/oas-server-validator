package me.chanjar.oas.server.validator.core.encoder.schema;

import io.swagger.v3.oas.models.parameters.Parameter;
import me.chanjar.oas.server.validator.core.encoder.PercentEncoder;
import me.chanjar.oas.server.validator.core.value.parameter.SerializeOption;
import me.chanjar.oas.server.validator.core.value.schema.ArrayVal;
import me.chanjar.oas.server.validator.core.value.schema.ObjectVal;
import me.chanjar.oas.server.validator.core.value.schema.PrimitiveVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementations should follow <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#style-values">style values</a> doc
 */
public abstract class SchemaValEncoder {

  private final Parameter.StyleEnum style;
  private final Set<Class<? extends SchemaVal>> schemaValClasses;
  protected final PercentEncoder NAME_ENCODER = PercentEncoder.DONT_ALLOW_RESERVED;

  public SchemaValEncoder(Parameter.StyleEnum style, Class<? extends SchemaVal>[] schemaValClasses) {
    this.style = style;
    this.schemaValClasses = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(schemaValClasses)));
  }

  public Parameter.StyleEnum getStyle() {
    return style;
  }

  public final boolean matches(SchemaVal schemaVal) {
    if (schemaVal == null) {
      return false;
    }

    return schemaValClasses.contains(schemaVal.getClass());
  }

  public final String encode(String externalName, SchemaVal schemaVal, SerializeOption serializeOption) {

    if (SchemaVal.isNull(schemaVal)) {

      return encodeNull(externalName, schemaVal, serializeOption);

    } else if (SchemaVal.isIgnored(schemaVal)) {

      return encodeIgnore(externalName, schemaVal, serializeOption);

    } else if (SchemaVal.isPrimitive(schemaVal)) {

      return encodePrimitive(externalName, (PrimitiveVal) schemaVal, serializeOption);

    } else if (SchemaVal.isArray(schemaVal)) {

      return encodeArray(externalName, (ArrayVal) schemaVal, serializeOption);

    } else if (SchemaVal.isObject(schemaVal)) {

      return encodeObject(externalName, (ObjectVal) schemaVal, serializeOption);

    }

    throw new SchemaEncodingException("Unsupported schemaVal type: " + schemaVal.getClass());
  }

  protected final String encodeIgnore(String externalName, SchemaVal schemaVal, SerializeOption serializeOption) {
    return "";
  }

  protected abstract String encodeNull(String externalName, SchemaVal schemaVal, SerializeOption serializeOption);

  protected abstract String encodePrimitive(String externalName, PrimitiveVal primitiveVal,
      SerializeOption serializeOption);

  protected abstract String encodeArray(String externalName, ArrayVal arrayVal,
      SerializeOption serializeOption);

  protected abstract String encodeObject(String externalName, ObjectVal objectVal, SerializeOption serializeOption);

  protected final PercentEncoder getValueEncoder(SerializeOption serializeOption) {
    return serializeOption.isAllowReserved() ?
        PercentEncoder.ALLOW_RESERVED :
        PercentEncoder.DONT_ALLOW_RESERVED;
  }

}
