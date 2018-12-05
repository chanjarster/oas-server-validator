package me.chanjar.oas.server.validator.core.value.mediatype;

import io.swagger.v3.oas.models.media.Encoding;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * see spec: <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#mediaTypeObject">MediaType Object</a>
 */
public class MediaTypeVal {

  private final SchemaVal schemaVal;

  private final Map<String, Encoding> encoding;

  public MediaTypeVal(SchemaVal schemaVal, Map<String, Encoding> encoding) {
    this.schemaVal = schemaVal;
    this.encoding = Collections.unmodifiableMap(encoding);
  }

  public SchemaVal getSchemaVal() {
    return schemaVal;
  }

  public Map<String, Encoding> getEncoding() {
    return encoding;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MediaTypeVal that = (MediaTypeVal) o;
    return Objects.equals(schemaVal, that.schemaVal) &&
        Objects.equals(encoding, that.encoding);
  }

  @Override
  public int hashCode() {
    return Objects.hash(schemaVal, encoding);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("schemaVal", schemaVal)
        .append("encoding", encoding)
        .toString();
  }
}
