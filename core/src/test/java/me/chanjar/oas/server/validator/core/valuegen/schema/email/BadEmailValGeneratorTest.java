package me.chanjar.oas.server.validator.core.valuegen.schema.email;

import io.swagger.v3.oas.models.media.EmailSchema;
import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.EmailVal;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class BadEmailValGeneratorTest {

  @DataProvider
  public Object[][] testSupportsData() {

    return new Object[][] {
        new Object[] { new EmailSchema(), true },
        new Object[] { new IntegerSchema(), false }
    };

  }

  @Test(dataProvider = "testSupportsData")
  public void testSupports(Schema schema, boolean expected) {
    BadEmailValGenerator generator = new BadEmailValGenerator("abc.com");
    assertEquals(generator.supports(schema), expected);
  }

  @Test
  public void testGenerate() {
    BadEmailValGenerator generator = new BadEmailValGenerator("abc.com");
    EmailVal val = generator.generate(new EmailSchema());
    assertEquals(val.getValue(), "abc.com");
  }

}
