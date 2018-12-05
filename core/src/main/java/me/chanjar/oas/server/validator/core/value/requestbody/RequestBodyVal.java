package me.chanjar.oas.server.validator.core.value.requestbody;

import me.chanjar.oas.server.validator.core.value.mediatype.MediaTypeVal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

public class RequestBodyVal {

  /**
   * A special RequestBodyVal which means it should be ignored.
   * <p>A RequestBodyVal should not be sent if it's ignored</p>
   */
  public static final RequestBodyVal IGNORED_VAL = new RequestBodyVal("IGNORED", null);

  private final String contentType;

  private final MediaTypeVal mediaTypeVal;

  public RequestBodyVal(String contentType, MediaTypeVal mediaTypeVal) {
    this.contentType = contentType;
    this.mediaTypeVal = mediaTypeVal;
  }

  public String getContentType() {
    return contentType;
  }

  public MediaTypeVal getMediaTypeVal() {
    return mediaTypeVal;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RequestBodyVal that = (RequestBodyVal) o;
    return Objects.equals(contentType, that.contentType) &&
        Objects.equals(mediaTypeVal, that.mediaTypeVal);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contentType, mediaTypeVal);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("contentType", contentType)
        .append("mediaTypeVal", mediaTypeVal)
        .toString();
  }

}
