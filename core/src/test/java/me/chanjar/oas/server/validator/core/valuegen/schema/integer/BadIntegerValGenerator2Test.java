package me.chanjar.oas.server.validator.core.valuegen.schema.integer;

import io.swagger.v3.oas.models.media.IntegerSchema;
import me.chanjar.oas.server.validator.core.value.schema.IntegerVal;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.*;

public class BadIntegerValGenerator2Test {

  private BadIntegerValGenerator2 generator = new BadIntegerValGenerator2();

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
    assertEquals(generator.supports(schema, null), expected);
  }

  @DataProvider
  public Object[][] testGenerateData() {
    return
        new Object[][] {

            new Object[] { createSchema2(BigDecimal.ONE, null, null), 2 },
            new Object[] { createSchema2(BigDecimal.ONE, true, null), 1 },
            new Object[] { createSchema2(BigDecimal.ONE, false, null), 2 },

            new Object[] { createSchema2(BigDecimal.ONE, null, BigDecimal.valueOf(2L)), 2 },
            new Object[] { createSchema2(BigDecimal.ONE, true, BigDecimal.valueOf(2L)), 2 },
            new Object[] { createSchema2(BigDecimal.ONE, false, BigDecimal.valueOf(2L)), 2 },

            new Object[] { createSchema2(BigDecimal.valueOf(3L), null, BigDecimal.valueOf(2L)), 4 },
            new Object[] { createSchema2(BigDecimal.valueOf(3L), true, BigDecimal.valueOf(2L)), 4 },
            new Object[] { createSchema2(BigDecimal.valueOf(3L), false, BigDecimal.valueOf(2L)), 4 },

            new Object[] { createSchema2(BigDecimal.valueOf(4L), null, BigDecimal.valueOf(2L)), 6 },
            new Object[] { createSchema2(BigDecimal.valueOf(4L), true, BigDecimal.valueOf(2L)), 4 },
            new Object[] { createSchema2(BigDecimal.valueOf(4L), false, BigDecimal.valueOf(2L)), 6 },

            new Object[] { createSchema2(BigDecimal.valueOf(5L), null, BigDecimal.valueOf(2L)), 6 },
            new Object[] { createSchema2(BigDecimal.valueOf(5L), true, BigDecimal.valueOf(2L)), 6 },
            new Object[] { createSchema2(BigDecimal.valueOf(5L), false, BigDecimal.valueOf(2L)), 6 },

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

  private IntegerSchema createSchema2(BigDecimal max, Boolean exclusiveMax, BigDecimal multipleOf) {

    IntegerSchema schema = new IntegerSchema();
    schema.setMaximum(max);
    schema.setExclusiveMaximum(exclusiveMax);
    schema.setMultipleOf(multipleOf);
    return schema;

  }

}
