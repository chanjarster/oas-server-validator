package me.chanjar.oas.server.validator.core.valuegen.requestbody;

import io.swagger.v3.oas.models.parameters.RequestBody;
import me.chanjar.oas.server.validator.core.value.requestbody.RequestBodyVal;

import java.util.List;

public interface RequestBodyValGenerator {

  /**
   * Generate one {@link RequestBodyVal}, which satisfies spec
   *
   * @param contentType
   * @param requestBody
   * @return
   */
  RequestBodyVal generateOne(String contentType, RequestBody requestBody);

  /**
   * Generate all {@link RequestBodyVal}s, which satisfy spec, including all edge cases
   *
   * @param contentType
   * @param requestBody
   * @return
   */
  List<RequestBodyVal> generateAll(String contentType, RequestBody requestBody);

}
