package me.chanjar.openapiv3.mockmvc.validator;

import io.swagger.v3.oas.models.responses.ApiResponse;
import me.chanjar.openapiv3.mockmvc.ValidationErrorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.test.web.servlet.ResultActions;

import java.text.MessageFormat;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ResponseValidator {

  private String requestInfo;

  private ResultActions resultActions;

  private ApiResponse response;
  private String contentType;
  private String httpStatusCode;

  public void validate() {
    // TODO

    try {

      doValidation();

    } catch (AssertionError e) {

      throw new AssertionError(
          MessageFormat.format("Assertion Error: {}\nRequest info: {}", e.getMessage(), requestInfo),
          e
      );
    } catch (Exception e) {

      throw new ValidationErrorException(
          MessageFormat.format("Exception: {}\nRequest info: {}", e.getMessage(), requestInfo),
          e
      );

    }
  }

  private void doValidation() throws Exception {

    if (StringUtils.isNotBlank(httpStatusCode) && !"default".equalsIgnoreCase(httpStatusCode)) {
      resultActions.andExpect(status().is(Integer.parseInt(httpStatusCode)));
    }

    resultActions
        .andExpect(header().exists("Content-Type"))
        .andExpect(header().string("Content-Type", contentType));

    // TODO make result matcher based on response
  }

  public void setRequestInfo(String requestInfo) {
    this.requestInfo = requestInfo;
  }

  public void setResultActions(ResultActions resultActions) {
    this.resultActions = resultActions;
  }

  public void setApiResponse(ApiResponse response) {
    this.response = response;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public void setHttpStatusCode(String httpStatusCode) {
    this.httpStatusCode = httpStatusCode;
  }
}
