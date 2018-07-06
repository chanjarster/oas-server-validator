package me.chanjar.oas.server.validator.core.serializer.content;

import me.chanjar.oas.server.validator.core.value.content.ContentVal;

public class FormContentValSerializer extends ContentValSerializer {

  // TODO encoding settings

  public FormContentValSerializer() {
    super("application/x-www-form-urlencoded");
  }

  @Override
  public String serialize(ContentVal contentVal) {
    // TODO
    // Do encoding based on encoding settings
    return null;
  }
}
