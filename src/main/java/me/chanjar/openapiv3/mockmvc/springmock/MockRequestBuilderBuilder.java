package me.chanjar.openapiv3.mockmvc.springmock;

import io.swagger.v3.oas.models.parameters.RequestBody;
import me.chanjar.openapiv3.mockmvc.parameters.TypeParametersGroup;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

public class MockRequestBuilderBuilder {

  private String path;
  private HttpMethod httpMethod;
  private TypeParametersGroup typeParametersGroup;
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

  public void setHttpMethod(HttpMethod httpMethod) {
    this.httpMethod = httpMethod;
  }

  public void setTypeParametersGroup(TypeParametersGroup typeParametersGroup) {
    this.typeParametersGroup = typeParametersGroup;
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
