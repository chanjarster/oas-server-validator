package me.chanjar.openapiv3.mockmvc.components;

public class ComponentDeferenceException extends RuntimeException {
  public ComponentDeferenceException() {
  }

  public ComponentDeferenceException(String message) {
    super(message);
  }

  public ComponentDeferenceException(String message, Throwable cause) {
    super(message, cause);
  }

  public ComponentDeferenceException(Throwable cause) {
    super(cause);
  }

  public ComponentDeferenceException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
