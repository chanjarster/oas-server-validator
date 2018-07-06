package me.chanjar.oas.server.validator.core.interaction;

import java.util.ArrayList;
import java.util.List;

public class OASRequest {

  private String url;

  /**
   * use responses.[http-status-code].content.[mime-type] as request's "Accept" header
   */
  private String accept;

  /**
   * use requestBody.content.[mime-type] as request's "Content-Type" header
   */
  private String contentType;

  private List<Header> headers = new ArrayList<>();

  private String requestBody;

  public static class Header {

    private String name;
    private String value;

  }

}
