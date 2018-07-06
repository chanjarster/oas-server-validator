package me.chanjar.oas.server.validator.core.interaction;

public class OASRequestResponse {

  private final OASRequest oasRequest;
  private final OASResponseSpec oasResponse;

  public OASRequestResponse(OASRequest oasRequest,
      OASResponseSpec oasResponse) {
    this.oasRequest = oasRequest;
    this.oasResponse = oasResponse;
  }

  public OASRequest getOasRequest() {
    return oasRequest;
  }

  public OASResponseSpec getOasResponse() {
    return oasResponse;
  }
}
