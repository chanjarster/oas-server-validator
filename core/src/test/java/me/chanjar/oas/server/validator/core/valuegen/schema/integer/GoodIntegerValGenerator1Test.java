package me.chanjar.oas.server.validator.core.valuegen.schema.integer;

import io.swagger.v3.oas.models.media.IntegerSchema;
import me.chanjar.oas.server.validator.core.value.schema.IntegerVal;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.assertEquals;

public class GoodIntegerValGenerator1Test {

  private GoodIntegerValGenerator1 generator = new GoodIntegerValGenerator1();

  @DataProvider
  public Object[][] testSupportsData() {
    return
        new Object[][] {
            new Object[] { createSchema(null, null), true },
            new Object[] { createSchema(null, BigDecimal.ONE), false },
            new Object[] { createSchema(BigDecimal.ONE, null), false }
        };
  }

  @Test(dataProvider = "testSupportsData")
  public void testSupports(IntegerSchema schema, boolean expected) {
    assertEquals(generator.supports(schema), expected);
  }

  @DataProvider
  public Object[][] testGenerateData() {
    return
        new Object[][] {
            new Object[] { createSchema2(BigDecimal.ONE), 0 },
            new Object[] { createSchema2(BigDecimal.valueOf(0.5D)), 0 },
            new Object[] { createSchema2(BigDecimal.valueOf(2.5D)), 0 }
        };
  }

  @Test(dataProvider = "testGenerateData")
  public void testGenerate(IntegerSchema schema, int expected) {
    assertEquals(generator.generate(schema), new IntegerVal(expected));
  }

  private IntegerSchema createSchema(BigDecimal min, BigDecimal max) {

    IntegerSchema schema = new IntegerSchema();
    schema.setMinimum(min);
    schema.setMaximum(max);
    return schema;

  }

  private IntegerSchema createSchema2(BigDecimal multipleOf) {

    IntegerSchema schema = new IntegerSchema();
    schema.setMultipleOf(multipleOf);
    return schema;

  }

}
