package me.chanjar.oas.server.validator.core.loader.spec;

import io.swagger.v3.oas.models.parameters.Parameter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ValidParametersFilterTest {

  @DataProvider
  public static Object[][] data() {

    return new Object[][] {
        new Object[] {
            Arrays.asList(ParameterTestFactory.makeNew("header", "Content-type"), ParameterTestFactory
                .makeNew("header", "Content-tYpe"), ParameterTestFactory.makeNew("heAder", "Content-tYpe")),
            Arrays.asList()
        },
        new Object[] {
            Arrays.asList(
                ParameterTestFactory.makeNew("header", "Accept"), ParameterTestFactory.makeNew("header", "AcCept"),
                ParameterTestFactory
                    .makeNew("heaDer", "Accept")),
            Arrays.asList()
        },
        new Object[] {
            Arrays.asList(ParameterTestFactory.makeNew("header", "Authorization"), ParameterTestFactory
                .makeNew("header", "AuthorizAtion"), ParameterTestFactory.makeNew("headEr", "Authorization")),
            Arrays.asList()
        },
        new Object[] {
            Arrays.asList(ParameterTestFactory.makeNew("header", "Content-type"), ParameterTestFactory
                .makeNew("header", "Accept-Language")),
            Arrays.asList(ParameterTestFactory.makeNew("header", "Accept-Language"))
        }
    };

  }



  @Test(dataProvider = "data")
  public void filter(List<Parameter> provided, List<Parameter> expected) {

    ValidParametersFilter parametersFilter = new ValidParametersFilter();
    List<Parameter> result = parametersFilter.filter(provided);

    assertEquals(expected, result);
  }

}
