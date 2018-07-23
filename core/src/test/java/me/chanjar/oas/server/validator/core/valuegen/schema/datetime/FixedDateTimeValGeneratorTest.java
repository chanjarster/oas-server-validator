package me.chanjar.oas.server.validator.core.valuegen.schema.datetime;

import io.swagger.v3.oas.models.media.DateTimeSchema;
import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.DateTimeVal;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Date;

import static org.testng.Assert.assertEquals;

public class FixedDateTimeValGeneratorTest {

  private FixedDateTimeValGenerator generator = new FixedDateTimeValGenerator(new Date(10000000L));

  @DataProvider
  public Object[][] testSupportsData() {
    return new Object[][] {
        new Object[] { new DateTimeSchema(), true },
        new Object[] { new IntegerSchema(), false },
    };
  }

  @Test(dataProvider = "testSupportsData")
  public void testSupports(Schema schema, boolean expected) {
    assertEquals(generator.supports(schema), expected);
  }

  @Test
  public void testGenerate() {
    DateTimeVal result = generator.generate(new DateTimeSchema());
    assertEquals(result, new DateTimeVal(new Date(10000000L)));
  }
  
}
