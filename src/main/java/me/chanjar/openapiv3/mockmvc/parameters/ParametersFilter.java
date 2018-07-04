package me.chanjar.openapiv3.mockmvc.parameters;

import io.swagger.v3.oas.models.parameters.Parameter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * If parameter's <code>in</code> is <code>header</code> and the <code>name</code> field is
 * <code>"Accept", "Content-Type" or "Authorization"</code>, the parameter definition SHALL be ignored.<br>
 * see: https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#parameterObject
 */
public class ParametersFilter {

  public List<Parameter> filter(List<Parameter> parameters) {

    return parameters
        .stream()
        .filter(p -> {
          if (!"header".equalsIgnoreCase(p.getIn())) {
            return true;
          }

          return !StringUtils.equalsAnyIgnoreCase(p.getName(), "accept", "content-type", "Authorization");
        })
        .collect(toList());
  }

}
