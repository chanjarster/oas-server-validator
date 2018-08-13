package me.chanjar.oas.server.validator.core.value.parameter;

import io.swagger.v3.oas.models.parameters.Parameter;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;

import java.util.List;

public interface ParameterValGenerator {

  /**
   * Generate one good {@link SchemaVal}, which satisfies spec
   *
   * @param parameter
   * @return
   */
  ParameterVal generateOne(Parameter parameter);

  /**
   * Generate all good {@link SchemaVal}s, which satisfy spec, including all edge cases
   *
   * @param parameter
   * @return
   */
  List<ParameterVal> generateAll(Parameter parameter);

}
