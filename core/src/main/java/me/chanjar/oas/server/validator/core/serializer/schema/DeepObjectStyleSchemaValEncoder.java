package me.chanjar.oas.server.validator.core.serializer.schema;

import io.swagger.v3.oas.models.parameters.Parameter;
import me.chanjar.oas.server.validator.core.value.schema.ObjectVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;

public class DeepObjectStyleSchemaValEncoder extends SchemaValEncoder {

  public DeepObjectStyleSchemaValEncoder() {
    super(Parameter.StyleEnum.DEEPOBJECT, new Class[] { ObjectVal.class });

  }

  @Override
  public String encode(boolean explode, String propName, SchemaVal schemaVal) {
    // TODO
    return null;
  }
}
