package me.chanjar.oas.server.validator.core.valuegen.schema.password;

import io.swagger.v3.oas.models.media.PasswordSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import me.chanjar.oas.server.validator.core.value.schema.PasswordVal;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FixedPasswordValGeneratorTest {

  private FixedPasswordValGenerator generator = new FixedPasswordValGenerator("fixedValue");

  @DataProvider
  public Object[][] testSupportsData() {

    return new Object[][] {
        new Object[] { createSchema(null, null), true },
        new Object[] { createSchema(1, null), true },
        new Object[] { createSchema(null, 1), true },
        new Object[] { new StringSchema(), false },

    };

  }

  private PasswordSchema createSchema(Integer minLength, Integer maxLength) {
    PasswordSchema stringSchema = new PasswordSchema();
    stringSchema.setMinLength(minLength);
    stringSchema.setMaxLength(maxLength);
    return stringSchema;
  }

  @Test(dataProvider = "testSupportsData")
  public void testSupports(Schema schema, boolean expected) {
    assertEquals(generator.supports(schema), expected);
  }

  @Test
  public void testGenerate() {

    assertEquals(generator.generate(createSchema(null, null)), new PasswordVal("fixedValue"));
  }
  
}
