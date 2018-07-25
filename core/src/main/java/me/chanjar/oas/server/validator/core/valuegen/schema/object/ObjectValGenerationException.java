package me.chanjar.oas.server.validator.core.valuegen.schema.object;

public class ObjectValGenerationException extends RuntimeException {
  public ObjectValGenerationException() {
    super();
  }

  public ObjectValGenerationException(String message) {
    super(message);
  }

  public ObjectValGenerationException(String message, Throwable cause) {
    super(message, cause);
  }

  public ObjectValGenerationException(Throwable cause) {
    super(cause);
  }

  protected ObjectValGenerationException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
