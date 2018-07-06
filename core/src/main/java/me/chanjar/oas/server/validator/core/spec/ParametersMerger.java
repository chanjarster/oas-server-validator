package me.chanjar.oas.server.validator.core.spec;

import io.swagger.v3.oas.models.parameters.Parameter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class ParametersMerger {

  /**
   * Override parameters defined in Path Item by ones defined in Operation, and return a new parameter list. <br>
   * see: https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#operation-object
   *
   * @param pathItemParameters
   * @param operationParameters
   * @return
   */
  public List<Parameter> merge(List<Parameter> pathItemParameters, List<Parameter> operationParameters) {

    Map<ParameterKey, Parameter> pathItemParameterMap = pathItemParameters.stream().collect(
        toMap(
            p -> new ParameterKey(p.getIn(), p.getName()),
            identity(),
            (p1, p2) -> p2,
            () -> new LinkedHashMap<>()
        )
    );

    operationParameters.stream().forEach(p -> {
      ParameterKey key = new ParameterKey(p.getIn(), p.getName());
      pathItemParameterMap.put(key, p);
    });

    return new ArrayList<>(pathItemParameterMap.values());
  }

  private class ParameterKey {
    private String in;
    private String name;

    public ParameterKey(String in, String name) {
      this.in = in;
      this.name = name;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      ParameterKey that = (ParameterKey) o;

      if (in != null ? !in.equals(that.in) : that.in != null) return false;
      return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
      int result = in != null ? in.hashCode() : 0;
      result = 31 * result + (name != null ? name.hashCode() : 0);
      return result;
    }
  }
}
