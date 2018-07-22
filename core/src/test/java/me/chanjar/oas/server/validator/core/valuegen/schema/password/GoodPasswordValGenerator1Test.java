package me.chanjar.oas.server.validator.core.valuegen.schema.password;

import io.swagger.v3.oas.models.media.PasswordSchema;
import me.chanjar.oas.server.validator.core.value.schema.PasswordVal;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GoodPasswordValGenerator1Test {

  private GoodPasswordValGenerator1 generator = new GoodPasswordValGenerator1();

  @DataProvider
  public Object[][] testSupportsData() {

    return new Object[][] {
        new Object[] { createSchema(null, null), true },
        new Object[] { createSchema(1, null), false },
        new Object[] { createSchema(null, 1), false },

    };

  }

  private PasswordSchema createSchema(Integer minLength, Integer maxLength) {
    PasswordSchema stringSchema = new PasswordSchema();
    stringSchema.setMinLength(minLength);
    stringSchema.setMaxLength(maxLength);
    return stringSchema;
  }

  @Test(dataProvider = "testSupportsData")
  public void testSupports(PasswordSchema schema, boolean expected) {
    assertEquals(generator.supports(schema), expected);
  }

  @Test
  public void testGenerate() {

    assertEquals(generator.generate(createSchema(null, null)), new PasswordVal("password"));
  }

}
