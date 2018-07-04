package me.chanjar.openapiv3.mockmvc.parameters;

import io.swagger.v3.oas.models.parameters.CookieParameter;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.parameters.PathParameter;
import io.swagger.v3.oas.models.parameters.QueryParameter;

import java.util.ArrayList;
import java.util.List;

public class TypeParametersGroup {

  private List<PathParameter> pathParameters = new ArrayList<>();

  private List<QueryParameter> queryParameters = new ArrayList<>();

  private List<HeaderParameter> headerParameters = new ArrayList<>();

  private List<CookieParameter> cookieParameters = new ArrayList<>();

  public List<PathParameter> getPathParameters() {
    return pathParameters;
  }

  public void setPathParameters(List<PathParameter> pathParameters) {
    this.pathParameters = pathParameters;
  }

  public List<QueryParameter> getQueryParameters() {
    return queryParameters;
  }

  public void setQueryParameters(List<QueryParameter> queryParameters) {
    this.queryParameters = queryParameters;
  }

  public List<HeaderParameter> getHeaderParameters() {
    return headerParameters;
  }

  public void setHeaderParameters(List<HeaderParameter> headerParameters) {
    this.headerParameters = headerParameters;
  }

  public List<CookieParameter> getCookieParameters() {
    return cookieParameters;
  }

  public void setCookieParameters(List<CookieParameter> cookieParameters) {
    this.cookieParameters = cookieParameters;
  }
}
