package me.chanjar.oas.server.validator.core.valuegen.schema.array;

import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class RightSizeArrayValGenerator2Test extends ArrayValGeneratorTestBase {

  @DataProvider
  public Object[][] testSupportsData() {

    return new Object[][] {
        new Object[] { createArraySchema(null, null), false },
        new Object[] { createArraySchema(1, null), true },
        new Object[] { createArraySchema(null, 1), false },
        new Object[] { createArraySchema(1, 1), true },
        new Object[] { new StringSchema(), false },
    };

  }

  @Test(dataProvider = "testSupportsData")
  public void testSupports(Schema schema, boolean expected) {
    ArrayValGeneratorTemplate generator = createGenerator();
    assertEquals(generator.supports(schema, null), expected);
  }

  @Override
  protected Integer getMinItems() {
    return 2;
  }

  @Override
  protected Integer getMaxItems() {
    return null;
  }

  @Override
  protected ArrayValGeneratorTemplate createGenerator() {
    return new RightSizeArrayValGenerator2();
  }

  @Override
  protected int getExpectedArraySize(ArraySchema arraySchema) {
    return arraySchema.getMinItems();
  }

}
