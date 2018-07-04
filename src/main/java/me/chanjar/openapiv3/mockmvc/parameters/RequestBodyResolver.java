package me.chanjar.openapiv3.mockmvc.parameters;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.parameters.RequestBody;

import me.chanjar.openapiv3.mockmvc.components.ComponentDereferencer;
import org.apache.commons.lang3.StringUtils;

public class RequestBodyResolver {

  private final OpenAPI openAPI;
  private final Operation operation;

  public RequestBodyResolver(OpenAPI openAPI, Operation operation) {
    this.openAPI = openAPI;
    this.operation = operation;
  }

  public RequestBody resolve() {

    RequestBody requestBody = operation.getRequestBody();
    if (requestBody == null) {
      return null;
    }

    String $ref = requestBody.get$ref();
    if (StringUtils.isNotBlank($ref)) {
      return ComponentDereferencer.getRequestBody(openAPI, $ref);
    }

    return requestBody;

  }

}
