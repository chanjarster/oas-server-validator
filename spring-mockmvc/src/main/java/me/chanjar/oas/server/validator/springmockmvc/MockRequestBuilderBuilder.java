package me.chanjar.oas.server.validator.springmockmvc;

import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.parameters.RequestBody;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

public class MockRequestBuilderBuilder {

  private String path;
  private PathItem.HttpMethod httpMethod;
  private RequestBody requestBody;
  private String accept;

  public List<MockHttpServletRequestBuilder> build() {
    // use requestBody.content.[mime-type] as request's "Content-Type" header
    // TODO
    return null;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public void setHttpMethod(PathItem.HttpMethod httpMethod) {
    this.httpMethod = httpMethod;
  }


  public void setRequestBody(RequestBody requestBody) {
    this.requestBody = requestBody;
  }

  public void setAccept(String accept) {
    this.accept = accept;
  }

  public String getAccept() {
    return accept;
  }
}
