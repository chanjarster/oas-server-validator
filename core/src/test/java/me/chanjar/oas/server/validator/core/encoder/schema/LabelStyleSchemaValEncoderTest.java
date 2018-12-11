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

public class LabelStyleSchemaValEncoderTest {

  @DataProvider
  public Object[][] testEmptyData() {
    return new Object[][] {
        new Object[] { createSerializeOption(true, true), "." },
        new Object[] { createSerializeOption(true, false), "." },
        new Object[] { createSerializeOption(false, true), "." },
        new Object[] { createSerializeOption(false, false), "." }
    };
  }

  @Test(dataProvider = "testEmptyData")
  public void testEmpty(SerializeOption serializeOption, String expected) {

    LabelStyleSchemaValEncoder encoder = new LabelStyleSchemaValEncoder();
    String result = encoder.encode("color", SchemaVal.NULL_VAL, serializeOption);
    assertEquals(result, expected);
  }

  @DataProvider
  public Object[][] testEncodePrimitiveData() {

    return new Object[][] {

        new Object[] { binaryVal(), createSerializeOption(true, true), ".blue" },
        new Object[] { binaryVal(), createSerializeOption(true, false), ".blue" },
        new Object[] { binaryVal(), createSerializeOption(false, true), ".blue" },
        new Object[] { binaryVal(), createSerializeOption(false, false), ".blue" },

        new Object[] { booleanVal(), createSerializeOption(true, true), ".true" },
        new Object[] { booleanVal(), createSerializeOption(true, false), ".true" },
        new Object[] { booleanVal(), createSerializeOption(false, true), ".true" },
        new Object[] { booleanVal(), createSerializeOption(false, false), ".true" },

        new Object[] { byteArrayVal(), createSerializeOption(true, true), ".AgQGCA==" },
        new Object[] { byteArrayVal(), createSerializeOption(true, false), ".AgQGCA%3D%3D" },
        new Object[] { byteArrayVal(), createSerializeOption(false, true), ".AgQGCA==" },
        new Object[] { byteArrayVal(), createSerializeOption(false, false), ".AgQGCA%3D%3D" },

        new Object[] { dateTimeVal(), createSerializeOption(true, true), ".2018-12-10T16:24:15.000+08:00" },
        new Object[] { dateTimeVal(), createSerializeOption(true, false), ".2018-12-10T16%3A24%3A15.000%2B08%3A00" },
        new Object[] { dateTimeVal(), createSerializeOption(false, true), ".2018-12-10T16:24:15.000+08:00" },
        new Object[] { dateTimeVal(), createSerializeOption(false, false), ".2018-12-10T16%3A24%3A15.000%2B08%3A00" },

        new Object[] { dateVal(), createSerializeOption(true, true), ".2018-12-10" },
        new Object[] { dateVal(), createSerializeOption(true, false), ".2018-12-10" },
        new Object[] { dateVal(), createSerializeOption(false, true), ".2018-12-10" },
        new Object[] { dateVal(), createSerializeOption(false, false), ".2018-12-10" },

        new Object[] { emailVal(), createSerializeOption(true, true), ".blue" },
        new Object[] { emailVal(), createSerializeOption(true, false), ".blue" },
        new Object[] { emailVal(), createSerializeOption(false, true), ".blue" },
        new Object[] { emailVal(), createSerializeOption(false, false), ".blue" },

        new Object[] { integerVal(), createSerializeOption(true, true), ".100" },
        new Object[] { integerVal(), createSerializeOption(true, false), ".100" },
        new Object[] { integerVal(), createSerializeOption(false, true), ".100" },
        new Object[] { integerVal(), createSerializeOption(false, false), ".100" },

        new Object[] { numberVal(), createSerializeOption(true, true), ".1.1" },
        new Object[] { numberVal(), createSerializeOption(true, false), ".1.1" },
        new Object[] { numberVal(), createSerializeOption(false, true), ".1.1" },
        new Object[] { numberVal(), createSerializeOption(false, false), ".1.1" },

        new Object[] { passwordVal(), createSerializeOption(true, true), ".blue" },
        new Object[] { passwordVal(), createSerializeOption(true, false), ".blue" },
        new Object[] { passwordVal(), createSerializeOption(false, true), ".blue" },
        new Object[] { passwordVal(), createSerializeOption(false, false), ".blue" },

        new Object[] { stringVal(), createSerializeOption(true, true), ".blue" },
        new Object[] { stringVal(), createSerializeOption(true, false), ".blue" },
        new Object[] { stringVal(), createSerializeOption(false, true), ".blue" },
        new Object[] { stringVal(), createSerializeOption(false, false), ".blue" },

        new Object[] { uuidVal(), createSerializeOption(true, true), ".blue" },
        new Object[] { uuidVal(), createSerializeOption(true, false), ".blue" },
        new Object[] { uuidVal(), createSerializeOption(false, true), ".blue" },
        new Object[] { uuidVal(), createSerializeOption(false, false), ".blue" },

    };

  }

