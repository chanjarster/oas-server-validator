package me.chanjar.oas.server.validator.core.engine;

import io.swagger.v3.oas.models.responses.ApiResponse;
import me.chanjar.oas.server.validator.core.http.OASResponseSpec;

/**
 * @param <RES> Response type be validated against
 */
public interface OASResponseValidator<RES> {

  /**
   * Validate responseObject against one content type of {@link ApiResponse}.<br>
   * see: <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#responseObject">Response Object</a>
   *
   * @param responseObject  result from using certain test framework or library to do request
   * @param oasResponseSpec {@link OASResponseSpec} Object
   */
  void validate(RES responseObject, OASResponseSpec oasResponseSpec);
}
