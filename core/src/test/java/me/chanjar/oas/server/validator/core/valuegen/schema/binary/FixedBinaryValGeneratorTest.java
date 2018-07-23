package me.chanjar.oas.server.validator.core.valuegen.schema.binary;

import io.swagger.v3.oas.models.media.BinarySchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import me.chanjar.oas.server.validator.core.value.schema.BinaryVal;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FixedBinaryValGeneratorTest {

  private FixedBinaryValGenerator generator = new FixedBinaryValGenerator("fixedValue");

  @DataProvider
  public Object[][] testSupportsData() {

    return new Object[][] {
        new Object[] { new BinarySchema(), true },
        new Object[] { new StringSchema(), false },

    };

  }

  @Test(dataProvider = "testSupportsData")
  public void testSupports(Schema schema, boolean expected) {
    assertEquals(generator.supports(schema), expected);
  }

  @Test
  public void testGenerate() {

    assertEquals(generator.generate(new BinarySchema()), new BinaryVal("fixedValue"));
  }

}
