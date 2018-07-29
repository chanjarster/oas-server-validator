package me.chanjar.oas.server.validator.core.valuegen.schema.bytearray;

import io.swagger.v3.oas.models.media.ByteArraySchema;
import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.ByteArrayVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;

public class GoodByteArrayValGeneratorTest {

  private GoodByteArrayValGenerator generator = new GoodByteArrayValGenerator();

  @DataProvider
  public Object[][] testSupportsData() {
    return new Object[][] {
        new Object[] { new IntegerSchema(), false },
        new Object[] { new ByteArraySchema(), true },
    };
  }

  @Test(dataProvider = "testSupportsData")
  public void testSupports(Schema schema, boolean expected) {
    assertEquals(generator.supports(schema, null), expected);
  }

  @Test
  public void testGenerate() {
    ByteArrayVal result = generator.generate(new ByteArraySchema(), null);
    assertNotNull(result.getValue());
    assertNotEquals(result, SchemaVal.NULL_VAL);
  }

}
