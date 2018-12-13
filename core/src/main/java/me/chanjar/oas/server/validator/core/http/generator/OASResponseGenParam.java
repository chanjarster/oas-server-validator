package me.chanjar.oas.server.validator.core.http.generator;

import io.swagger.v3.oas.models.headers.Header;
import io.swagger.v3.oas.models.media.MediaType;

import java.util.Map;

public class OASResponseGenParam {

  private Integer statusCode;
  private String contentType;
  private Map<String, Header> headers;
  private MediaType mediaType;

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public String getContentType() {
    return contentType;
  }

  public void setHeaders(Map<String, Header> headers) {
    this.headers = headers;
  }

  public Map<String, Header> getHeaders() {
    return headers;
  }

  public void setStatusCode(Integer statusCode) {
    this.statusCode = statusCode;
  }

  public Integer getStatusCode() {
    return statusCode;
  }

  public void setMediaType(MediaType mediaType) {
    this.mediaType = mediaType;
  }

  public MediaType getMediaType() {
    return mediaType;
  }
}
