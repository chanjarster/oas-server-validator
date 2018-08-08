package me.chanjar.oas.server.validator.core.valuegen.schema.array;

import io.swagger.v3.oas.models.media.*;
import me.chanjar.oas.server.validator.core.value.schema.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collections;

import static me.chanjar.oas.server.validator.core.valuegen.schema.array.ArrayValGenerationServiceFactory.array;
import static me.chanjar.oas.server.validator.core.valuegen.schema.array.ArrayValGenerationServiceFactory.registerGoods;
import static me.chanjar.oas.server.validator.core.valuegen.schema.object.ObjectValGenerationServiceFactory.goodObject;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public abstract class ArrayValGeneratorTestBase {

  @Test
  public void testCalcItemSize() {
    ArrayValGeneratorTemplate generator = createGenerator();
    ArraySchema arraySchema = createArraySchema(getMinItems(), getMaxItems());
    int itemSize = generator.calcItemSize(arraySchema);
    assertEquals(itemSize, getExpectedArraySize(arraySchema));
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
        new Object[] { createArraySchema(new StringSchema(), getMinItems(), getMaxItems()), ArrayVal.class },
        new Object[] { createObjectSchema(new StringSchema()), ObjectVal.class },
        new Object[] { createObjectSchema(createArraySchema(new StringSchema(), getMinItems(), getMaxItems())), ObjectVal.class },
    };
  }

  @Test(dataProvider = "testGenerateData")
  public void testGenerate(Schema itemSchema, Class<? extends SchemaVal> expectedSchemaClass) {

    ArrayValGeneratorTemplate generator = createGenerator();

    ArrayValGenerationService arrayValGenerationService = array(generator);
    registerGoods(generator, arrayValGenerationService, goodObject());

    ArraySchema arraySchema = createArraySchema(itemSchema, getMinItems(), getMaxItems());

    ArrayVal arrayVal = generator.generate(arraySchema, null);

    assertEquals(arrayVal.getValue().length, getExpectedArraySize(arraySchema));
    for (SchemaVal schemaVal : arrayVal.getValue()) {
      assertNotNull(schemaVal);
      assertEquals(schemaVal.getClass(), expectedSchemaClass);
    }
  }

  @Test
  public void testGeneratedNestedArray() {

    ArrayValGeneratorTemplate generator = createGenerator();

    ArrayValGenerationService arrayValGenerationService = array(generator);
    registerGoods(generator, arrayValGenerationService, goodObject());

    Schema itemSchema = createArraySchema(new StringSchema(), getMinItems(), getMaxItems());
    ArraySchema arraySchema = createArraySchema(itemSchema, getMinItems(), getMaxItems());

    ArrayVal arrayVal = generator.generate(arraySchema, null);

    assertEquals(arrayVal.getValue().length, getExpectedArraySize(arraySchema));

    SchemaVal nestedArrayVal = arrayVal.getValue()[0];
    assertEquals(nestedArrayVal.getClass(), ArrayVal.class);
    assertEquals(((ArrayVal) nestedArrayVal).getValue()[0].getClass(), StringVal.class);

  }

  @Test
  public void testGeneratedObjectNestedArray() {

    ArrayValGeneratorTemplate generator = createGenerator();

    ArrayValGenerationService arrayValGenerationService = array(generator);
    registerGoods(generator, arrayValGenerationService, goodObject());

    Schema itemSchema = createObjectSchema(createArraySchema(new StringSchema(), getMinItems(), getMaxItems()));
    ArraySchema arraySchema = createArraySchema(itemSchema, getMinItems(), getMaxItems());

    ArrayVal arrayVal = generator.generate(arraySchema, null);

    assertEquals(arrayVal.getValue().length, getExpectedArraySize(arraySchema));

    assertEquals(arrayVal.getValue()[0].getClass(), ObjectVal.class);

    SchemaVal objectPropVal = ((ObjectVal) arrayVal.getValue()[0]).getValue().get("prop");
    assertEquals(objectPropVal.getClass(), ArrayVal.class);
    assertEquals(((ArrayVal) objectPropVal).getValue()[0].getClass(), StringVal.class);

  }


  protected ArraySchema createArraySchema(Integer minItems, Integer maxItems) {
    return createArraySchema(null, minItems, maxItems);
  }

  protected ArraySchema createArraySchema(Schema itemSchema, Integer minItems, Integer maxItems) {
    ArraySchema schema = new ArraySchema();
    schema.setItems(itemSchema);
    schema.setMinItems(minItems);
    schema.setMaxItems(maxItems);
    return schema;
  }

  private ObjectSchema createObjectSchema(Schema propertySchema) {
    ObjectSchema objectSchema = new ObjectSchema();
    objectSchema.setProperties(Collections.singletonMap("prop", propertySchema));
    return objectSchema;
  }

  protected abstract Integer getMinItems();

  protected abstract Integer getMaxItems();

  protected abstract ArrayValGeneratorTemplate createGenerator();

  protected abstract int getExpectedArraySize(ArraySchema arraySchema);
}
