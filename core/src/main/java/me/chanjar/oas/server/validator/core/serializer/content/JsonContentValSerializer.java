package me.chanjar.oas.server.validator.core.serializer.content;

import me.chanjar.oas.server.validator.core.value.content.ContentVal;

public class JsonContentValSerializer extends ContentValSerializer {

  public JsonContentValSerializer() {
    super("application/json");
  }

  @Override
  public String serialize(ContentVal contentVal) {
    // TODO
    return null;
  }
}
