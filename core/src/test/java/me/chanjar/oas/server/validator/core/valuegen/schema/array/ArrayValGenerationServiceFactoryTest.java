package me.chanjar.oas.server.validator.core.valuegen.schema.array;

import io.swagger.v3.oas.models.media.ArraySchema;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.value.schema.StringVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValCons;
import org.testng.annotations.Test;

import java.util.List;

import static me.chanjar.oas.server.validator.core.valuegen.schema.array.ArrayValGenerationServiceFactory.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class ArrayValGenerationServiceFactoryTest {

  @Test
  public void testArray() {
    // TODO
  }

  @Test
  public void testGoodArray() {
    ArrayValGenerationService service = goodArray();
    assertNotNull(service);
  }

  @Test
  public void testGoodArrayWithoutObject() {
    ArrayValGenerationService service = goodArrayWithoutObject();
    assertNotNull(service);
  }

  @Test
  public void testBadArray() {
    ArrayValGenerationService service = badArray();
    assertNotNull(service);
  }

  @Test
  public void testBadArrayWithoutObject() {
    ArrayValGenerationService service = badArrayWithoutObject();
    assertNotNull(service);
  }

  @Test
  public void testFixedArray() {
    ArrayValGenerationService service = fixedArray(
        new StringVal[] { new StringVal("a"), new StringVal("b") },
        new SchemaVal[][] {
            new StringVal[] { new StringVal("c"), new StringVal("d") },
            new StringVal[] { new StringVal("e"), new StringVal("f") },
        }
    );
    List<SchemaVal> schemaVals = service.generateAll(new ArraySchema(), new SchemaValCons(true, false));
    assertEquals(schemaVals.size(), 3);
  }

  @Test
  public void testRegisterGoods() {
    // TODO
  }

  @Test
  public void testRegisterBads() {
    // TODO
  }
}
