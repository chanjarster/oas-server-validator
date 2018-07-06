package me.chanjar.oas.server.validator.springmockmvc;

import me.chanjar.oas.server.validator.core.engine.OASRequestRunner;
import me.chanjar.oas.server.validator.core.interaction.OASRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * see: {@link MockMvcRequestBuilders}
 */
public class SpringMockMvcRequestRunner implements OASRequestRunner<ResultActions> {

  private MockMvc mockMvc;

  private List<MockHttpServletRequestCustomizer> requestCustomizers = new ArrayList<>();

  public SpringMockMvcRequestRunner(MockMvc mockMvc) {
    this.mockMvc = mockMvc;
  }

  @Override
  public ResultActions doRequest(OASRequest oasRequest) throws Exception {

    String path = oasRequest.getPath();
    String method = oasRequest.getMethod();

    MockHttpServletRequestBuilder requestBuilder;
    if ("get".equalsIgnoreCase(method)) {
      // TODO be careful to url encoding
      requestBuilder = get(path);
    } else if ("post".equalsIgnoreCase(method)) {
      requestBuilder = post(path);
      // TODO when to use multipart
      // MockMvcRequestBuilders.multipart(path, new Object[0]);
    } else if ("put".equalsIgnoreCase(method)) {
      requestBuilder = put(path);
    } else if ("delete".equalsIgnoreCase(method)) {
      requestBuilder = delete(path);
    } else if ("options".equalsIgnoreCase(method)) {
      requestBuilder = options(path);
    } else if ("head".equalsIgnoreCase(method)) {
      requestBuilder = head(path);
    } else if ("patch".equalsIgnoreCase(method)) {
      requestBuilder = patch(path);
    } else if ("trace".equalsIgnoreCase(method)) {
      requestBuilder = request(HttpMethod.TRACE, path, new Object[0]);
    } else {
      throw new UnsupportedOperationException("Http Method: " + method + " is not supported");
    }

    if (StringUtils.isNotBlank(oasRequest.getAccept())) {
      requestBuilder.accept(oasRequest.getAccept());
    }

    if (StringUtils.isNotBlank(oasRequest.getContentType())) {
      requestBuilder.contentType(oasRequest.getContentType());
    }

    oasRequest.getCookies().stream()
        .map(c -> new Cookie(c.getName(), c.getValue()))
        .forEach(c -> requestBuilder.cookie(c));

    oasRequest.getHeaders().stream()
        .forEach(h -> requestBuilder.header(h.getName(), h.getValues().toArray()));

    requestBuilder.content(oasRequest.getContent());

    requestCustomizers.forEach(c -> c.customize(requestBuilder));

    return mockMvc.perform(requestBuilder);

  }

  public void addRequestCustomizer(MockHttpServletRequestCustomizer requestCustomizer) {
    this.requestCustomizers.add(requestCustomizer);
  }

}
