package me.chanjar.oas.server.validator.core.valuegen.schema.bool;

import io.swagger.v3.oas.models.media.BooleanSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import me.chanjar.oas.server.validator.core.value.schema.BooleanVal;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FixedBooleanValGeneratorTest {

  private FixedBooleanValGenerator generator = new FixedBooleanValGenerator(true);

  @DataProvider
  public Object[][] testSupportsData() {

    return new Object[][] {
        new Object[] { new BooleanSchema(), true },
        new Object[] { new StringSchema(), false },

    };

  }

  @Test(dataProvider = "testSupportsData")
  public void testSupports(Schema schema, boolean expected) {
    assertEquals(generator.supports(schema), expected);
  }

  @Test
  public void testGenerate() {

    assertEquals(generator.generate(new BooleanSchema()), new BooleanVal(true));
  }

}