  @Test(dataProvider = "testEncodePrimitiveData")
  public void testEncodePrimitive(PrimitiveVal primitiveVal, SerializeOption serializeOption, String expected) {

    LabelStyleSchemaValEncoder encoder = new LabelStyleSchemaValEncoder();

    String result = encoder.encode("color", primitiveVal, serializeOption);

    assertEquals(result, expected);
  }

  @DataProvider
  public Object[][] testEncodeArrayData() {

    return new Object[][] {

        new Object[] { arrayVal(), createSerializeOption(true, true), ".blue.black.brown" },
        new Object[] { arrayVal(), createSerializeOption(true, false), ".blue.black.brown" },
        new Object[] { arrayVal(), createSerializeOption(false, true), ".blue.black.brown" },
        new Object[] { arrayVal(), createSerializeOption(false, false), ".blue.black.brown" },

        new Object[] { arrayValComplex(), createSerializeOption(true, true), ".blue.black.brown" },
        new Object[] { arrayValComplex(), createSerializeOption(true, false), ".blue.black.brown" },
        new Object[] { arrayValComplex(), createSerializeOption(false, true), ".blue.black.brown" },
        new Object[] { arrayValComplex(), createSerializeOption(false, false), ".blue.black.brown" },

    };

  }

  @Test(dataProvider = "testEncodeArrayData")
  public void testEncodeArray(ArrayVal arrayVal, SerializeOption serializeOption, String expected) {

    LabelStyleSchemaValEncoder encoder = new LabelStyleSchemaValEncoder();

    String result = encoder.encode("color", arrayVal, serializeOption);

    assertEquals(result, expected);

  }

  @DataProvider
  public Object[][] testEncodeObjectData() {

    return new Object[][] {
        new Object[] { objectVal(), createSerializeOption(true, true), ".R=100.G=200.B=150" },
        new Object[] { objectVal(), createSerializeOption(true, false), ".R=100.G=200.B=150" },
        new Object[] { objectVal(), createSerializeOption(false, true), ".R.100.G.200.B.150" },
        new Object[] { objectVal(), createSerializeOption(false, false), ".R.100.G.200.B.150" },

        new Object[] { objectValComplex(), createSerializeOption(true, true), ".R=100.G=200.B=150" },
        new Object[] { objectValComplex(), createSerializeOption(true, false), ".R=100.G=200.B=150" },
        new Object[] { objectValComplex(), createSerializeOption(false, true), ".R.100.G.200.B.150" },
        new Object[] { objectValComplex(), createSerializeOption(false, false), ".R.100.G.200.B.150" },

    };

  }

  @Test(dataProvider = "testEncodeObjectData")
  public void testEncodeObject(ObjectVal objectVal, SerializeOption serializeOption, String expected) {

    LabelStyleSchemaValEncoder encoder = new LabelStyleSchemaValEncoder();

    String result = encoder.encode("color", objectVal, serializeOption);

    assertEquals(result, expected);

  }

  private SerializeOption createSerializeOption(boolean exploded, boolean allowReserved) {
    return new SerializeOption(Parameter.StyleEnum.SIMPLE, exploded, allowReserved);
  }

}
