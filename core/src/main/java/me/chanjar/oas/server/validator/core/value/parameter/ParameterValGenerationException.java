package me.chanjar.oas.server.validator.core.value.parameter;

public class ParameterValGenerationException extends RuntimeException {

  public ParameterValGenerationException() {
    super();
  }

  public ParameterValGenerationException(String message) {
    super(message);
  }

  public ParameterValGenerationException(String message, Throwable cause) {
    super(message, cause);
  }

  public ParameterValGenerationException(Throwable cause) {
    super(cause);
  }

  protected ParameterValGenerationException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
