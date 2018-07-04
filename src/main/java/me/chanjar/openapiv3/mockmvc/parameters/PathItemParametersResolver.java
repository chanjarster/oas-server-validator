package me.chanjar.openapiv3.mockmvc.parameters;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.parameters.Parameter;


import java.util.List;

/**
 * PathItem parameter list locator<br>
 * see: https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#path-item-object
 */
public class PathItemParametersResolver extends ParametersResolver {

  private final PathItem pathItem;

  public PathItemParametersResolver(OpenAPI openAPI, PathItem pathItem) {
    super(openAPI);
    this.pathItem = pathItem;
  }

  @Override
  protected List<Parameter> getParametersFromObject() {
    return pathItem.getParameters();
  }

}
