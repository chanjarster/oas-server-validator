package me.chanjar.oas.server.validator.core.valuegen.schema.array;

import io.swagger.v3.oas.models.media.*;
import me.chanjar.oas.server.validator.core.value.schema.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collections;

import static me.chanjar.oas.server.validator.core.valuegen.schema.array.ArrayValGenerationServiceFactory.array;
import static me.chanjar.oas.server.validator.core.valuegen.schema.array.ArrayValGenerationServiceFactory.registerGoods;
import static me.chanjar.oas.server.validator.core.valuegen.schema.object.ObjectValGenerationServiceFactory.goodObject;
import static me.chanjar.oas.server.validator.core.valuegen.schema.string.StringValGenerationServiceFactory.goodString;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class RightSizeArrayValGenerator1Test {

  @DataProvider
  public Object[][] testSupportsData() {

    return new Object[][] {
        new Object[] { createArraySchema(null, null), true },
        new Object[] { createArraySchema(1, null), false },
        new Object[] { createArraySchema(null, 1), false },
        new Object[] { createArraySchema(1, 1), false },
        new Object[] { new StringSchema(), false },
    };

  }

  @Test(dataProvider = "testSupportsData")
  public void testSupports(Schema schema, boolean expected) {
    RightSizeArrayValGenerator1 generator = new RightSizeArrayValGenerator1();
    assertEquals(generator.supports(schema, null), expected);
  }

  @Test
  public void testCalcItemSize() {
    RightSizeArrayValGenerator1 generator = new RightSizeArrayValGenerator1();
    int itemSize = generator.calcItemSize(new ArraySchema());
    assertEquals(itemSize, 1);
  }

  @DataProvider
  public Object[][] testGenerateData() {

    return new Object[][] {
        new Object[] { new BinarySchema(), BinaryVal.class },
        new Object[] { new BooleanSchema(), BooleanVal.class },
        new Object[] { new ByteArraySchema(), ByteArrayVal.class },
        new Object[] { new DateSchema(), DateVal.class },
        new Object[] { new DateTimeSchema(), DateTimeVal.class },
        new Object[] { new EmailSchema(), EmailVal.class },
        new Object[] { new IntegerSchema(), IntegerVal.class },
        new Object[] { new NumberSchema(), NumberVal.class },
        new Object[] { new PasswordSchema(), PasswordVal.class },
        new Object[] { new StringSchema(), StringVal.class },
        new Object[] { new UUIDSchema(), UUIDVal.class },
        new Object[] { createArraySchema(new StringSchema()), ArrayVal.class },
        new Object[] { createObjectSchema(new StringSchema()), ObjectVal.class },
        new Object[] { createObjectSchema(createArraySchema(new StringSchema())), ObjectVal.class },
    };
  }

  @Test(dataProvider = "testGenerateData")
  public void testGenerate(Schema itemSchema, Class<? extends SchemaVal> expectedSchemaClass) {

    RightSizeArrayValGenerator1 generator = new RightSizeArrayValGenerator1();

    ArrayValGenerationService arrayValGenerationService = array(generator);
    registerGoods(generator, arrayValGenerationService, goodObject());
    generator.addItemGenerationService(goodString());

    ArraySchema schema = createArraySchema(itemSchema);

    ArrayVal arrayVal = generator.generate(schema, null);

    assertEquals(arrayVal.getValue().length, 1);
    for (SchemaVal schemaVal : arrayVal.getValue()) {
      assertNotNull(schemaVal);
      assertEquals(schemaVal.getClass(), expectedSchemaClass);
    }
  }

  private ArraySchema createArraySchema(Integer minItems, Integer maxItems) {
    ArraySchema schema = new ArraySchema();
    schema.setMinItems(minItems);
    schema.setMaxItems(maxItems);
    return schema;
  }

  private ArraySchema createArraySchema(Schema itemSchema) {
    ArraySchema schema = new ArraySchema();
    schema.setItems(itemSchema);
    return schema;
  }

  private ObjectSchema createObjectSchema(Schema propertySchema) {
    ObjectSchema objectSchema = new ObjectSchema();
    objectSchema.setProperties(Collections.singletonMap("prop", propertySchema));
    return objectSchema;
  }
}
