package me.chanjar.oas.server.validator.core.valuegen.schema.integer;

import io.swagger.v3.oas.models.media.IntegerSchema;
import me.chanjar.oas.server.validator.core.value.schema.IntegerVal;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.assertEquals;

public class GoodIntegerValGenerator2Test {

  private GoodIntegerValGenerator2 generator = new GoodIntegerValGenerator2();

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
  public void testSupports(IntegerSchema schema, boolean expected) {
    assertEquals(generator.supports(schema, null), expected);
  }

  @DataProvider
  public Object[][] testGenerateData() {
    return
        new Object[][] {
            new Object[] { createSchema2(BigDecimal.ONE, null, null), 1 },
            new Object[] { createSchema2(BigDecimal.ONE, true, null), 2 },
            new Object[] { createSchema2(BigDecimal.ONE, false, null), 1 },

            new Object[] { createSchema2(BigDecimal.ONE, null, BigDecimal.valueOf(2L)), 2 },
            new Object[] { createSchema2(BigDecimal.ONE, true, BigDecimal.valueOf(2L)), 2 },
            new Object[] { createSchema2(BigDecimal.ONE, false, BigDecimal.valueOf(2L)), 2 },

            new Object[] { createSchema2(BigDecimal.valueOf(2L), null, BigDecimal.valueOf(2L)), 2 },
            new Object[] { createSchema2(BigDecimal.valueOf(2L), true, BigDecimal.valueOf(2L)), 4 },
            new Object[] { createSchema2(BigDecimal.valueOf(2L), false, BigDecimal.valueOf(2L)), 2 },

            new Object[] { createSchema2(BigDecimal.ONE, null, BigDecimal.valueOf(2.5D)), 5 },
            new Object[] { createSchema2(BigDecimal.ONE, true, BigDecimal.valueOf(2.5D)), 5 },
            new Object[] { createSchema2(BigDecimal.ONE, false, BigDecimal.valueOf(2.5D)), 5 },

            new Object[] { createSchema2(BigDecimal.ONE, null, BigDecimal.valueOf(0.5D)), 1 },
            new Object[] { createSchema2(BigDecimal.ONE, true, BigDecimal.valueOf(0.5D)), 2 },
            new Object[] { createSchema2(BigDecimal.ONE, false, BigDecimal.valueOf(0.5D)), 1 },

            new Object[] { createSchema2(BigDecimal.valueOf(0.5D), null, null), 1 },
            new Object[] { createSchema2(BigDecimal.valueOf(0.5D), true, null), 1 },
            new Object[] { createSchema2(BigDecimal.valueOf(0.5D), false, null), 1 },

            new Object[] { createSchema2(BigDecimal.valueOf(0.5D), null, BigDecimal.valueOf(2L)), 2 },
            new Object[] { createSchema2(BigDecimal.valueOf(0.5D), true, BigDecimal.valueOf(2L)), 2 },
            new Object[] { createSchema2(BigDecimal.valueOf(0.5D), false, BigDecimal.valueOf(2L)), 2 },

            new Object[] { createSchema2(BigDecimal.valueOf(0.5D), null, BigDecimal.valueOf(2.5D)), 5 },
            new Object[] { createSchema2(BigDecimal.valueOf(0.5D), true, BigDecimal.valueOf(2.5D)), 5 },
            new Object[] { createSchema2(BigDecimal.valueOf(0.5D), false, BigDecimal.valueOf(2.5D)), 5 },

            new Object[] { createSchema2(BigDecimal.valueOf(0.5D), null, BigDecimal.valueOf(0.5D)), 1 },
            new Object[] { createSchema2(BigDecimal.valueOf(0.5D), true, BigDecimal.valueOf(0.5D)), 1 },
            new Object[] { createSchema2(BigDecimal.valueOf(0.5D), false, BigDecimal.valueOf(0.5D)), 1 },

            new Object[] { createSchema2(BigDecimal.valueOf(2.5D), null, null), 3 },
            new Object[] { createSchema2(BigDecimal.valueOf(2.5D), true, null), 3 },
            new Object[] { createSchema2(BigDecimal.valueOf(2.5D), false, null), 3 },

            new Object[] { createSchema2(BigDecimal.valueOf(2.5D), null, BigDecimal.valueOf(2L)), 4 },
            new Object[] { createSchema2(BigDecimal.valueOf(2.5D), true, BigDecimal.valueOf(2L)), 4 },
            new Object[] { createSchema2(BigDecimal.valueOf(2.5D), false, BigDecimal.valueOf(2L)), 4 },

            new Object[] { createSchema2(BigDecimal.valueOf(2.5D), null, BigDecimal.valueOf(2.5D)), 5 },
            new Object[] { createSchema2(BigDecimal.valueOf(2.5D), true, BigDecimal.valueOf(2.5D)), 5 },
            new Object[] { createSchema2(BigDecimal.valueOf(2.5D), false, BigDecimal.valueOf(2.5D)), 5 },

            new Object[] { createSchema2(BigDecimal.valueOf(2.5D), null, BigDecimal.valueOf(0.5D)), 3 },
            new Object[] { createSchema2(BigDecimal.valueOf(2.5D), true, BigDecimal.valueOf(0.5D)), 3 },
            new Object[] { createSchema2(BigDecimal.valueOf(2.5D), false, BigDecimal.valueOf(0.5D)), 3 },

        };
  }

  @Test(dataProvider = "testGenerateData")
  public void testGenerate(IntegerSchema schema, int expected) {
    assertEquals(generator.generate(schema, null), new IntegerVal(expected));
  }

  private IntegerSchema createSchema(BigDecimal min, BigDecimal max) {
    IntegerSchema schema = new IntegerSchema();
    schema.setMinimum(min);
    schema.setMaximum(max);
    return schema;
  }

  private IntegerSchema createSchema2(BigDecimal min, Boolean exclusiveMin, BigDecimal multipleOf) {

    IntegerSchema schema = new IntegerSchema();
    schema.setMinimum(min);
    schema.setExclusiveMinimum(exclusiveMin);
    schema.setMultipleOf(multipleOf);
    return schema;

  }

}
