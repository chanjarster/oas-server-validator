package me.chanjar.oas.server.validator.core.encoder.schema;

import io.swagger.v3.oas.models.parameters.Parameter;
import me.chanjar.oas.server.validator.core.encoder.PercentEncoder;
import me.chanjar.oas.server.validator.core.value.parameter.SerializeOption;
import me.chanjar.oas.server.validator.core.value.schema.ArrayVal;
import me.chanjar.oas.server.validator.core.value.schema.ObjectVal;
import me.chanjar.oas.server.validator.core.value.schema.PrimitiveVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;
import java.util.StringJoiner;

/**
 * see: https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#style-examples
 */
public class FormStyleSchemaValEncoder extends SchemaValEncoder {

  private static final Logger LOGGER = LoggerFactory.getLogger(FormStyleSchemaValEncoder.class);

  public FormStyleSchemaValEncoder() {
    super(Parameter.StyleEnum.FORM,
        ArrayUtils.addAll(
            new Class[] { ArrayVal.class, ObjectVal.class },
            SchemaVal.getPrimitives()
        )
    );
  }

  @Override
  protected String encodeNull(String externalName, SchemaVal schemaVal, SerializeOption serializeOption) {
    return NAME_ENCODER.encode(externalName) + "=";
  }

  @Override
  protected String encodePrimitive(String externalName, PrimitiveVal primitiveVal,
      SerializeOption serializeOption) {

    PercentEncoder valueEncoder = serializeOption.isAllowReserved() ?
        PercentEncoder.ALLOW_RESERVED :
        PercentEncoder.DONT_ALLOW_RESERVED;

    return NAME_ENCODER.encode(externalName) + "=" + valueEncoder.encode(primitiveVal.getValueString());
  }

  @Override
  protected String encodeArray(String externalName, ArrayVal arrayVal, SerializeOption serializeOption) {

    PercentEncoder valueEncoder = getValueEncoder(serializeOption);

    if (serializeOption.isExplode()) {

      StringJoiner stringJoiner = new StringJoiner("&");

      Arrays.stream(arrayVal.getValue())
          .filter(new PrimitivePredicate(LOGGER))
          .map(e -> (PrimitiveVal) e)
          .map(e ->
              NAME_ENCODER.encode(externalName)
                  + "="
                  + valueEncoder.encode(e.getValueString()))
          .forEach(string -> stringJoiner.add(string));

      return stringJoiner.toString();

    }

    StringBuilder sb = new StringBuilder();

    StringJoiner stringJoiner = new StringJoiner(",");

    Arrays.stream(arrayVal.getValue())
        .filter(new PrimitivePredicate(LOGGER))
        .map(e -> (PrimitiveVal) e)
        .map(e -> valueEncoder.encode(e.getValueString()))
        .forEach(string -> stringJoiner.add(string));

    sb.append(NAME_ENCODER.encode(externalName))
        .append('=')
        .append(stringJoiner.toString());

    return sb.toString();
  }

  @Override
  protected String encodeObject(String externalName, ObjectVal objectVal, SerializeOption serializeOption) {

    PrimitiveEntryPredicate primitiveEntryPredicate = new PrimitiveEntryPredicate(LOGGER);

    PercentEncoder valueEncoder = getValueEncoder(serializeOption);


    Map<String, SchemaVal> props = objectVal.getValue();
    if (serializeOption.isExplode()) {

      StringJoiner stringJoiner = new StringJoiner("&");

      props.entrySet().stream()
          .filter(primitiveEntryPredicate)
          .map(entry ->
              NAME_ENCODER.encode(entry.getKey())
                  + "="
                  + valueEncoder.encode(((PrimitiveVal) entry.getValue()).getValueString())
          )
          .forEach(string -> stringJoiner.add(string));

      return stringJoiner.toString();
    }


    StringJoiner stringJoiner = new StringJoiner(",");

    props.entrySet().stream()
          .filter(primitiveEntryPredicate)
          .map(entry ->
              NAME_ENCODER.encode(entry.getKey())
                  + ","
                  + valueEncoder.encode(((PrimitiveVal) entry.getValue()).getValueString())
          )
          .forEach(string -> stringJoiner.add(string));



    return new StringBuilder(NAME_ENCODER.encode(externalName))
        .append('=')
        .append(stringJoiner.toString()).toString();

  }
}
