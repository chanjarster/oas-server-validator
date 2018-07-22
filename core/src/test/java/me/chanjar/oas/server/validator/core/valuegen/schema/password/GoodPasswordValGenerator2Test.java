package me.chanjar.oas.server.validator.core.valuegen.schema.password;

import io.swagger.v3.oas.models.media.PasswordSchema;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GoodPasswordValGenerator2Test {

  private GoodPasswordValGenerator2 generator = new GoodPasswordValGenerator2();

  @DataProvider
  public Object[][] testSupportsData() {

    return new Object[][] {
        new Object[] { createSchema(null), false },
        new Object[] { createSchema(1), true },

    };

  }

  private PasswordSchema createSchema(Integer minLength) {
    PasswordSchema stringSchema = new PasswordSchema();
    stringSchema.setMinLength(minLength);
    return stringSchema;
  }

  @Test(dataProvider = "testSupportsData")
  public void testSupports(PasswordSchema schema, boolean expected) {
    assertEquals(generator.supports(schema), expected);
  }

  @Test
  public void testGenerate() {

    assertEquals(generator.generate(createSchema(10)).getValue().length(), 10);
  }

}
