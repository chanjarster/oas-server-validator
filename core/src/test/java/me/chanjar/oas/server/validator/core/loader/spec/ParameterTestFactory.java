package me.chanjar.oas.server.validator.core.loader.spec;

import io.swagger.v3.oas.models.parameters.Parameter;

public abstract class ParameterTestFactory {

  private ParameterTestFactory() {
    // singleton
  }

  public static Parameter makeNew(String in, String name) {

    Parameter parameter = new Parameter() {
      @Override
      public String toString() {
        return "in=" + getIn() + ", name=" + getName();
      }
    };
    parameter.setIn(in);
    parameter.setName(name);
    return parameter;

  }

  public static Parameter makeNew(String in, String name, String description) {

    Parameter parameter = new Parameter() {
      @Override
      public String toString() {
        return "in=" + getIn() + ", name=" + getName() + ", description=" + getDescription();
      }
    };
    parameter.setIn(in);
    parameter.setName(name);
    parameter.setDescription(description);
    return parameter;

  }

}
