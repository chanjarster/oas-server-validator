package me.chanjar.openapiv3.mockmvc.parameters;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.parameters.Parameter;


import java.util.List;

/**
 * Operation parameter list locator<br>
 * see: https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#operation-object
 */
public class OperationParametersResolver extends ParametersResolver {

  private final Operation operation;

  public OperationParametersResolver(OpenAPI openAPI, Operation operation) {
    super(openAPI);
    this.operation = operation;
  }

  @Override
  protected List<Parameter> getParametersFromObject() {
    return operation.getParameters();
  }

}
