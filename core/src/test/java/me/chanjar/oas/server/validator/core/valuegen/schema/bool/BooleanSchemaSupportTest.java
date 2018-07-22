package me.chanjar.oas.server.validator.core.valuegen.schema.bool;

import io.swagger.v3.oas.models.media.BooleanSchema;
import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.Schema;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class BooleanSchemaSupportTest {

  @DataProvider
  public Object[][] testSupportsData() {
    return new Object[][] {
        new Object[] { new IntegerSchema(), false },
        new Object[] { new BooleanSchema(), true },
    };
  }

  @Test(dataProvider = "testSupportsData")
  public void testSupports(Schema schema, boolean expected) {
    assertEquals(BooleanSchemaSupport.supports(schema), expected);
  }

}
