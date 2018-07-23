package me.chanjar.oas.server.validator.core.valuegen.schema.integer;

import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import me.chanjar.oas.server.validator.core.value.schema.IntegerVal;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.assertEquals;

public class FixedIntegerValGeneratorTest {

  private FixedIntegerValGenerator generator = new FixedIntegerValGenerator(1);

  @DataProvider
  public Object[][] testSupportsData() {

    return new Object[][] {
        new Object[] { createSchema(null, null), true },
        new Object[] { createSchema(1, null), true },
        new Object[] { createSchema(null, 1), true },
        new Object[] { new StringSchema(), false },

    };

  }

  private IntegerSchema createSchema(Integer minLength, Integer maxLength) {
    IntegerSchema stringSchema = new IntegerSchema();
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

    assertEquals(generator.generate(createSchema(null, null)), new IntegerVal(1));
  }
  
}
