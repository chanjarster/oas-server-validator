package me.chanjar.oas.server.validator.core.value.parameters;

import static me.chanjar.oas.server.validator.core.value.parameter.ParameterValGeneratorFactory.goodParameter;

public abstract class ParameterValPackageGeneratorFactory {

  private ParameterValPackageGeneratorFactory() {
    // singleton
  }

  public static ParameterValPackageGenerator goodParameters() {

    return new DefaultParameterValPackageGenerator(
        goodParameter()
    );
  }

  public static ParameterValPackageGenerator badParameters() {

    return new DefaultParameterValPackageGenerator(
        goodParameter(),
        goodParameters()
    );

  }
}
