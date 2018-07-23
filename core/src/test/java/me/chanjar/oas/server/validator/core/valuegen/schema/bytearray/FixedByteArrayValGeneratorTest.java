package me.chanjar.oas.server.validator.core.valuegen.schema.bytearray;

import io.swagger.v3.oas.models.media.ByteArraySchema;
import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.ByteArrayVal;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FixedByteArrayValGeneratorTest {

  private FixedByteArrayValGenerator generator = new FixedByteArrayValGenerator(new Byte[] { 1, 2, 3 });

  @DataProvider
  public Object[][] testSupportsData() {
    return new Object[][] {
        new Object[] { new ByteArraySchema(), true },
        new Object[] { new IntegerSchema(), false },
    };
  }

  @Test(dataProvider = "testSupportsData")
  public void testSupports(Schema schema, boolean expected) {
    assertEquals(generator.supports(schema), expected);
  }

  @Test
  public void testGenerate() {
    ByteArrayVal result = generator.generate(new ByteArraySchema());
    assertEquals(result, new ByteArrayVal(new Byte[] { 1, 2, 3 }));
  }

}
