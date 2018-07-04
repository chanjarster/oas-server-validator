package me.chanjar.openapiv3.mockmvc.parameters;

import io.swagger.v3.oas.models.parameters.Parameter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static me.chanjar.openapiv3.mockmvc.parameters.ParameterTestFactory.makeNew;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class ParametersMergerTest {


  private List<Parameter> list1;
  private List<Parameter> list2;
  private List<Parameter> expected;

  public ParametersMergerTest(List<Parameter> list1, List<Parameter> list2,
      List<Parameter> expected) {
    this.list1 = list1;
    this.list2 = list2;
    this.expected = expected;
  }

  @Parameterized.Parameters(name = "{index}: {0} | {1}")
  public static Iterable<Object[]> data() {

    return Arrays.asList(
        new Object[] {
            Arrays.asList(makeNew("query", "a", "a1"), makeNew("query", "b", "b1")),
            Arrays.asList(),
            Arrays.asList(makeNew("query", "a", "a1"), makeNew("query", "b", "b1"))
        },
        new Object[] {
            Arrays.asList(makeNew("query", "a", "a1"), makeNew("query", "b", "b1")),
            Arrays.asList(makeNew("header", "a", "a2")),
            Arrays.asList(makeNew("query", "a", "a1"), makeNew("query", "b", "b1"), makeNew("header", "a", "a2"))
        },
        new Object[] {
            Arrays.asList(makeNew("query", "a", "a1"), makeNew("query", "b", "b1")),
            Arrays.asList(makeNew("query", "a", "a2")),
            Arrays.asList(makeNew("query", "a", "a2"), makeNew("query", "b", "b1"))
        },
        new Object[] {
            Arrays.asList(makeNew("query", "a", "a1"), makeNew("query", "a", "a2")),
            Arrays.asList(),
            Arrays.asList(makeNew("query", "a", "a2"))
        },
        new Object[] {
            Arrays.asList(),
            Arrays.asList(),
            Arrays.asList()
        }
    );

  }


  @Test
  public void testMerge() {
    ParametersMerger merger = new ParametersMerger();
    List<Parameter> result = merger.merge(list1, list2);
    assertEquals(expected, result);
  }


}
