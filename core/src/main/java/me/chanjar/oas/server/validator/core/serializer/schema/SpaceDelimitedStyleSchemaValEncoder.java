package me.chanjar.oas.server.validator.core.serializer.schema;

import io.swagger.v3.oas.models.parameters.Parameter;
import me.chanjar.oas.server.validator.core.value.schema.ArrayVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;

public class SpaceDelimitedStyleSchemaValEncoder extends SchemaValEncoder {

  public SpaceDelimitedStyleSchemaValEncoder() {
    super(Parameter.StyleEnum.SPACEDELIMITED, new Class[] { ArrayVal.class });

  }

  @Override
  public String encode(boolean explode, String propName, SchemaVal schemaVal) {
    // TODO
    return null;
  }
}
