package me.chanjar.openapiv3.mockmvc.validator;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import me.chanjar.openapiv3.mockmvc.parameters.*;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.List;

public class OperationRunner {

  private MockMvc mockMvc;

  private OpenAPI openAPI;
  private Operation operation;

  private String path;
  private HttpMethod httpMethod;

  private String httpStatusCode = "default";

  public void run() throws IOException {

    PathItem pathItem = openAPI.getPaths().get(path);

    PathItemParametersResolver pathItemParametersLocator = new PathItemParametersResolver(openAPI, pathItem);
    List<Parameter> pathItemParameters = pathItemParametersLocator.resolveParameters();

    OperationParametersResolver operationParametersLocator = new OperationParametersResolver(openAPI, operation);
    List<Parameter> operationParameters = operationParametersLocator.resolveParameters();

    ParametersMerger parametersMerger = new ParametersMerger();
    List<Parameter> effectiveParameters = parametersMerger.merge(pathItemParameters, operationParameters);

    ParametersFilter parametersFilter = new ParametersFilter();
    List<Parameter> filteredParameters = parametersFilter.filter(effectiveParameters);

    TypeParametersGroupBuilder typeParametersGroupBuilder = new TypeParametersGroupBuilder();
    TypeParametersGroup typeParametersGroup = typeParametersGroupBuilder.build(filteredParameters);

    RequestBodyResolver requestBodyResolver = new RequestBodyResolver(openAPI, operation);
    RequestBody requestBody = requestBodyResolver.resolve();

    RequestRunner requestRunner = new RequestRunner();
    requestRunner.setMockMvc(mockMvc);
    requestRunner.setPath(path);
    requestRunner.setHttpMethod(httpMethod);
    requestRunner.setRequestBody(requestBody);
    requestRunner.setHttpStatusCode(httpStatusCode);
    requestRunner.setOperation(operation);
    requestRunner.setTypeParametersGroup(typeParametersGroup);
    requestRunner.run();

  }

  /**
   * Set spring {@link MockMvc} Object
   *
   * @param mockMvc
   */
  public void setMockMvc(MockMvc mockMvc) {
    this.mockMvc = mockMvc;
  }

  /**
   * Which {@link OpenAPI} Object will be tested
   *
   * @param openAPI
   */
  public void setOpenAPI(OpenAPI openAPI) {
    this.openAPI = openAPI;
  }

  /**
   * Which Path will be tested<br>
   * Path is also {@link PathItem} Object<br>
   * {@link PathItem} locates at: <code>paths.[path(PathItem)]</code><br>
   * see: https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#pathItemObject
   *
   * @param path
   */
  public void setPath(String path) {
    this.path = path;
  }

  /**
   * Which {@link Operation} Object will the tested<br>
   * {@link Operation} locates at: <code>paths.[path(PathItem)].[http-method(Operation)]</code><br>
   * see: https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#operationObject
   *
   * @param operation
   */
  public void setOperation(Operation operation) {
    this.operation = operation;
  }

  /**
   * Which Http Method will the tested<br>
   *
   * @param httpMethod
   * @see #setOperation(Operation)
   */
  public void setHttpMethod(HttpMethod httpMethod) {
    this.httpMethod = httpMethod;
  }

  /**
   * Which Http Status Code will be validated<br>
   * Http Status Code is also {@link ApiResponse} Object<br>
   * {@link ApiResponse} Object locates at: <code>paths.[path(PathItem)].[http-method(Operation)].responses.[http-status-code(Response)]</code><br>
   * see: https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#responses-object
   *
   * @param httpStatusCode
   */
  public void setHttpStatusCode(String httpStatusCode) {
    this.httpStatusCode = httpStatusCode;
  }

}
