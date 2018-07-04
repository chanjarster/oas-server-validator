package me.chanjar.openapiv3.mockmvc.components;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.headers.Header;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;


import java.util.function.Function;
import java.util.function.Supplier;

public abstract class ComponentDereferencer {

  private ComponentDereferencer() {
    // singleton
  }

  public static Parameter getParameter(OpenAPI openAPI, String ref) {

    return getObject(ref,
        () -> "Parameter",
        () -> ref.replaceFirst("#/components/parameters/", ""),
        (_ref) -> openAPI.getComponents().getParameters().get(_ref));

  }

  public static RequestBody getRequestBody(OpenAPI openAPI, String ref) {

    return getObject(ref,
        () -> "RequestBody",
        () -> ref.replaceFirst("#/components/requestBodies/", ""),
        (_ref) -> openAPI.getComponents().getRequestBodies().get(_ref));

  }

  public static ApiResponse getResponse(OpenAPI openAPI, String ref) {

    return getObject(ref,
        () -> "ApiResponse",
        () -> ref.replaceFirst("#/components/responses/", ""),
        (_ref) -> openAPI.getComponents().getResponses().get(_ref));

  }

  public static Header getHeader(OpenAPI openAPI, String ref) {

    return getObject(ref,
        () -> "Header",
        () -> ref.replaceFirst("#/components/headers/", ""),
        (_ref) -> openAPI.getComponents().getHeaders().get(_ref));

  }

  public static Schema getSchema(OpenAPI openAPI, String ref) {

    return getObject(ref,
        () -> "Schema",
        () -> ref.replaceFirst("#/components/schemas/", ""),
        (_ref) -> openAPI.getComponents().getSchemas().get(_ref));

  }

  private static <T> T getObject(String ref, Supplier<String> objectType, Supplier<String> nomalizedRefName, Function<String, T> finder) {

    try {
      T object = finder.apply(nomalizedRefName.get());

      if (object == null) {
        throw new ComponentDeferenceException("Cannot find " + objectType.get() + " object by reference: " + ref);
      }

      return object;

    } catch (Exception e) {
      throw new ComponentDeferenceException("Cannot find " + objectType.get() + " object by reference: " + ref, e);
    }

  }

}
