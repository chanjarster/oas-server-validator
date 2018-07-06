package me.chanjar.oas.server.validator.core.spec;

import io.swagger.v3.oas.models.parameters.Parameter;

import java.util.List;

import static java.util.stream.Collectors.toList;

public abstract class AbstractParameterExtractor<T extends Parameter> implements ParameterExtractor<T> {

  private final String in;

  public AbstractParameterExtractor(String in) {
    this.in = in;
  }

  @Override
  public List<T> extract(List<Parameter> parameterList) {

    return (List) parameterList
        .stream()
        .filter(p -> in.equalsIgnoreCase(p.getIn()))
        .collect(toList());

  }
}
