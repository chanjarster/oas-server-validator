package me.chanjar.oas.server.validator.core.http;

import java.util.ArrayList;
import java.util.List;

public class OASRequest {

  private String path;

  private List<OASHeader> headers = new ArrayList<>();

  private List<OASCookie> cookies = new ArrayList<>();

  private String method;

  /**
   * use responses.[http-status-code].content.[mime-type] as request's "Accept" header
   */
  private String accept;

  /**
   * use content.content.[mime-type] as request's "Content-Type" header
   */
  private String contentType;

  private byte[] content;

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public List<OASHeader> getHeaders() {
    return headers;
  }

  public void setHeaders(List<OASHeader> headers) {
    this.headers = headers;
  }

  public List<OASCookie> getCookies() {
    return cookies;
  }

  public void setCookies(List<OASCookie> cookies) {
    this.cookies = cookies;
  }

  public String getAccept() {
    return accept;
  }

  public void setAccept(String accept) {
    this.accept = accept;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public byte[] getContent() {
    return content;
  }

  public void setContent(byte[] content) {
    this.content = content;
  }

  /**
   * Corresponding to {@link io.swagger.v3.oas.models.parameters.CookieParameter}
   */
  public static class OASCookie {

    private String name;
    private String value;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getValue() {
      return value;
    }

    public void setValue(String value) {
      this.value = value;
    }
  }

  /**
   * Corresponding to {@link io.swagger.v3.oas.models.parameters.HeaderParameter}
   */
  public static class OASHeader {

    private String name;
    private List<String> values;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public List<String> getValues() {
      return values;
    }

    public void setValues(List<String> values) {
      this.values = values;
    }
  }

}
