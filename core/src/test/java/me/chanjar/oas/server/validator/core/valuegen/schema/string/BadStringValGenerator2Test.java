package me.chanjar.oas.server.validator.core.valuegen.schema.string;

import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class BadStringValGenerator2Test {

  private BadStringValGenerator2 generator = new BadStringValGenerator2();

  @DataProvider
  public Object[][] testSupportsData() {

    return new Object[][] {
        new Object[] { createSchema(null, null), false },
        new Object[] { createSchema(null, 1), true },

        new Object[] { createSchema("pattern", null), false },
        new Object[] { createSchema("pattern", 1), false },
        new Object[] { new IntegerSchema(), false },
    };

  }

  private StringSchema createSchema(String pattern, Integer maxLength) {
    StringSchema stringSchema = new StringSchema();
    stringSchema.setPattern(pattern);
    stringSchema.setMaxLength(maxLength);
    return stringSchema;
  }

  @Test(dataProvider = "testSupportsData")
  public void testSupports(Schema schema, boolean expected) {
    assertEquals(generator.supports(schema, null), expected);
  }

  @Test
  public void testGenerate() {

    assertEquals(generator.generate(createSchema(null, 10), null).getValue().length(), 11);
  }

}
