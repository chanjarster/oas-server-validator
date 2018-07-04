package me.chanjar.openapiv3.mockmvc.parameters;

import io.swagger.v3.oas.models.parameters.Parameter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static me.chanjar.openapiv3.mockmvc.parameters.ParameterTestFactory.makeNew;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParametersFilterTest {

  private List<Parameter> provided;
  private List<Parameter> expected;

  public ParametersFilterTest(List<Parameter> provided, List<Parameter> expected) {
    this.provided = provided;
    this.expected = expected;
  }

  @Parameterized.Parameters(name = "{index}: {0}")
  public static Iterable<Object[]> data() {

    return Arrays.asList(
        new Object[] {
            Arrays.asList(makeNew("header", "Content-type"), makeNew("header", "Content-tYpe"), makeNew("heAder", "Content-tYpe")),
            Arrays.asList()
        },
        new Object[] {
            Arrays.asList(makeNew("header", "Accept"), makeNew("header", "AcCept"), makeNew("heaDer", "Accept")),
            Arrays.asList()
        },
        new Object[] {
            Arrays.asList(makeNew("header", "Authorization"), makeNew("header", "AuthorizAtion"), makeNew("headEr", "Authorization")),
            Arrays.asList()
        },
        new Object[] {
            Arrays.asList(makeNew("header", "Content-type"), makeNew("header", "Accept-Language")),
            Arrays.asList(makeNew("header", "Accept-Language"))
        }
    );

  }



  @Test
  public void filter() {

    ParametersFilter parametersFilter = new ParametersFilter();
    List<Parameter> result = parametersFilter.filter(provided);

    assertEquals(expected, result);
  }

}
