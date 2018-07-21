package me.chanjar.oas.server.validator.core.valuegen.schema.integer;

import io.swagger.v3.oas.models.media.IntegerSchema;
import me.chanjar.oas.server.validator.core.value.schema.IntegerVal;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.assertEquals;

public class GoodIntegerGenerator3Test {

  private GoodIntegerGenerator3 goodIntegerGenerator2 = new GoodIntegerGenerator3();

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
  public void testSupports(IntegerSchema schema, boolean expected) {
    assertEquals(goodIntegerGenerator2.supports(schema), expected);
  }

  @DataProvider
  public Object[][] testGenerateData() {
    return
        new Object[][] {
            new Object[] { createSchema2(BigDecimal.ONE, null, null), 1 },
            new Object[] { createSchema2(BigDecimal.ONE, true, null), 0 },
            new Object[] { createSchema2(BigDecimal.ONE, false, null), 1 },

            new Object[] { createSchema2(BigDecimal.ONE, null, BigDecimal.valueOf(2L)), 0 },
            new Object[] { createSchema2(BigDecimal.ONE, true, BigDecimal.valueOf(2L)), 0 },
            new Object[] { createSchema2(BigDecimal.ONE, false, BigDecimal.valueOf(2L)), 0 },

            new Object[] { createSchema2(BigDecimal.valueOf(2L), null, BigDecimal.valueOf(2L)), 2 },
            new Object[] { createSchema2(BigDecimal.valueOf(2L), true, BigDecimal.valueOf(2L)), 0 },
            new Object[] { createSchema2(BigDecimal.valueOf(2L), false, BigDecimal.valueOf(2L)), 2 },

            new Object[] { createSchema2(BigDecimal.valueOf(4L), null, BigDecimal.valueOf(2L)), 4 },
            new Object[] { createSchema2(BigDecimal.valueOf(4L), true, BigDecimal.valueOf(2L)), 2 },
            new Object[] { createSchema2(BigDecimal.valueOf(4L), false, BigDecimal.valueOf(2L)), 4 },

            new Object[] { createSchema2(BigDecimal.valueOf(4L), null, BigDecimal.valueOf(2.5D)), 0 },
            new Object[] { createSchema2(BigDecimal.valueOf(4L), true, BigDecimal.valueOf(2.5D)), 0 },
            new Object[] { createSchema2(BigDecimal.valueOf(4L), false, BigDecimal.valueOf(2.5D)), 0 },

            new Object[] { createSchema2(BigDecimal.ONE, null, BigDecimal.valueOf(0.5D)), 1 },
            new Object[] { createSchema2(BigDecimal.ONE, true, BigDecimal.valueOf(0.5D)), 0 },
            new Object[] { createSchema2(BigDecimal.ONE, false, BigDecimal.valueOf(0.5D)), 1 },

            new Object[] { createSchema2(BigDecimal.valueOf(5.5D), null, null), 5 },
            new Object[] { createSchema2(BigDecimal.valueOf(5.5D), true, null), 5 },
            new Object[] { createSchema2(BigDecimal.valueOf(5.5D), false, null), 5 },

            new Object[] { createSchema2(BigDecimal.valueOf(5.5D), null, BigDecimal.valueOf(2L)), 4 },
            new Object[] { createSchema2(BigDecimal.valueOf(5.5D), true, BigDecimal.valueOf(2L)), 4 },
            new Object[] { createSchema2(BigDecimal.valueOf(5.5D), false, BigDecimal.valueOf(2L)), 4 },

            new Object[] { createSchema2(BigDecimal.valueOf(5.5D), null, BigDecimal.valueOf(2.5D)), 5 },
            new Object[] { createSchema2(BigDecimal.valueOf(5.5D), true, BigDecimal.valueOf(2.5D)), 5 },
            new Object[] { createSchema2(BigDecimal.valueOf(5.5D), false, BigDecimal.valueOf(2.5D)), 5 },

            new Object[] { createSchema2(BigDecimal.valueOf(5.5D), null, BigDecimal.valueOf(0.5D)), 5 },
            new Object[] { createSchema2(BigDecimal.valueOf(5.5D), true, BigDecimal.valueOf(0.5D)), 5 },
            new Object[] { createSchema2(BigDecimal.valueOf(5.5D), false, BigDecimal.valueOf(0.5D)), 5 },

        };
  }

  @Test(dataProvider = "testGenerateData")
  public void testGenerate(IntegerSchema schema, int expected) {
    assertEquals(goodIntegerGenerator2.generate(schema), new IntegerVal(expected));
  }

  private IntegerSchema createSchema(BigDecimal min, BigDecimal max) {
    IntegerSchema schema = new IntegerSchema();
    schema.setMinimum(min);
    schema.setMaximum(max);
    return schema;
  }

  private IntegerSchema createSchema2(BigDecimal max, Boolean exclusiveMax, BigDecimal multipleOf) {

    IntegerSchema schema = new IntegerSchema();
    schema.setMaximum(max);
    schema.setExclusiveMaximum(exclusiveMax);
    schema.setMultipleOf(multipleOf);
    return schema;

  }

}
