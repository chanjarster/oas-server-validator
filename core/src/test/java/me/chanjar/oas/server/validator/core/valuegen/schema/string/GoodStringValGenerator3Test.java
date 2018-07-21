package me.chanjar.oas.server.validator.core.valuegen.schema.string;

import io.swagger.v3.oas.models.media.StringSchema;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GoodStringValGenerator3Test {

  private GoodStringValGenerator3 generator = new GoodStringValGenerator3();

  @DataProvider
  public Object[][] testSupportsData() {

    return new Object[][] {
        new Object[] { createSchema(null, null), false },
        new Object[] { createSchema(null, 1), true },

        new Object[] { createSchema("pattern", null), false },
        new Object[] { createSchema("pattern", 1), false }
    };

  }

  private StringSchema createSchema(String pattern, Integer maxLength) {
    StringSchema stringSchema = new StringSchema();
    stringSchema.setPattern(pattern);
    stringSchema.setMaxLength(maxLength);
    return stringSchema;
  }

  @Test(dataProvider = "testSupportsData")
  public void testSupports(StringSchema schema, boolean expected) {
    assertEquals(generator.supports(schema), expected);
  }

  @Test
  public void testGenerate() {

    assertEquals(generator.generate(createSchema(null, 10)).getValue().length(), 10);
  }

}
