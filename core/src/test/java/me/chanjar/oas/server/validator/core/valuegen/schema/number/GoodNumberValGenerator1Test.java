package me.chanjar.oas.server.validator.core.valuegen.schema.number;

import io.swagger.v3.oas.models.media.NumberSchema;
import me.chanjar.oas.server.validator.core.value.schema.NumberVal;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.assertEquals;

public class GoodNumberValGenerator1Test {

  private GoodNumberValGenerator1 generator = new GoodNumberValGenerator1();

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
  public void testSupports(NumberSchema schema, boolean expected) {
    assertEquals(generator.supports(schema), expected);
  }

  @DataProvider
  public Object[][] testGenerateData() {
    return
        new Object[][] {
            new Object[] { createSchema2(BigDecimal.ONE), 0D },
            new Object[] { createSchema2(BigDecimal.valueOf(0.5D)), 0D },
            new Object[] { createSchema2(BigDecimal.valueOf(2.5D)), 0D }
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

  private NumberSchema createSchema2(BigDecimal multipleOf) {

    NumberSchema schema = new NumberSchema();
    schema.setMultipleOf(multipleOf);
    return schema;

  }

}
