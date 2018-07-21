package me.chanjar.oas.server.validator.core.valuegen.schema.binary;

import io.swagger.v3.oas.models.media.BinarySchema;
import me.chanjar.oas.server.validator.core.value.schema.BinaryVal;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class GoodBinaryValGeneratorTest {

  private GoodBinaryValGenerator generator = new GoodBinaryValGenerator();

  @Test
  public void testGenerate() {
    BinaryVal result = generator.generate(new BinarySchema());
    assertNotNull(result.getValue());
  }
}
