package me.chanjar.oas.server.validator.core.valuegen.schema.uuid;

import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.UUIDSchema;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class UUIDSchemaSupportTest {

  @DataProvider
  public Object[][] testSupportsData() {
    return new Object[][] {
        new Object[] { new UUIDSchema(), true },
        new Object[] { new IntegerSchema(), false }
    };
  }

  @Test(dataProvider = "testSupportsData")
  public void testSupports(Schema schema, boolean expected) {
    assertEquals(UUIDSchemaSupport.supports(schema), expected);
  }

}
