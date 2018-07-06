package me.chanjar.oas.server.validator.core.serializer.schema;

import io.swagger.v3.oas.models.parameters.Parameter;
import me.chanjar.oas.server.validator.core.value.schema.ArrayVal;
import me.chanjar.oas.server.validator.core.value.schema.ObjectVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import org.apache.commons.lang3.ArrayUtils;

public class LabelStyleSchemaValEncoder extends SchemaValEncoder {

  public LabelStyleSchemaValEncoder() {
    super(Parameter.StyleEnum.LABEL,
        ArrayUtils.addAll(
            new Class[] { ArrayVal.class, ObjectVal.class },
            SchemaVal.getPrimitives()
        )
    );
  }

  @Override
  public String encode(boolean explode, String propName, SchemaVal schemaVal) {
    // TODO
    return null;
  }
}
