package me.chanjar.openapiv3.mockmvc.validator;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import me.chanjar.openapiv3.mockmvc.ValidationErrorException;
import me.chanjar.openapiv3.mockmvc.parameters.TypeParametersGroup;
import me.chanjar.openapiv3.mockmvc.springmock.MockRequestBuilderBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.io.IOException;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class RequestRunner {

  private MockMvc mockMvc;

  private Operation operation;

  private String httpStatusCode;

  private String path;

  private HttpMethod httpMethod;

  private TypeParametersGroup typeParametersGroup;

  private RequestBody requestBody;

  public void run() throws IOException {

    ApiResponse responseDefinition = operation.getResponses().get(httpStatusCode);

    Set<String> responseContentTypes = responseDefinition.getContent().keySet();

    for (String responseContentType : responseContentTypes) {

      MockRequestBuilderBuilder builderBuilder = new MockRequestBuilderBuilder();
      // use responses.[http-status-code].content.[mime-type] as request's "Accept" header
      builderBuilder.setAccept(responseContentType);
      builderBuilder.setPath(path);
      builderBuilder.setHttpMethod(httpMethod);
      builderBuilder.setTypeParametersGroup(typeParametersGroup);
      builderBuilder.setRequestBody(requestBody);

      List<MockHttpServletRequestBuilder> requestList = builderBuilder.build();

      for (MockHttpServletRequestBuilder requestBuilder : requestList) {

        StringWriter writer = new StringWriter();

        try {

          ResultActions resultActions = mockMvc.perform(requestBuilder);
          resultActions.andDo(print(writer));
          writer.close();

          ResponseValidator responseValidator = new ResponseValidator();
          responseValidator.setResultActions(resultActions);
          responseValidator.setApiResponse(responseDefinition);
          // use responses.[http-status-code].content.[mime-type] as response's "Content-Type" header
          responseValidator.setContentType(responseContentType);
          responseValidator.setHttpStatusCode(httpStatusCode);
          responseValidator.validate();

        } catch (Exception e) {

          String requestInfo = writer.toString();
          if (StringUtils.isNotBlank(requestInfo)) {

            throw new ValidationErrorException(
                MessageFormat.format("Exception: {}\nRequest info: {}", e.getMessage(), requestInfo),
                e
            );

          }

          throw new ValidationErrorException(
              MessageFormat.format("Exception: {}", e.getMessage()),
              e
          );

        } finally {
          writer.close();
        }
      }

    }

  }

  public void setPath(String path) {
    this.path = path;
  }

  public void setHttpMethod(HttpMethod httpMethod) {
    this.httpMethod = httpMethod;
  }

  /**
   * @param operation
   * @see OperationRunner#setOperation(Operation)
   */
  public void setOperation(Operation operation) {
    this.operation = operation;
  }

  public void setTypeParametersGroup(TypeParametersGroup typeParametersGroup) {
    this.typeParametersGroup = typeParametersGroup;
  }

  public void setRequestBody(RequestBody requestBody) {
    this.requestBody = requestBody;
  }

  public void setMockMvc(MockMvc mockMvc) {
    this.mockMvc = mockMvc;
  }

  public void setHttpStatusCode(String httpStatusCode) {
    this.httpStatusCode = httpStatusCode;
  }

}
