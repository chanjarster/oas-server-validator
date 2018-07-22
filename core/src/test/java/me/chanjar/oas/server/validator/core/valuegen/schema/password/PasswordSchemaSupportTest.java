package me.chanjar.oas.server.validator.core.valuegen.schema.password;

import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.PasswordSchema;
import io.swagger.v3.oas.models.media.Schema;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PasswordSchemaSupportTest {

  @DataProvider
  public Object[][] testSupportsData() {
    return new Object[][] {
        new Object[] { new PasswordSchema(), true },
        new Object[] { new IntegerSchema(), false }
    };
  }

  @Test(dataProvider = "testSupportsData")
  public void testSupports(Schema schema, boolean expected) {
    assertEquals(PasswordSchemaSupport.supports(schema), expected);
  }

}
