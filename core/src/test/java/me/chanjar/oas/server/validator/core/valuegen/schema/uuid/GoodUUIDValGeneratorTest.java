package me.chanjar.oas.server.validator.core.valuegen.schema.uuid;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.media.UUIDSchema;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GoodUUIDValGeneratorTest {

  private GoodUUIDValGenerator generator = new GoodUUIDValGenerator();

  @DataProvider
  public Object[][] testSupportsData() {

    return new Object[][] {
        new Object[] { new UUIDSchema(), true },
        new Object[] { new StringSchema(), false }
    };

  }

  @Test(dataProvider = "testSupportsData")
  public void testSupports(Schema schema, boolean expected) {
    assertEquals(generator.supports(schema, null), expected);
  }

  @Test
  public void testGenerate() {
    assertTrue(Pattern.matches("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}",
        generator.generate(new UUIDSchema(), null).getValue()));
  }

}
