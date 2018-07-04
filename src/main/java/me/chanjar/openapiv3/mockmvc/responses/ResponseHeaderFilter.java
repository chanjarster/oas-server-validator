package me.chanjar.openapiv3.mockmvc.responses;

import io.swagger.v3.oas.models.headers.Header;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * If a response header is defined with the name <code>"Content-Type"</code>, it SHALL be ignored.<br>
 * see: https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#responseObject
 */
public class ResponseHeaderFilter {

  public Map<String, Header> filter(Map<String, Header> headers) {

    Map<String, Header> result = new LinkedHashMap<>();
    for (Map.Entry<String, Header> entry : headers.entrySet()) {
      String name = entry.getKey();
      Header header = entry.getValue();
      if (!"content-type".equalsIgnoreCase(name)) {
        continue;
      }
      result.put(name, header);
    }
    return result;

  }

}
