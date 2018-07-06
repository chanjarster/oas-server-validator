package me.chanjar.oas.server.validator.core.engine;

public class OASValidationOptions {

  private String baseUrl = "";
  private boolean fireGoodRequests = true;
  private boolean fireBadRequests = true;
  private boolean validateResponseSchema = true;

  public void setBaseUrl(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  public void setFireGoodRequests(boolean fireGoodRequests) {
    this.fireGoodRequests = fireGoodRequests;
  }

  public void setFireBadRequests(boolean fireBadRequests) {
    this.fireBadRequests = fireBadRequests;
  }

  public void setValidateResponseSchema(boolean validateResponseSchema) {
    this.validateResponseSchema = validateResponseSchema;
  }
}
