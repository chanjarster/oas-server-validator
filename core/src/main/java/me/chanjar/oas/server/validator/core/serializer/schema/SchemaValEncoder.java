package me.chanjar.oas.server.validator.core.serializer.schema;

import io.swagger.v3.oas.models.parameters.Parameter;
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

  public SchemaValEncoder(Parameter.StyleEnum style, Class<? extends SchemaVal>[] schemaValClasses) {
    this.style = style;
    this.schemaValClasses = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(schemaValClasses)));
  }

  public final boolean matches(SchemaVal schemaVal) {
    if (schemaVal == null) {
      return false;
    }
    return schemaValClasses.contains(schemaVal.getClass());
  }

  public abstract String encode(boolean explode, String propName, SchemaVal schemaVal);

}
