package me.chanjar.oas.server.validator.core.serializer.content;

import me.chanjar.oas.server.validator.core.value.content.ContentVal;

public class MultipartContentValSerializer extends ContentValSerializer {

  public MultipartContentValSerializer() {
    super("multipart/form-data");
  }

  @Override
  public String serialize(ContentVal contentVal) {
    // TODO
    return null;
  }
}
