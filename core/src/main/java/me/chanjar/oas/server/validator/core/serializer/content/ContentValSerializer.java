package me.chanjar.oas.server.validator.core.serializer.content;

import me.chanjar.oas.server.validator.core.value.content.ContentVal;
import org.apache.commons.lang3.StringUtils;

public abstract class ContentValSerializer {

  private final String mimeType;

  public ContentValSerializer(String mimeType) {
    this.mimeType = mimeType;
  }

  public boolean matches(String mimeType) {
    return StringUtils.equalsIgnoreCase(this.mimeType, mimeType);
  }

  public String getMimeType() {
    return mimeType;
  }

  /**
   * Serialize {@link ContentVal}
   *
   * @param contentVal
   * @return
   */
  public abstract String serialize(ContentVal contentVal);
}
