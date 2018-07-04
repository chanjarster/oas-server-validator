package me.chanjar.openapiv3.mockmvc.operations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import me.chanjar.openapiv3.mockmvc.ValidationErrorException;
import org.springframework.http.HttpMethod;

public class OperationResolver {

  private OpenAPI openAPI;

  public Operation resolve(OpenAPI openAPI, String path, HttpMethod httpMethod) {

    PathItem pathItem = openAPI.getPaths().get(path);
    switch (httpMethod) {
    case GET:
      return pathItem.getGet();
    case POST:
      return pathItem.getPost();
    case PUT:
      return pathItem.getPut();
    case DELETE:
      return pathItem.getDelete();
    case OPTIONS:
      return pathItem.getOptions();
    case HEAD:
      return pathItem.getHead();
    case PATCH:
      return pathItem.getPatch();
    // case TRACE:
    //   return pathItem.getTrace();
    default:
      throw new ValidationErrorException("Not supported http method: " + httpMethod.name());
    }

  }
}
