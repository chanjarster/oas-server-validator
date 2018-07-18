package me.chanjar.oas.server.validator.core.value.schema;

import java.util.Date;

/**
 * As defined by <code>date-time</code> - <a href="https://xml2rfc.ietf.org/public/rfc/html/rfc3339.html#anchor14">RFC3339</a><br>
 * doc: <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#dataTypeFormat">Date Types</a>
 *
 * @see io.swagger.v3.oas.models.media.DateTimeSchema
 */
public class DateTimeVal extends SchemaVal<Date> {
  public DateTimeVal(Date value) {
    super(value);
  }
}
