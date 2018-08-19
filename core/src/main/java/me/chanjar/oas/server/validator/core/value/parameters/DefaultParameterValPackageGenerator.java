package me.chanjar.oas.server.validator.core.value.parameters;

import io.swagger.v3.oas.models.parameters.Parameter;
import me.chanjar.oas.server.validator.core.value.parameter.ParameterVal;
import me.chanjar.oas.server.validator.core.value.parameter.ParameterValGenerator;

import java.util.*;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class DefaultParameterValPackageGenerator implements ParameterValPackageGenerator {

  private final ParameterValGenerator parameterValGenerator;

  private final ParameterValPackageGenerator defaultParameterValPackageGenerator;

  public DefaultParameterValPackageGenerator(
      ParameterValGenerator parameterValGenerator) {
    this.parameterValGenerator = parameterValGenerator;
    this.defaultParameterValPackageGenerator = this;
  }

  public DefaultParameterValPackageGenerator(
      ParameterValGenerator parameterValGenerator,
      ParameterValPackageGenerator defaultParameterValPackageGenerator) {
    this.parameterValGenerator = parameterValGenerator;
    this.defaultParameterValPackageGenerator = defaultParameterValPackageGenerator;
  }

  @Override
  public ParameterValPackage generateOne(List<Parameter> parameters) {

    List<ParameterVal> parameterVals = parameters.stream()
        .map(parameter -> parameterValGenerator.generateOne(parameter))
        .collect(toList());

    return new ParameterValPackage(parameterVals);

  }

  @Override
  public List<ParameterValPackage> generateAll(List<Parameter> parameters) {

    ParameterValPackage defaultParameterValPackage = defaultParameterValPackageGenerator.generateOne(parameters);

    Map<ParameterKey, ParameterVal> defaultParameterVal = makeMap(defaultParameterValPackage);

    Map<ParameterKey, List<ParameterVal>> parameterKeyParameterVals = new HashMap<>();

    for (Parameter parameter : parameters) {

      ParameterKey parameterKey = new ParameterKey(parameter);
      List<ParameterVal> parameterVals = parameterValGenerator.generateAll(parameter);

      parameterKeyParameterVals.put(parameterKey, parameterVals);

    }

    return permutate(parameterKeyParameterVals, defaultParameterVal);

  }

  private Map<ParameterKey, ParameterVal> makeMap(ParameterValPackage parameterValPackage) {

    List<ParameterVal> parameterVals = new ArrayList<>();

    parameterVals.addAll(parameterValPackage.getCookieParameters());
    parameterVals.addAll(parameterValPackage.getPathParameters());
    parameterVals.addAll(parameterValPackage.getQueryParameters());
    parameterVals.addAll(parameterValPackage.getHeaderParameters());

    return parameterVals.stream().collect(toMap(ParameterKey::new, identity()));

  }

  /**
   * Input:
   * <pre class="code">
   * parameterKeyParameterVals: &lt;name=foo,in=query&gt;: [1, 2], &lt;name=bar,in=query&gt;: ["a", "b"]
   * defaultParameterVal: &lt;name=foo,in=query&gt;: 3, &lt;name=bar,in=query&gt;: "c"
   * </pre>
   * Output:
   * <pre class="code">
   * { &lt;name=foo,in=query&gt;: 1, &lt;name=bar,in=query&gt;: "c"}
   * { &lt;name=foo,in=query&gt;: 2, &lt;name=bar,in=query&gt;: "c"}
   * { &lt;name=foo,in=query&gt;: 3, &lt;name=bar,in=query&gt;: "a"}
   * { &lt;name=foo,in=query&gt;: 3, &lt;name=bar,in=query&gt;: "b"}
   * </pre>
   *
   * @param parameterKeyParameterVals
   * @param defaultParameterVal
   * @return
   */
  private List<ParameterValPackage> permutate(Map<ParameterKey, List<ParameterVal>> parameterKeyParameterVals,
      Map<ParameterKey, ParameterVal> defaultParameterVal) {

    Set<ParameterValPackage> result = new HashSet<>(parameterKeyParameterVals.size());

    for (Map.Entry<ParameterKey, List<ParameterVal>> entry : parameterKeyParameterVals.entrySet()) {

      ParameterKey currentParameterKey = entry.getKey();
      List<ParameterVal> currentParameterVals = entry.getValue();

      for (ParameterVal currentParameterVal : currentParameterVals) {
        Map<ParameterKey, ParameterVal> map = new HashMap<>(defaultParameterVal);
        map.put(currentParameterKey, currentParameterVal);
        result.add(new ParameterValPackage(map.values()));
      }

    }

    return new ArrayList<>(result);

  }

  private class ParameterKey {

    private final String in;
    private final String name;

    public ParameterKey(Parameter parameter) {
      this.in = parameter.getIn();
      this.name = parameter.getName();
    }

    public ParameterKey(ParameterVal parameterVal) {
      this.in = parameterVal.getIn();
      this.name = parameterVal.getName();
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ParameterKey that = (ParameterKey) o;
      return Objects.equals(in, that.in) &&
          Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

      return Objects.hash(in, name);
    }
  }
}
