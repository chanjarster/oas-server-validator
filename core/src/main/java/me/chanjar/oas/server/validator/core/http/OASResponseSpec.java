package me.chanjar.oas.server.validator.core.http;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.headers.Header;
import io.swagger.v3.oas.models.media.MediaType;

import java.util.List;


public class OASResponseSpec {

  /**
   * use responses.[http-status-code].content.[mime-type] as response's "Content-Type" header
   */
  private String contentType;
  private List<Header> headers;

  private OpenAPI openAPI;
  private MediaType mediaType;

  public void setOpenAPI(OpenAPI openAPI) {
    this.openAPI = openAPI;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public List<Header> getHeaders() {
    return headers;
  }

  public void setHeaders(List<Header> headers) {
    this.headers = headers;
  }

  public MediaType getMediaType() {
    return mediaType;
  }

  public void setMediaType(MediaType mediaType) {
    this.mediaType = mediaType;
  }
}
