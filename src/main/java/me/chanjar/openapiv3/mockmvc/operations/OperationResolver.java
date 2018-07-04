package me.chanjar.openapiv3.mockmvc.operations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import me.chanjar.openapiv3.mockmvc.ValidationErrorException;
import org.springframework.http.HttpMethod;

public class OperationResolver {

  private OpenAPI openAPI;
  private String path;
  private HttpMethod httpMethod;

  /**
   * @param openAPI    {@link OpenAPI} object be searched
   * @param path       locates at: <code>paths.[path(PathItem)]</code><br>
   *                   see: <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#paths-object">Paths Object</a>
   * @param httpMethod locates at: <code>paths.[path].[http-method]</code><br>
   *                   see: <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#path-item-object">Path Item Object</a>
   */
  public OperationResolver(OpenAPI openAPI, String path, HttpMethod httpMethod) {
    this.openAPI = openAPI;
    this.path = path;
    this.httpMethod = httpMethod;
  }

  /**
   * Get an {@link Operation} object
   *
   * @return
   */
  public Operation resolve() {

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
