package me.chanjar.oas.server.validator.core.valuegen.schema.bytearray;

import io.swagger.v3.oas.models.media.ByteArraySchema;
import me.chanjar.oas.server.validator.core.value.schema.ByteArrayVal;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class GoodByteArrayValGeneratorTest {

  private GoodByteArrayValGenerator generator = new GoodByteArrayValGenerator();

  @Test
  public void testGenerate() {
    ByteArrayVal result = generator.generate(new ByteArraySchema());
    assertNotNull(result.getValue());
  }

}
