package me.chanjar.oas.server.validator.core.valuegen.requestbody;

public class RequestBodyValGenerationException extends RuntimeException {

  public RequestBodyValGenerationException() {
    super();
  }

  public RequestBodyValGenerationException(String message) {
    super(message);
  }

  public RequestBodyValGenerationException(String message, Throwable cause) {
    super(message, cause);
  }

  public RequestBodyValGenerationException(Throwable cause) {
    super(cause);
  }

  protected RequestBodyValGenerationException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
