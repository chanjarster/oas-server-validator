package me.chanjar.oas.server.validator.core.valuegen.mediatype;

public class MediaTypeValGenerationException extends RuntimeException {

  public MediaTypeValGenerationException() {
    super();
  }

  public MediaTypeValGenerationException(String message) {
    super(message);
  }

  public MediaTypeValGenerationException(String message, Throwable cause) {
    super(message, cause);
  }

  public MediaTypeValGenerationException(Throwable cause) {
    super(cause);
  }

  protected MediaTypeValGenerationException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
