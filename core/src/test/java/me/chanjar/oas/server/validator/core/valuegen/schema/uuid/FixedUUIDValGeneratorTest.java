package me.chanjar.oas.server.validator.core.valuegen.schema.uuid;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.media.UUIDSchema;
import me.chanjar.oas.server.validator.core.value.schema.UUIDVal;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FixedUUIDValGeneratorTest {

  private FixedUUIDValGenerator generator = new FixedUUIDValGenerator("fixedValue");

  @DataProvider
  public Object[][] testSupportsData() {

    return new Object[][] {
        new Object[] { createSchema(null, null), true },
        new Object[] { createSchema(1, null), true },
        new Object[] { createSchema(null, 1), true },
        new Object[] { new StringSchema(), false },

    };

  }

  private UUIDSchema createSchema(Integer minLength, Integer maxLength) {
    UUIDSchema stringSchema = new UUIDSchema();
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

    assertEquals(generator.generate(createSchema(null, null)), new UUIDVal("fixedValue"));
  }
  
}
