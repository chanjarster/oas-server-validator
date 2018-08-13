package me.chanjar.oas.server.validator.core.value.parameters;

import io.swagger.v3.oas.models.parameters.Parameter;

import java.util.List;

public interface ParameterValPackageGenerator {

  ParameterValPackage generateOne(List<Parameter> parameters);

  List<ParameterValPackage> generateAll(List<Parameter> parameters);

}
