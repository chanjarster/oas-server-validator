package me.chanjar.oas.server.validator.core.generator;

import io.swagger.v3.oas.models.parameters.*;

import java.util.List;

public class OASRequestGenParam {

  private String accept;
  private RequestBody requestBody;
  private List<CookieParameter> cookieParameters;
  private List<HeaderParameter> headerParameters;
  private List<QueryParameter> queryParameters;
  private List<PathParameter> pathParameters;

  public void setAccept(String accept) {
    this.accept = accept;
  }

  public String getAccept() {
    return accept;
  }

  public void setRequestBody(RequestBody requestBody) {
    this.requestBody = requestBody;
  }

  public RequestBody getRequestBody() {
    return requestBody;
  }

  public void setCookieParameters(List<CookieParameter> cookieParameters) {
    this.cookieParameters = cookieParameters;
  }

  public List<CookieParameter> getCookieParameters() {
    return cookieParameters;
  }

  public void setHeaderParameters(List<HeaderParameter> headerParameters) {
    this.headerParameters = headerParameters;
  }

  public List<HeaderParameter> getHeaderParameters() {
    return headerParameters;
  }

  public void setQueryParameters(List<QueryParameter> queryParameters) {
    this.queryParameters = queryParameters;
  }

  public List<QueryParameter> getQueryParameters() {
    return queryParameters;
  }

  public void setPathParameters(List<PathParameter> pathParameters) {
    this.pathParameters = pathParameters;
  }

  public List<PathParameter> getPathParameters() {
    return pathParameters;
  }
}
