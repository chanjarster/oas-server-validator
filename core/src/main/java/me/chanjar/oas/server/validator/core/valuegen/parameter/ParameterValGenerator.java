package me.chanjar.oas.server.validator.core.valuegen.parameter;

import io.swagger.v3.oas.models.parameters.Parameter;
import me.chanjar.oas.server.validator.core.value.parameter.ParameterVal;

import java.util.List;

public interface ParameterValGenerator {

  /**
   * Generate one {@link ParameterVal}, which satisfies spec
   *
   * @param parameter
   * @return
   */
  ParameterVal generateOne(Parameter parameter);

  /**
   * Generate all {@link ParameterVal}s, which satisfy spec, including all edge cases
   *
   * @param parameter
   * @return
   */
  List<ParameterVal> generateAll(Parameter parameter);

}
