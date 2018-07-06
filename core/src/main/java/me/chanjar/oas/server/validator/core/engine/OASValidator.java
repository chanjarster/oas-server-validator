package me.chanjar.oas.server.validator.core.engine;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.parameters.*;
import io.swagger.v3.oas.models.responses.ApiResponse;
import me.chanjar.oas.server.validator.core.generator.OASRequestGenParam;
import me.chanjar.oas.server.validator.core.generator.OASRequestResponseGenerator;
import me.chanjar.oas.server.validator.core.generator.OASResponseGenParam;
import me.chanjar.oas.server.validator.core.interaction.OASRequestResponse;
import me.chanjar.oas.server.validator.core.spec.*;

import java.util.List;
import java.util.Set;

public abstract class OASValidator<RES, R extends OASRequestRunner<RES>, V extends OASResponseValidator<RES>> {

  private final OpenAPI openAPI;

  private ValidParametersFilter validParametersFilter = new ValidParametersFilter();
  private ParametersMerger parametersMerger = new ParametersMerger();

  private CookieParameterExtractor cookieParameterExtractor = new CookieParameterExtractor();
  private HeaderParameterExtractor headerParameterExtractor = new HeaderParameterExtractor();
  private QueryParameterExtractor queryParameterExtractor = new QueryParameterExtractor();
  private PathParameterExtractor pathParameterExtractor = new PathParameterExtractor();

  private OASRequestResponseGenerator oasRequestResponseGenerator = null;

  private OASValidationOptions options = new OASValidationOptions();

  private R oasRequestRunner = null;
  private V oasResponseValidator = null;

  protected OASValidator(OpenAPI openAPI) {
    this.openAPI = openAPI;
  }

  /**
   * @param path                   Which Path will be tested<br>
   *                               Path is also {@link PathItem} Object<br>
   *                               {@link PathItem} locates at: <code>paths.[path(PathItem)]</code><br>
   *                               see <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#pathItemObject">Path Item Object</a>
   * @param httpMethod             Which {@link Operation} Object will the tested<br>
   *                               {@link Operation} locates at: <code>paths.[path(PathItem)].[http-method(Operation)]</code><br>
   *                               see <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#operationObject">Operation Object</a>
   * @param responseHttpStatusCode Which Http Status Code will be validated<br>
   *                               Http Status Code is also {@link ApiResponse} Object<br>
   *                               {@link ApiResponse} Object locates at: <code>paths.[path(PathItem)].[http-method(Operation)].responses.[http-status-code(Response)]</code><br>
   *                               see <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#responses-object">Response Object</a>
   */
  public void validate(String path, PathItem.HttpMethod httpMethod, String responseHttpStatusCode) throws Exception {

    PathItem pathItem = openAPI.getPaths().get(path);

    Operation operation = pathItem.readOperationsMap().get(httpMethod);

    List<Parameter> effectiveParameters = parametersMerger.merge(pathItem.getParameters(), operation.getParameters());

    List<Parameter> validParameters = validParametersFilter.filter(effectiveParameters);

    List<CookieParameter> cookieParameters = cookieParameterExtractor.extract(validParameters);
    List<HeaderParameter> headerParameters = headerParameterExtractor.extract(validParameters);
    List<QueryParameter> queryParameters = queryParameterExtractor.extract(validParameters);
    List<PathParameter> pathParameters = pathParameterExtractor.extract(validParameters);

    RequestBody requestBody = operation.getRequestBody();

    ApiResponse apiResponse = operation.getResponses().get(responseHttpStatusCode);

    Set<String> responseContentTypes = apiResponse.getContent().keySet();

    for (String responseContentType : responseContentTypes) {

      OASRequestGenParam oasRequestGenParam = new OASRequestGenParam();
      oasRequestGenParam.setAccept(responseContentType);
      oasRequestGenParam.setRequestBody(requestBody);
      oasRequestGenParam.setCookieParameters(cookieParameters);
      oasRequestGenParam.setHeaderParameters(headerParameters);
      oasRequestGenParam.setQueryParameters(queryParameters);
      oasRequestGenParam.setPathParameters(pathParameters);

      OASResponseGenParam oasResponseGenParam = new OASResponseGenParam();
      if (!"default".equalsIgnoreCase(responseHttpStatusCode)) {
        oasResponseGenParam.setStatusCode(Integer.parseInt(responseHttpStatusCode));
      }
      oasResponseGenParam.setContentType(responseContentType);
      oasResponseGenParam.setHeaders(apiResponse.getHeaders());
      oasResponseGenParam.setMediaType(apiResponse.getContent().get(responseContentType));

      List<OASRequestResponse> oasRequestResponses = oasRequestResponseGenerator
          .create(oasRequestGenParam, oasResponseGenParam);

      for (OASRequestResponse oasRequestResponse : oasRequestResponses) {

        RES res = oasRequestRunner.doRequest(oasRequestResponse.getOasRequest());
        oasResponseValidator.validate(res, oasRequestResponse.getOasResponse());

      }

    }

  }

  public void setOptions(OASValidationOptions options) {
    this.options = options;
  }

  public void setOasRequestRunner(R oasRequestRunner) {
    this.oasRequestRunner = oasRequestRunner;
  }

  public void setOasResponseValidator(V oasResponseValidator) {
    this.oasResponseValidator = oasResponseValidator;
  }
}
