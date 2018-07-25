package me.chanjar.oas.server.validator.core.valuegen.schema.number;

import io.swagger.v3.oas.models.media.NumberSchema;
import me.chanjar.oas.server.validator.core.value.schema.NumberVal;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.assertEquals;

public class GoodNumberValGenerator3Test {

  private GoodNumberValGenerator3 generator = new GoodNumberValGenerator3();

  @DataProvider
  public Object[][] testSupportsData() {
    return
        new Object[][] {
            new Object[] { createSchema(null, null), false },
            new Object[] { createSchema(null, BigDecimal.ONE), true },
            new Object[] { createSchema(BigDecimal.ONE, null), false }
        };
  }

  @Test(dataProvider = "testSupportsData")
  public void testSupports(NumberSchema schema, boolean expected) {
    assertEquals(generator.supports(schema, null), expected);
  }

  @DataProvider
  public Object[][] testGenerateData() {
    return
        new Object[][] {
            new Object[] { createSchema2(BigDecimal.ONE, null, null), 1D },
            new Object[] { createSchema2(BigDecimal.ONE, true, null), 0.9D },
            new Object[] { createSchema2(BigDecimal.ONE, false, null), 1D },

            new Object[] { createSchema2(BigDecimal.ONE, null, BigDecimal.valueOf(2L)), 0D },
            new Object[] { createSchema2(BigDecimal.ONE, true, BigDecimal.valueOf(2L)), 0D },
            new Object[] { createSchema2(BigDecimal.ONE, false, BigDecimal.valueOf(2L)), 0D },

            new Object[] { createSchema2(BigDecimal.valueOf(2L), null, BigDecimal.valueOf(2L)), 2D },
            new Object[] { createSchema2(BigDecimal.valueOf(2L), true, BigDecimal.valueOf(2L)), 0D },
            new Object[] { createSchema2(BigDecimal.valueOf(2L), false, BigDecimal.valueOf(2L)), 2D },

            new Object[] { createSchema2(BigDecimal.ONE, null, BigDecimal.valueOf(0.5D)), 1D },
            new Object[] { createSchema2(BigDecimal.ONE, true, BigDecimal.valueOf(0.5D)), 0.5D },
            new Object[] { createSchema2(BigDecimal.ONE, false, BigDecimal.valueOf(0.5D)), 1D },

            new Object[] { createSchema2(BigDecimal.valueOf(2.5D), null, BigDecimal.valueOf(2.5D)), 2.5D },
            new Object[] { createSchema2(BigDecimal.valueOf(2.5D), true, BigDecimal.valueOf(2.5D)), 0D },
            new Object[] { createSchema2(BigDecimal.valueOf(2.5D), false, BigDecimal.valueOf(2.5D)), 2.5D },

            new Object[] { createSchema2(BigDecimal.valueOf(4D), null, BigDecimal.valueOf(2.5D)), 2.5D },
            new Object[] { createSchema2(BigDecimal.valueOf(4D), true, BigDecimal.valueOf(2.5D)), 2.5D },
            new Object[] { createSchema2(BigDecimal.valueOf(4D), false, BigDecimal.valueOf(2.5D)), 2.5D },
        };
  }

  @Test(dataProvider = "testGenerateData")
  public void testGenerate(NumberSchema schema, Double expected) {
    assertEquals(generator.generate(schema, null).getValue().compareTo(new NumberVal(BigDecimal.valueOf(expected)).getValue()), 0);
  }

  private NumberSchema createSchema(BigDecimal min, BigDecimal max) {
    NumberSchema schema = new NumberSchema();
    schema.setMinimum(min);
    schema.setMaximum(max);
    return schema;
  }

  private NumberSchema createSchema2(BigDecimal max, Boolean exclusiveMax, BigDecimal multipleOf) {

    NumberSchema schema = new NumberSchema();
    schema.setMaximum(max);
    schema.setExclusiveMaximum(exclusiveMax);
    schema.setMultipleOf(multipleOf);
    return schema;

  }

}
