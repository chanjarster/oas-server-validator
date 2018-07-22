package me.chanjar.oas.server.validator.core.valuegen.schema.number;

import io.swagger.v3.oas.models.media.NumberSchema;
import me.chanjar.oas.server.validator.core.value.schema.NumberVal;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.assertEquals;

public class BadNumberValGenerator1Test {

  private BadNumberValGenerator1 generator = new BadNumberValGenerator1();

  @DataProvider
  public Object[][] testSupportsData() {
    return
        new Object[][] {
            new Object[] { createSchema(null, null), false },
            new Object[] { createSchema(null, BigDecimal.ONE), false },
            new Object[] { createSchema(BigDecimal.ONE, null), true }
        };
  }

  @Test(dataProvider = "testSupportsData")
  public void testSupports(NumberSchema schema, boolean expected) {
    assertEquals(generator.supports(schema), expected);
  }

  @DataProvider
  public Object[][] testGenerateData() {
    return
        new Object[][] {

            new Object[] { createSchema2(BigDecimal.ONE, null, null), 0.9D },
            new Object[] { createSchema2(BigDecimal.ONE, true, null), 1D },
            new Object[] { createSchema2(BigDecimal.ONE, false, null), 0.9D },

            new Object[] { createSchema2(BigDecimal.ONE, null, BigDecimal.valueOf(2L)), 0D },
            new Object[] { createSchema2(BigDecimal.ONE, true, BigDecimal.valueOf(2L)), 0D },
            new Object[] { createSchema2(BigDecimal.ONE, false, BigDecimal.valueOf(2L)), 0D },

            new Object[] { createSchema2(BigDecimal.valueOf(3L), null, BigDecimal.valueOf(2L)), 2D },
            new Object[] { createSchema2(BigDecimal.valueOf(3L), true, BigDecimal.valueOf(2L)), 2D },
            new Object[] { createSchema2(BigDecimal.valueOf(3L), false, BigDecimal.valueOf(2L)), 2D },

            new Object[] { createSchema2(BigDecimal.valueOf(4L), null, BigDecimal.valueOf(2L)), 2D },
            new Object[] { createSchema2(BigDecimal.valueOf(4L), true, BigDecimal.valueOf(2L)), 4D },
            new Object[] { createSchema2(BigDecimal.valueOf(4L), false, BigDecimal.valueOf(2L)), 2D },

            new Object[] { createSchema2(BigDecimal.valueOf(5L), null, BigDecimal.valueOf(2L)), 4D },
            new Object[] { createSchema2(BigDecimal.valueOf(5L), true, BigDecimal.valueOf(2L)), 4D },
            new Object[] { createSchema2(BigDecimal.valueOf(5L), false, BigDecimal.valueOf(2L)), 4D },

        };
  }

  @Test(dataProvider = "testGenerateData")
  public void testGenerate(NumberSchema schema, Double expected) {
    assertEquals(generator.generate(schema).getValue().compareTo(new NumberVal(BigDecimal.valueOf(expected)).getValue()), 0);
  }

  private NumberSchema createSchema(BigDecimal min, BigDecimal max) {
    NumberSchema schema = new NumberSchema();
    schema.setMinimum(min);
    schema.setMaximum(max);
    return schema;
  }

  private NumberSchema createSchema2(BigDecimal min, Boolean exclusiveMin, BigDecimal multipleOf) {

    NumberSchema schema = new NumberSchema();
    schema.setMinimum(min);
    schema.setExclusiveMinimum(exclusiveMin);
    schema.setMultipleOf(multipleOf);
    return schema;

  }

}
