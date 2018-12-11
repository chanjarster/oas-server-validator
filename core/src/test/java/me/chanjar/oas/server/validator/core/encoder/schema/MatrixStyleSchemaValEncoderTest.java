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
import static me.chanjar.oas.server.validator.core.encoder.schema.StyleExampleData.objectVal;
import static me.chanjar.oas.server.validator.core.encoder.schema.StyleExampleData.objectValComplex;
import static org.testng.Assert.*;

public class MatrixStyleSchemaValEncoderTest {

  @DataProvider
  public Object[][] testEmptyData() {
    return new Object[][] {
        new Object[] { createSerializeOption(true, true), ";color" },
        new Object[] { createSerializeOption(true, false), ";color" },
        new Object[] { createSerializeOption(false, true), ";color" },
        new Object[] { createSerializeOption(false, false), ";color" }
    };
  }

  @Test(dataProvider = "testEmptyData")
  public void testEmpty(SerializeOption serializeOption, String expected) {

    MatrixStyleSchemaValEncoder encoder = new MatrixStyleSchemaValEncoder();
    String result = encoder.encode("color", SchemaVal.NULL_VAL, serializeOption);
    assertEquals(result, expected);
  }

  @DataProvider
  public Object[][] testEncodePrimitiveData() {

    return new Object[][] {

        new Object[] { binaryVal(), createSerializeOption(true, true), ";color=blue" },
        new Object[] { binaryVal(), createSerializeOption(true, false), ";color=blue" },
        new Object[] { binaryVal(), createSerializeOption(false, true), ";color=blue" },
        new Object[] { binaryVal(), createSerializeOption(false, false), ";color=blue" },

        new Object[] { booleanVal(), createSerializeOption(true, true), ";color=true" },
        new Object[] { booleanVal(), createSerializeOption(true, false), ";color=true" },
        new Object[] { booleanVal(), createSerializeOption(false, true), ";color=true" },
        new Object[] { booleanVal(), createSerializeOption(false, false), ";color=true" },

        new Object[] { byteArrayVal(), createSerializeOption(true, true), ";color=AgQGCA==" },
        new Object[] { byteArrayVal(), createSerializeOption(true, false), ";color=AgQGCA%3D%3D" },
        new Object[] { byteArrayVal(), createSerializeOption(false, true), ";color=AgQGCA==" },
        new Object[] { byteArrayVal(), createSerializeOption(false, false), ";color=AgQGCA%3D%3D" },

        new Object[] { dateTimeVal(), createSerializeOption(true, true), ";color=2018-12-10T16:24:15.000+08:00" },
        new Object[] { dateTimeVal(), createSerializeOption(true, false), ";color=2018-12-10T16%3A24%3A15.000%2B08%3A00" },
        new Object[] { dateTimeVal(), createSerializeOption(false, true), ";color=2018-12-10T16:24:15.000+08:00" },
        new Object[] { dateTimeVal(), createSerializeOption(false, false), ";color=2018-12-10T16%3A24%3A15.000%2B08%3A00" },

        new Object[] { dateVal(), createSerializeOption(true, true), ";color=2018-12-10" },
        new Object[] { dateVal(), createSerializeOption(true, false), ";color=2018-12-10" },
        new Object[] { dateVal(), createSerializeOption(false, true), ";color=2018-12-10" },
        new Object[] { dateVal(), createSerializeOption(false, false), ";color=2018-12-10" },

        new Object[] { emailVal(), createSerializeOption(true, true), ";color=blue" },
        new Object[] { emailVal(), createSerializeOption(true, false), ";color=blue" },
        new Object[] { emailVal(), createSerializeOption(false, true), ";color=blue" },
        new Object[] { emailVal(), createSerializeOption(false, false), ";color=blue" },

        new Object[] { integerVal(), createSerializeOption(true, true), ";color=100" },
        new Object[] { integerVal(), createSerializeOption(true, false), ";color=100" },
        new Object[] { integerVal(), createSerializeOption(false, true), ";color=100" },
        new Object[] { integerVal(), createSerializeOption(false, false), ";color=100" },

        new Object[] { numberVal(), createSerializeOption(true, true), ";color=1.1" },
        new Object[] { numberVal(), createSerializeOption(true, false), ";color=1.1" },
        new Object[] { numberVal(), createSerializeOption(false, true), ";color=1.1" },
        new Object[] { numberVal(), createSerializeOption(false, false), ";color=1.1" },

        new Object[] { passwordVal(), createSerializeOption(true, true), ";color=blue" },
        new Object[] { passwordVal(), createSerializeOption(true, false), ";color=blue" },
        new Object[] { passwordVal(), createSerializeOption(false, true), ";color=blue" },
        new Object[] { passwordVal(), createSerializeOption(false, false), ";color=blue" },

        new Object[] { stringVal(), createSerializeOption(true, true), ";color=blue" },
        new Object[] { stringVal(), createSerializeOption(true, false), ";color=blue" },
        new Object[] { stringVal(), createSerializeOption(false, true), ";color=blue" },
        new Object[] { stringVal(), createSerializeOption(false, false), ";color=blue" },

        new Object[] { uuidVal(), createSerializeOption(true, true), ";color=blue" },
        new Object[] { uuidVal(), createSerializeOption(true, false), ";color=blue" },
        new Object[] { uuidVal(), createSerializeOption(false, true), ";color=blue" },
        new Object[] { uuidVal(), createSerializeOption(false, false), ";color=blue" },


    };

  }

