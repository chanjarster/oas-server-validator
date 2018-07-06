package me.chanjar.oas.server.validator.core.loader;

public class OpenAPIV3LoaderException extends RuntimeException {
  public OpenAPIV3LoaderException() {
  }

  public OpenAPIV3LoaderException(String message) {
    super(message);
  }

  public OpenAPIV3LoaderException(String message, Throwable cause) {
    super(message, cause);
  }

  public OpenAPIV3LoaderException(Throwable cause) {
    super(cause);
  }

  public OpenAPIV3LoaderException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
