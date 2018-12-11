package me.chanjar.oas.server.validator.core.encoder.schema;

import io.swagger.v3.oas.models.parameters.Parameter;
import me.chanjar.oas.server.validator.core.value.parameter.SerializeOption;
import me.chanjar.oas.server.validator.core.value.schema.ArrayVal;
import me.chanjar.oas.server.validator.core.value.schema.ObjectVal;
import me.chanjar.oas.server.validator.core.value.schema.PrimitiveVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static me.chanjar.oas.server.validator.core.encoder.schema.StyleExampleData.*;
import static org.testng.Assert.assertEquals;

public class DeepObjectStyleSchemaValEncoderTest {

  @DataProvider
  public Object[][] testEmptyData() {
    return new Object[][] {
        new Object[] { createSerializeOption(true, true), "" },
        new Object[] { createSerializeOption(true, false), "" },
        new Object[] { createSerializeOption(false, true), "" },
        new Object[] { createSerializeOption(false, false), "" }
    };
  }

  @Test(dataProvider = "testEmptyData")
  public void testEmpty(SerializeOption serializeOption, String expected) {

    DeepObjectStyleSchemaValEncoder encoder = new DeepObjectStyleSchemaValEncoder();
    String result = encoder.encode("color", SchemaVal.NULL_VAL, serializeOption);
    assertEquals(result, expected);
  }

  @DataProvider
  public Object[][] testEncodePrimitiveData() {

    return new Object[][] {

        new Object[] { binaryVal(), createSerializeOption(false, true), "" },
        new Object[] { binaryVal(), createSerializeOption(false, false), "" },

        new Object[] { booleanVal(), createSerializeOption(false, true), "" },
        new Object[] { booleanVal(), createSerializeOption(false, false), "" },

        new Object[] { byteArrayVal(), createSerializeOption(false, true), "" },
        new Object[] { byteArrayVal(), createSerializeOption(false, false), "" },

        new Object[] { dateTimeVal(), createSerializeOption(false, true), "" },
        new Object[] { dateTimeVal(), createSerializeOption(false, false), "" },

        new Object[] { dateVal(), createSerializeOption(false, true), "" },
        new Object[] { dateVal(), createSerializeOption(false, false), "" },

        new Object[] { emailVal(), createSerializeOption(false, true), "" },
        new Object[] { emailVal(), createSerializeOption(false, false), "" },

        new Object[] { integerVal(), createSerializeOption(false, true), "" },
        new Object[] { integerVal(), createSerializeOption(false, false), "" },

        new Object[] { numberVal(), createSerializeOption(false, true), "" },
        new Object[] { numberVal(), createSerializeOption(false, false), "" },

        new Object[] { passwordVal(), createSerializeOption(false, true), "" },
        new Object[] { passwordVal(), createSerializeOption(false, false), "" },

        new Object[] { stringVal(), createSerializeOption(false, true), "" },
        new Object[] { stringVal(), createSerializeOption(false, false), "" },

        new Object[] { uuidVal(), createSerializeOption(false, true), "" },
        new Object[] { uuidVal(), createSerializeOption(false, false), "" },


    };

  }

  @Test(dataProvider = "testEncodePrimitiveData")
  public void testEncodePrimitive(PrimitiveVal primitiveVal, SerializeOption serializeOption, String expected) {

    DeepObjectStyleSchemaValEncoder encoder = new DeepObjectStyleSchemaValEncoder();

    String result = encoder.encode("color", primitiveVal, serializeOption);

    assertEquals(result, expected);
  }

  @DataProvider
  public Object[][] testEncodeArrayData() {

    return new Object[][] {

        new Object[] { arrayVal(), createSerializeOption(false, true), "" },
        new Object[] { arrayVal(), createSerializeOption(false, false), "" },

        new Object[] { arrayValComplex(), createSerializeOption(false, true), "" },
        new Object[] { arrayValComplex(), createSerializeOption(false, false), "" },

    };

  }

  @Test(dataProvider = "testEncodeArrayData")
  public void testEncodeArray(ArrayVal arrayVal, SerializeOption serializeOption, String expected) {

    DeepObjectStyleSchemaValEncoder encoder = new DeepObjectStyleSchemaValEncoder();

    String result = encoder.encode("color", arrayVal, serializeOption);

    assertEquals(result, expected);

  }

  @DataProvider
  public Object[][] testEncodeObjectData() {

    return new Object[][] {
        new Object[] { objectVal(), createSerializeOption(false, true), "color[R]=100&color[G]=200&color[B]=150" },
        new Object[] { objectVal(), createSerializeOption(false, false), "color[R]=100&color[G]=200&color[B]=150" },

        new Object[] { objectValComplex(), createSerializeOption(false, true), "color[R]=100&color[G]=200&color[B]=150" },
        new Object[] { objectValComplex(), createSerializeOption(false, false), "color[R]=100&color[G]=200&color[B]=150" },

    };

  }

  @Test(dataProvider = "testEncodeObjectData")
  public void testEncodeObject(ObjectVal objectVal, SerializeOption serializeOption, String expected) {

    DeepObjectStyleSchemaValEncoder encoder = new DeepObjectStyleSchemaValEncoder();

    String result = encoder.encode("color", objectVal, serializeOption);

    assertEquals(result, expected);

  }

  private SerializeOption createSerializeOption(boolean exploded, boolean allowReserved) {
    return new SerializeOption(Parameter.StyleEnum.SIMPLE, exploded, allowReserved);
  }

  
}