  @Test(dataProvider = "testEncodePrimitiveData")
  public void testEncodePrimitive(PrimitiveVal primitiveVal, SerializeOption serializeOption, String expected) {

    MatrixStyleSchemaValEncoder encoder = new MatrixStyleSchemaValEncoder();

    String result = encoder.encode("color", primitiveVal, serializeOption);

    assertEquals(result, expected);
  }

  @DataProvider
  public Object[][] testEncodeArrayData() {

    return new Object[][] {

        new Object[] { arrayVal(), createSerializeOption(true, true), ";color=blue;color=black;color=brown" },
        new Object[] { arrayVal(), createSerializeOption(true, false), ";color=blue;color=black;color=brown" },
        new Object[] { arrayVal(), createSerializeOption(false, true), ";color=blue,black,brown" },
        new Object[] { arrayVal(), createSerializeOption(false, false), ";color=blue,black,brown" },

        new Object[] { arrayValComplex(), createSerializeOption(true, true), ";color=blue;color=black;color=brown" },
        new Object[] { arrayValComplex(), createSerializeOption(true, false), ";color=blue;color=black;color=brown" },
        new Object[] { arrayValComplex(), createSerializeOption(false, true), ";color=blue,black,brown" },
        new Object[] { arrayValComplex(), createSerializeOption(false, false), ";color=blue,black,brown" },

    };

  }

  @Test(dataProvider = "testEncodeArrayData")
  public void testEncodeArray(ArrayVal arrayVal, SerializeOption serializeOption, String expected) {

    MatrixStyleSchemaValEncoder encoder = new MatrixStyleSchemaValEncoder();

    String result = encoder.encode("color", arrayVal, serializeOption);

    assertEquals(result, expected);

  }

  @DataProvider
  public Object[][] testEncodeObjectData() {

    return new Object[][] {
        new Object[] { objectVal(), createSerializeOption(true, true), ";R=100;G=200;B=150" },
        new Object[] { objectVal(), createSerializeOption(true, false), ";R=100;G=200;B=150" },
        new Object[] { objectVal(), createSerializeOption(false, true), ";color=R,100,G,200,B,150" },
        new Object[] { objectVal(), createSerializeOption(false, false), ";color=R,100,G,200,B,150" },

        new Object[] { objectValComplex(), createSerializeOption(true, true), ";R=100;G=200;B=150" },
        new Object[] { objectValComplex(), createSerializeOption(true, false), ";R=100;G=200;B=150" },
        new Object[] { objectValComplex(), createSerializeOption(false, true), ";color=R,100,G,200,B,150" },
        new Object[] { objectValComplex(), createSerializeOption(false, false), ";color=R,100,G,200,B,150" },

    };

  }

  @Test(dataProvider = "testEncodeObjectData")
  public void testEncodeObject(ObjectVal objectVal, SerializeOption serializeOption, String expected) {

    MatrixStyleSchemaValEncoder encoder = new MatrixStyleSchemaValEncoder();

    String result = encoder.encode("color", objectVal, serializeOption);

    assertEquals(result, expected);

  }

  private SerializeOption createSerializeOption(boolean exploded, boolean allowReserved) {
    return new SerializeOption(Parameter.StyleEnum.SIMPLE, exploded, allowReserved);
  }

  
}
