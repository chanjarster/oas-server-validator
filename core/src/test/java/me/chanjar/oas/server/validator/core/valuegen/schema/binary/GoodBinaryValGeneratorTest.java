package me.chanjar.oas.server.validator.core.valuegen.schema.binary;

import io.swagger.v3.oas.models.media.BinarySchema;
import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.BinaryVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValCons;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GoodBinaryValGeneratorTest {

  private GoodBinaryValGenerator generator = new GoodBinaryValGenerator();

  @DataProvider
  public Object[][] testSupportsData() {
    return new Object[][] {
        new Object[] { new IntegerSchema(), false },
        new Object[] { new BinarySchema(), true },
    };
  }

  @Test(dataProvider = "testSupportsData")
  public void testSupports(Schema schema, boolean expected) {
    assertEquals(generator.supports(schema, null), expected);
  }

  @Test
  public void testGenerate() {
    BinaryVal result = generator.generate(new BinarySchema(), null);
    assertEquals(result.isNull(), false);
  }
}
