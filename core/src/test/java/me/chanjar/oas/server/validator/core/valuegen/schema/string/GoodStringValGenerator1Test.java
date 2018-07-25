package me.chanjar.oas.server.validator.core.valuegen.schema.string;

import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import me.chanjar.oas.server.validator.core.value.schema.StringVal;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GoodStringValGenerator1Test {

  private GoodStringValGenerator1 generator = new GoodStringValGenerator1();

  @DataProvider
  public Object[][] testSupportsData() {

    return new Object[][] {
        new Object[] { createSchema(null, null, null), true },
        new Object[] { createSchema(null, 1, null), false },
        new Object[] { createSchema(null, null, 1), false },
        new Object[] { createSchema(null, 1, 1), false },

        new Object[] { createSchema("pattern", null, null), false },
        new Object[] { createSchema("pattern", 1, null), false },
        new Object[] { createSchema("pattern", null, 1), false },
        new Object[] { createSchema("pattern", 1, 1), false },
        new Object[] { new IntegerSchema(), false },
    };

  }

  private StringSchema createSchema(String pattern, Integer minLength, Integer maxLength) {
    StringSchema stringSchema = new StringSchema();
    stringSchema.setPattern(pattern);
    stringSchema.setMinLength(minLength);
    stringSchema.setMaxLength(maxLength);
    return stringSchema;
  }

  @Test(dataProvider = "testSupportsData")
  public void testSupports(Schema schema, boolean expected) {
    assertEquals(generator.supports(schema, null), expected);
  }

  @Test
  public void testGenerate() {

    assertEquals(generator.generate(createSchema(null, null, null), null), new StringVal("string"));
  }
}
