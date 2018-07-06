package me.chanjar.oas.server.validator.core.value.content;

import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;

public class ContentVal {

  private SchemaVal schemaVal;

  public ContentVal(SchemaVal schemaVal) {
    this.schemaVal = schemaVal;
  }

  public SchemaVal getSchemaVal() {
    return schemaVal;
  }

}
