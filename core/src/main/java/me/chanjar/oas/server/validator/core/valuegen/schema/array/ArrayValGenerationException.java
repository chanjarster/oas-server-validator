package me.chanjar.oas.server.validator.core.valuegen.schema.array;

public class ArrayValGenerationException extends RuntimeException {
  public ArrayValGenerationException() {
    super();
  }

  public ArrayValGenerationException(String message) {
    super(message);
  }

  public ArrayValGenerationException(String message, Throwable cause) {
    super(message, cause);
  }

  public ArrayValGenerationException(Throwable cause) {
    super(cause);
  }

  protected ArrayValGenerationException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
