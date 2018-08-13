package me.chanjar.oas.server.validator.core.value.parameters;

import io.swagger.v3.oas.models.parameters.CookieParameter;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.parameters.PathParameter;
import io.swagger.v3.oas.models.parameters.QueryParameter;
import me.chanjar.oas.server.validator.core.value.parameter.ParameterVal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Collections.unmodifiableList;

/**
 * A package of {@link ParameterVal}
 */
public class ParameterValPackage {

  /**
   * Corresponding to {@link QueryParameter}
   */
  private final List<ParameterVal> queryParameters = new ArrayList<>();

  /**
   * Corresponding to {@link PathParameter}
   */
  private final List<ParameterVal> pathParameters = new ArrayList<>();

  /**
   * Corresponding to {@link CookieParameter}
   */
  private final List<ParameterVal> cookieParameters = new ArrayList<>();

  /**
   * Corresponding to {@link HeaderParameter}
   */
  private final List<ParameterVal> headerParameters = new ArrayList<>();

  public ParameterValPackage(Collection<ParameterVal> parameterVals) {

    parameterVals.stream()
        .filter(p -> "path".equalsIgnoreCase(p.getIn()))
        .forEach(p -> pathParameters.add(p));

    parameterVals.stream()
        .filter(p -> "query".equalsIgnoreCase(p.getIn()))
        .forEach(p -> queryParameters.add(p));

    parameterVals.stream()
        .filter(p -> "cookie".equalsIgnoreCase(p.getIn()))
        .forEach(p -> cookieParameters.add(p));

    parameterVals.stream()
        .filter(p -> "header".equalsIgnoreCase(p.getIn()))
        .forEach(p -> headerParameters.add(p));

  }

  public List<ParameterVal> getQueryParameters() {
    return unmodifiableList(queryParameters);
  }

  public List<ParameterVal> getPathParameters() {
    return unmodifiableList(pathParameters);
  }

  public List<ParameterVal> getCookieParameters() {
    return unmodifiableList(cookieParameters);
  }

  public List<ParameterVal> getHeaderParameters() {
    return unmodifiableList(headerParameters);
  }
}
