package me.chanjar.oas.server.validator.core.valuegen.schema.special;

import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.IgnoredVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValCons;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class IgnoredValGeneratorTest {

  @DataProvider
  public Object[][] testSupportsGoodModeData() {
    return new Object[][] {
        new Object[] { new SchemaValCons(true, true), false },
        new Object[] { new SchemaValCons(true, false), false },
        new Object[] { new SchemaValCons(false, true), true },
        new Object[] { new SchemaValCons(false, false), true },
    };
  }

  @Test(dataProvider = "testSupportsGoodModeData")
  public void testSupportsGoodMode(SchemaValCons cons, boolean expected) {
    IgnoredValGenerator generator = new IgnoredValGenerator(true);
    assertEquals(generator.supports(new Schema(), cons), expected);
  }

  @DataProvider
  public Object[][] testSupportsBadModeData() {
    return new Object[][] {
        new Object[] { new SchemaValCons(true, true), true },
        new Object[] { new SchemaValCons(true, false), true },
        new Object[] { new SchemaValCons(false, true), false },
        new Object[] { new SchemaValCons(false, false), false },
    };
  }

  @Test(dataProvider = "testSupportsBadModeData")
  public void testSupportsBadMode(SchemaValCons cons, boolean expected) {
    IgnoredValGenerator generator = new IgnoredValGenerator(false);
    assertEquals(generator.supports(new Schema(), cons), expected);
  }

  @Test
  public void testGenerate() {
    IgnoredValGenerator generator = new IgnoredValGenerator(true);
    assertEquals(generator.generate(null, null), IgnoredVal.INSTANCE);
  }

}
