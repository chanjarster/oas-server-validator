package me.chanjar.oas.server.validator.core.spec;

import io.swagger.v3.oas.models.parameters.Parameter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ParametersMergerTest {

  @DataProvider
  public static Object[][] data() {

    return new Object[][] {
        new Object[] {
            Arrays.asList(
                ParameterTestFactory.makeNew("query", "a", "a1"), ParameterTestFactory.makeNew("query", "b", "b1")),
            Arrays.asList(),
            Arrays.asList(
                ParameterTestFactory.makeNew("query", "a", "a1"), ParameterTestFactory.makeNew("query", "b", "b1"))
        },
        new Object[] {
            Arrays.asList(
                ParameterTestFactory.makeNew("query", "a", "a1"), ParameterTestFactory.makeNew("query", "b", "b1")),
            Arrays.asList(ParameterTestFactory.makeNew("header", "a", "a2")),
            Arrays.asList(
                ParameterTestFactory.makeNew("query", "a", "a1"), ParameterTestFactory.makeNew("query", "b", "b1"),
                ParameterTestFactory
                    .makeNew("header", "a", "a2"))
        },
        new Object[] {
            Arrays.asList(
                ParameterTestFactory.makeNew("query", "a", "a1"), ParameterTestFactory.makeNew("query", "b", "b1")),
            Arrays.asList(ParameterTestFactory.makeNew("query", "a", "a2")),
            Arrays.asList(
                ParameterTestFactory.makeNew("query", "a", "a2"), ParameterTestFactory.makeNew("query", "b", "b1"))
        },
        new Object[] {
            Arrays.asList(
                ParameterTestFactory.makeNew("query", "a", "a1"), ParameterTestFactory.makeNew("query", "a", "a2")),
            Arrays.asList(),
            Arrays.asList(ParameterTestFactory.makeNew("query", "a", "a2"))
        },
        new Object[] {
            Arrays.asList(),
            Arrays.asList(),
            Arrays.asList()
        }
    };

  }

  @Test(dataProvider = "data")
  public void testMerge(List<Parameter> list1, List<Parameter> list2, List<Parameter> expected) {
    ParametersMerger merger = new ParametersMerger();
    List<Parameter> result = merger.merge(list1, list2);
    assertEquals(expected, result);
  }

}
