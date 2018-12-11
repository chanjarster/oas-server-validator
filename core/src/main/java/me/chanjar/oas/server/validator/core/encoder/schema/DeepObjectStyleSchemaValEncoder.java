package me.chanjar.oas.server.validator.core.encoder.schema;

import io.swagger.v3.oas.models.parameters.Parameter;
import me.chanjar.oas.server.validator.core.encoder.PercentEncoder;
import me.chanjar.oas.server.validator.core.value.parameter.SerializeOption;
import me.chanjar.oas.server.validator.core.value.schema.ArrayVal;
import me.chanjar.oas.server.validator.core.value.schema.ObjectVal;
import me.chanjar.oas.server.validator.core.value.schema.PrimitiveVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.StringJoiner;

/**
 * see: https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#style-examples
 */
public class DeepObjectStyleSchemaValEncoder extends SchemaValEncoder {

  private static final Logger LOGGER = LoggerFactory.getLogger(DeepObjectStyleSchemaValEncoder.class);

  public DeepObjectStyleSchemaValEncoder() {
    super(Parameter.StyleEnum.DEEPOBJECT, new Class[] { ObjectVal.class });

  }

  @Override
  protected String encodeNull(String externalName, SchemaVal schemaVal, SerializeOption serializeOption) {
    return "";
  }

  @Override
  protected String encodePrimitive(String externalName, PrimitiveVal primitiveVal,
      SerializeOption serializeOption) {
    return "";
  }

  @Override
  protected String encodeArray(String externalName, ArrayVal arrayVal, SerializeOption serializeOption) {
    return "";
  }

  @Override
  protected String encodeObject(String externalName, ObjectVal objectVal, SerializeOption serializeOption) {

    String encodedExternalName = NAME_ENCODER.encode(externalName);

    PrimitiveEntryPredicate primitiveEntryPredicate = new PrimitiveEntryPredicate(LOGGER);

    PercentEncoder valueEncoder = getValueEncoder(serializeOption);

    StringJoiner stringJoiner = new StringJoiner("&");

    Map<String, SchemaVal> props = objectVal.getValue();

    props.entrySet().stream()
        .filter(primitiveEntryPredicate)
        .map(entry ->
            encodedExternalName
                + "["
                + NAME_ENCODER.encode(entry.getKey())
                + "]"
                + "="
                + valueEncoder.encode(((PrimitiveVal) entry.getValue()).getValueString())
        )
        .forEach(string -> stringJoiner.add(string));

    return stringJoiner.toString();

  }
}
