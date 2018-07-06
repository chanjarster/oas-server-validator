package me.chanjar.oas.server.validator.core.valuegen;

public class SchemaValGenerationException extends RuntimeException {
  public SchemaValGenerationException() {
  }

  public SchemaValGenerationException(String message) {
    super(message);
  }

  public SchemaValGenerationException(String message, Throwable cause) {
    super(message, cause);
  }

  public SchemaValGenerationException(Throwable cause) {
    super(cause);
  }

  public SchemaValGenerationException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
