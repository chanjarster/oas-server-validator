package me.chanjar.oas.server.validator.core.valuegen.requestbody;

import static me.chanjar.oas.server.validator.core.valuegen.mediatype.MediaTypeValGeneratorFactory.badMediaType;
import static me.chanjar.oas.server.validator.core.valuegen.mediatype.MediaTypeValGeneratorFactory.goodMediaType;

public abstract class RequestBodyValGeneratorFactory {

  private RequestBodyValGeneratorFactory() {
    // singleton
  }

  public static RequestBodyValGenerator goodRequestBody() {

    return new DefaultRequestBodyValGenerator(
        true,
        goodMediaType()
    );
  }

  public static RequestBodyValGenerator badRequestBody() {

    return new DefaultRequestBodyValGenerator(
        false,
        badMediaType()
    );

  }

}
