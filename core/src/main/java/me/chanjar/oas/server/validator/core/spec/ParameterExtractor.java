package me.chanjar.oas.server.validator.core.spec;

import io.swagger.v3.oas.models.parameters.Parameter;

import java.util.List;

public interface ParameterExtractor<T extends Parameter> {

  List<T> extract(List<Parameter> parameterList);

}
