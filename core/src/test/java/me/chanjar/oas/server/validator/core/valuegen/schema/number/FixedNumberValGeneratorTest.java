package me.chanjar.oas.server.validator.core.valuegen.schema.number;

import io.swagger.v3.oas.models.media.NumberSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import me.chanjar.oas.server.validator.core.value.schema.NumberVal;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.assertEquals;

public class FixedNumberValGeneratorTest {

  private FixedNumberValGenerator generator = new FixedNumberValGenerator(BigDecimal.valueOf(1.1D));

  @DataProvider
  public Object[][] testSupportsData() {

    return new Object[][] {
        new Object[] { createSchema(null, null), true },
        new Object[] { createSchema(1, null), true },
        new Object[] { createSchema(null, 1), true },
        new Object[] { new StringSchema(), false },

    };

  }

  private NumberSchema createSchema(Integer minLength, Integer maxLength) {
    NumberSchema stringSchema = new NumberSchema();
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

    assertEquals(generator.generate(createSchema(null, null)), new NumberVal(BigDecimal.valueOf(1.1D)));
  }
  
}
