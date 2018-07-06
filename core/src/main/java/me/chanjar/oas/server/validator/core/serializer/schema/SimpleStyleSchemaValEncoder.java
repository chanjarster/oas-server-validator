package me.chanjar.oas.server.validator.core.serializer.schema;

import io.swagger.v3.oas.models.parameters.Parameter;
import me.chanjar.oas.server.validator.core.value.schema.ArrayVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;

public class SimpleStyleSchemaValEncoder extends SchemaValEncoder {

  public SimpleStyleSchemaValEncoder() {
    super(Parameter.StyleEnum.SIMPLE, new Class[] { ArrayVal.class });
  }

  @Override
  public String encode(boolean explode, String propName, SchemaVal schemaVal) {
    // TODO
    return null;
  }
}
