package me.chanjar.oas.server.validator.core.encoder.schema;

public class SchemaEncodingException extends RuntimeException {
  public SchemaEncodingException() {
    super();
  }

  public SchemaEncodingException(String message) {
    super(message);
  }

  public SchemaEncodingException(String message, Throwable cause) {
    super(message, cause);
  }

  public SchemaEncodingException(Throwable cause) {
    super(cause);
  }

  protected SchemaEncodingException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
