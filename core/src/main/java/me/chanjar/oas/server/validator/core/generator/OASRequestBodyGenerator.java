package me.chanjar.oas.server.validator.core.generator;

import io.swagger.v3.oas.models.parameters.RequestBody;

public interface OASRequestBodyGenerator {

  /**
   * Use OAS {@link RequestBody} object to generate request body.
   *
   * @param requestBody OAS {@link RequestBody} object.
   * @return
   */
  String generate(RequestBody requestBody);

}
