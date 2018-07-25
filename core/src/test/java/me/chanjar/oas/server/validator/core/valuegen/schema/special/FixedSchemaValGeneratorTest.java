package me.chanjar.oas.server.validator.core.valuegen.schema.special;

import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.StringVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValCons;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FixedSchemaValGeneratorTest {

  @DataProvider
  public Object[][] testSupportsData() {
    return new Object[][] {
        new Object[] { new Schema(), new SchemaValCons(true, true), true },
        new Object[] { new Schema(), new SchemaValCons(true, false), true },
        new Object[] { new Schema(), new SchemaValCons(false, true), true },
        new Object[] { new Schema(), new SchemaValCons(false, false), true },
        new Object[] { new Schema(), null, true },

        new Object[] { null, new SchemaValCons(true, true), true },
        new Object[] { null, new SchemaValCons(true, false), true },
        new Object[] { null, new SchemaValCons(false, true), true },
        new Object[] { null, new SchemaValCons(false, false), true },
        new Object[] { null, null, true },
    };
  }

  @Test(dataProvider = "testSupportsData")
  public void testSupports(Schema schema, SchemaValCons cons, boolean expected) {
    FixedSchemaValGenerator generator = new FixedSchemaValGenerator(new StringVal("fixed string"));
    assertEquals(generator.supports(schema, cons), true);
  }

  @Test
  public void testGenerate() {
    FixedSchemaValGenerator generator = new FixedSchemaValGenerator(new StringVal("fixed string"));
    assertEquals(generator.generate(null, null), new StringVal("fixed string"));

  }
}
