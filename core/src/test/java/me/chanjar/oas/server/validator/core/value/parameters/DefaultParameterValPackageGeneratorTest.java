package me.chanjar.oas.server.validator.core.value.parameters;

import io.swagger.v3.oas.models.parameters.Parameter;
import me.chanjar.oas.server.validator.core.value.parameter.ParameterVal;
import me.chanjar.oas.server.validator.core.value.parameter.ParameterValGenerator;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.value.schema.StringVal;
import org.testng.annotations.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.atIndex;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DefaultParameterValPackageGeneratorTest {

  @Test
  public void testGenerateOne() {

    ParameterValGenerator parameterValGenerator = mock(ParameterValGenerator.class);

    Parameter param1 = parameter("query", "foo");
    Parameter param2 = parameter("header", "foo");

    when(parameterValGenerator.generateOne(param1)).thenReturn(parameterVal(param1, new StringVal("abc")));
    when(parameterValGenerator.generateOne(param2)).thenReturn(parameterVal(param2, new StringVal("xyz")));

    ParameterValPackageGenerator generator = new DefaultParameterValPackageGenerator(parameterValGenerator);

    ParameterValPackage parameterValPackage = generator.generateOne(asList(param1, param2));

    assertThat(parameterValPackage.getQueryParameters()).hasSize(1);
    assertThat(parameterValPackage.getQueryParameters())
        .contains(parameterVal(param1, new StringVal("abc")), atIndex(0));

    assertThat(parameterValPackage.getHeaderParameters()).hasSize(1);
    assertThat(parameterValPackage.getHeaderParameters())
        .contains(parameterVal(param2, new StringVal("xyz")), atIndex(0));

    assertThat(parameterValPackage.getCookieParameters()).hasSize(0);
    assertThat(parameterValPackage.getPathParameters()).hasSize(0);

  }

  /**
   * Input:
   * <pre class="code">
   * parameterKeyParameterVals: &lt;name=foo,in=query&gt;: [abc, def], &lt;name=foo,in=header&gt;: [uvw, xyz]
   * defaultParameterVal: &lt;name=foo,in=query&gt;: abc, &lt;name=foo,in=header&gt;: xyz
   * </pre>
   * Output:
   * <pre class="code">
   * { &lt;name=foo,in=query&gt;: abc, &lt;name=foo,in=header&gt;: xyz}
   * { &lt;name=foo,in=query&gt;: def, &lt;name=foo,in=header&gt;: xyz}
   * { &lt;name=foo,in=query&gt;: abc, &lt;name=foo,in=header&gt;: uvw}
   * </pre>
   */
  @Test
  public void testGenerateAll() {

    ParameterValGenerator parameterValGenerator = mock(ParameterValGenerator.class);

    Parameter param1 = parameter("query", "foo");
    Parameter param2 = parameter("header", "foo");

    when(parameterValGenerator.generateOne(param1)).thenReturn(parameterVal(param1, new StringVal("abc")));
    when(parameterValGenerator.generateOne(param2)).thenReturn(parameterVal(param2, new StringVal("xyz")));

    when(parameterValGenerator.generateAll(param1)).thenReturn(
        asList(
            parameterVal(param1, new StringVal("abc")),
            parameterVal(param1, new StringVal("def"))
        )
    );
    when(parameterValGenerator.generateAll(param2)).thenReturn(
        asList(
            parameterVal(param2, new StringVal("uvw")),
            parameterVal(param2, new StringVal("xyz"))
        )
    );

    ParameterValPackageGenerator generator = new DefaultParameterValPackageGenerator(parameterValGenerator);

    List<ParameterValPackage> parameterValPackages = generator.generateAll(asList(param1, param2));
    assertThat(parameterValPackages).hasSize(3);

    assertThat(parameterValPackages).contains(
        new ParameterValPackage(
            asList(
                parameterVal(param1, new StringVal("abc")),
                parameterVal(param2, new StringVal("xyz"))
            )
        )
    );

    assertThat(parameterValPackages).contains(
        new ParameterValPackage(
            asList(
                parameterVal(param1, new StringVal("def")),
                parameterVal(param2, new StringVal("xyz"))
            )
        )
    );

    assertThat(parameterValPackages).contains(
        new ParameterValPackage(
            asList(
                parameterVal(param1, new StringVal("abc")),
                parameterVal(param2, new StringVal("uvw"))
            )
        )
    );

  }

  private Parameter parameter(String in, String name) {
    Parameter parameter = new Parameter();
    parameter.setIn(in);
    parameter.setName(name);

    parameter.setRequired(true);
    parameter.setAllowEmptyValue(false);

    parameter.setStyle(Parameter.StyleEnum.SIMPLE);
    parameter.setExplode(false);
    parameter.setAllowReserved(true);

    return parameter;
  }

  private ParameterVal parameterVal(Parameter parameter, SchemaVal schemaVal) {
    return new ParameterVal(parameter, schemaVal);
  }

}
