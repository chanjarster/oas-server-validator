package me.chanjar.oas.server.validator.core.valuegen.schema.date;

import io.swagger.v3.oas.models.media.DateSchema;
import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.DateVal;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Date;

import static org.testng.Assert.assertEquals;

public class FixedDateValGeneratorTest {

  private FixedDateValGenerator generator = new FixedDateValGenerator(new Date(10000000L));

  @DataProvider
  public Object[][] testSupportsData() {
    return new Object[][] {
        new Object[] { new DateSchema(), true },
        new Object[] { new IntegerSchema(), false },
    };
  }

  @Test(dataProvider = "testSupportsData")
  public void testSupports(Schema schema, boolean expected) {
    assertEquals(generator.supports(schema), expected);
  }

  @Test
  public void testGenerate() {
    DateVal result = generator.generate(new DateSchema());
    assertEquals(result, new DateVal(new Date(10000000L)));
  }
  
}
