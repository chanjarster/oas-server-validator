package me.chanjar.openapiv3.mockmvc.parameters;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.parameters.Parameter;

import me.chanjar.openapiv3.mockmvc.components.ComponentDereferencer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

abstract class ParametersResolver {

  private final OpenAPI openAPI;

  public ParametersResolver(OpenAPI openAPI) {
    this.openAPI = openAPI;
  }

  /**
   * Resolve {@link Parameter} objects from Object such as {@link PathItem} and {@link Operation}, with reference handling.
   *
   * @return
   */
  public final List<Parameter> resolveParameters() {

    List<Parameter> parametersFromObject = getParametersFromObject();
    List<Parameter> parameters;
    if (CollectionUtils.isEmpty(parametersFromObject)) {
      parameters = new ArrayList<>();
    } else {
      parameters = new ArrayList<>(parametersFromObject);
    }

    List<Parameter> resolvedParameters = parameters.stream().map(p -> {
      String $ref = p.get$ref();
      if (StringUtils.isNotBlank($ref)) {
        return ComponentDereferencer.getParameter(openAPI, $ref);
      }
      return p;
    }).collect(toList());

    return resolvedParameters;

  }

  /**
   * get {@link Parameter} objects directly from Object such as {@link PathItem} and {@link Operation}
   *
   * @return
   */
  protected abstract List<Parameter> getParametersFromObject();



}
