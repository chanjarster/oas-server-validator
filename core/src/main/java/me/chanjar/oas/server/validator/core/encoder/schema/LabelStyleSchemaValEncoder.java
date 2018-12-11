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
public class LabelStyleSchemaValEncoder extends SchemaValEncoder {

  private static final Logger LOGGER = LoggerFactory.getLogger(LabelStyleSchemaValEncoder.class);

  public LabelStyleSchemaValEncoder() {
    super(Parameter.StyleEnum.LABEL,
        ArrayUtils.addAll(
            new Class[] { ArrayVal.class, ObjectVal.class },
            SchemaVal.getPrimitives()
        )
    );
  }

  @Override
  protected String encodeNull(String externalName, SchemaVal schemaVal, SerializeOption serializeOption) {
    return ".";
  }

  @Override
  protected String encodePrimitive(String externalName, PrimitiveVal primitiveVal,
      SerializeOption serializeOption) {
    PercentEncoder valueEncoder = getValueEncoder(serializeOption);
    return "." + valueEncoder.encode(String.valueOf(primitiveVal.getValueString()));
  }

  @Override
  protected String encodeArray(String externalName, ArrayVal arrayVal, SerializeOption serializeOption) {

    StringJoiner stringJoiner = new StringJoiner(".", ".", "");

    PercentEncoder valueEncoder = getValueEncoder(serializeOption);

    Arrays.stream(arrayVal.getValue())
        .filter(new PrimitivePredicate(LOGGER))
        .map(e -> (PrimitiveVal) e)
        .map(e -> valueEncoder.encode(e.getValueString()))
        .forEach(string -> stringJoiner.add(string));

    return stringJoiner.toString();

  }

  @Override
  protected String encodeObject(String externalName, ObjectVal objectVal, SerializeOption serializeOption) {

    PrimitiveEntryPredicate primitiveEntryPredicate = new PrimitiveEntryPredicate(LOGGER);

    PercentEncoder valueEncoder = getValueEncoder(serializeOption);

    StringJoiner stringJoiner = new StringJoiner(".", ".", "");

    Map<String, SchemaVal> props = objectVal.getValue();
    if (serializeOption.isExplode()) {

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

    props.entrySet().stream()
        .filter(primitiveEntryPredicate)
        .map(entry ->
            NAME_ENCODER.encode(entry.getKey())
                + "."
                + valueEncoder.encode(((PrimitiveVal) entry.getValue()).getValueString())
        )
        .forEach(string -> stringJoiner.add(string));

    return stringJoiner.toString();

  }
}
