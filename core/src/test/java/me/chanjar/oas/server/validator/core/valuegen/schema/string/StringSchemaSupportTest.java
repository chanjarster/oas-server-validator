package me.chanjar.oas.server.validator.core.valuegen.schema.string;

import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class StringSchemaSupportTest {

  @DataProvider
  public Object[][] testSupportsData() {
    return new Object[][] {
        new Object[] { createSchema(null), true },
        new Object[] { createSchema("pattern"), false },
        new Object[] { new IntegerSchema(), false }
    };
  }

  @Test(dataProvider = "testSupportsData")
  public void testSupports(Schema schema, boolean expected) {
    assertEquals(StringSchemaSupport.supports(schema), expected);
  }

  private StringSchema createSchema(String pattern) {
    StringSchema schema = new StringSchema();
    schema.setPattern(pattern);
    return schema;
  }

}
