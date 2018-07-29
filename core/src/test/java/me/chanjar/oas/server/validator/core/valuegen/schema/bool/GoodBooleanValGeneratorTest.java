package me.chanjar.oas.server.validator.core.valuegen.schema.bool;

import io.swagger.v3.oas.models.media.BooleanSchema;
import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.BooleanVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValCons;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;

public class GoodBooleanValGeneratorTest {

  private GoodBooleanValGenerator generator = new GoodBooleanValGenerator();

  @DataProvider
  public Object[][] testSupportsData() {
    return new Object[][] {
        new Object[] { new IntegerSchema(), false },
        new Object[] { new BooleanSchema(), true },
    };
  }

  @Test(dataProvider = "testSupportsData")
  public void testSupports(Schema schema, boolean expected) {
    assertEquals(generator.supports(schema, null), expected);
  }

  @Test
  public void testGenerate() {
    BooleanVal result = generator.generate(new BooleanSchema(), null);
    assertNotNull(result.getValue());
    assertNotEquals(result, SchemaVal.NULL_VAL);
  }

}
