package me.chanjar.oas.server.validator.core.valuegen.schema;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import me.chanjar.oas.server.validator.core.value.schema.IgnoredVal;
import me.chanjar.oas.server.validator.core.value.schema.IntegerVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.value.schema.StringVal;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.testng.Assert.assertEquals;

public class PrimitiveSchemaValGenerationServiceTest {

  private TestValGenerator generator = new TestValGenerator();

  @Test
  public void testGenerateGood() {

    StringVal stringVal = generator.generateGood(new StringSchema());
    assertEquals(stringVal, new StringVal("string"));
  }

  @DataProvider
  public Object[][] testGenerateGoodsData() {

    return new Object[][] {
        new Object[] { true, true, true, asList(new StringVal("string"), new StringVal(null)) },
        new Object[] { true, true, false, asList(new StringVal("string"), new StringVal(null), new IntegerVal(0)) },
        new Object[] { true, false, false, asList(new StringVal("string"), new IntegerVal(0)) },
        new Object[] { false, false, false, asList(new StringVal("string"), IgnoredVal.INSTANCE, new IntegerVal(0)) },
        new Object[] { false, true, true, asList(new StringVal("string"), IgnoredVal.INSTANCE, new StringVal(null)) },
        new Object[] { false, false, true, asList(new StringVal("string"), IgnoredVal.INSTANCE) },
        new Object[] { true, false, true, asList(new StringVal("string")) }
    };

  }

  @Test(dataProvider = "testGenerateGoodsData")
  public void testGenerateGoods(boolean required, boolean nullable, boolean typeSensitive, List<SchemaVal> expected) {

    List<SchemaVal> result = generator.generateGoods(
        new StringSchema(), new SchemaValCons(required, nullable), typeSensitive);
    assertEquals(result.containsAll(expected), true);
    assertEquals(expected.containsAll(result), true);

  }

  @DataProvider
  public Object[][] testGenerateBadData() {

    return new Object[][] {
        new Object[] { true, true, true, asList(IgnoredVal.INSTANCE, new IntegerVal(0)) },
        new Object[] { true, true, false, asList(IgnoredVal.INSTANCE) },
        new Object[] { true, false, false, asList(IgnoredVal.INSTANCE, new StringVal(null)) },
        new Object[] { false, false, false, asList(new StringVal(null)) },
        new Object[] { false, true, true, asList(new IntegerVal(0)) },
        new Object[] { false, false, true, asList(new StringVal(null), new IntegerVal(0)) },
        new Object[] { true, false, true, asList(IgnoredVal.INSTANCE, new StringVal(null), new IntegerVal(0)) }
    };

  }

  @Test(dataProvider = "testGenerateBadData")
  public void testGenerateBad(boolean required, boolean nullable, boolean typeSensitive, List<SchemaVal> expected) {

    SchemaVal result = generator.generateBad(
        new StringSchema(), new SchemaValCons(required, nullable), typeSensitive
    );
    assertEquals(result, expected.get(0));
  }

  @DataProvider
  public Object[][] testGenerateBadsData() {

    return new Object[][] {
        new Object[] { true, true, true, asList(IgnoredVal.INSTANCE, new IntegerVal(0)) },
        new Object[] { true, true, false, asList(IgnoredVal.INSTANCE) },
        new Object[] { true, false, false, asList(IgnoredVal.INSTANCE, new StringVal(null)) },
        new Object[] { false, false, false, asList(new StringVal(null)) },
        new Object[] { false, true, true, asList(new IntegerVal(0)) },
        new Object[] { false, false, true, asList(new StringVal(null), new IntegerVal(0)) },
        new Object[] { true, false, true, asList(IgnoredVal.INSTANCE, new StringVal(null), new IntegerVal(0)) }
    };

  }

  @Test(dataProvider = "testGenerateBadsData")
  public void testGenerateBads(boolean required, boolean nullable, boolean typeSensitive, List<SchemaVal> expected) {

    List<SchemaVal> result = generator.generateBads(
        new StringSchema(), new SchemaValCons(required, nullable), typeSensitive);
    assertEquals(result.containsAll(expected), true);
    assertEquals(expected.containsAll(result), true);

  }

  public static class TestValGenerator extends PrimitiveSchemaValGenerationService<StringSchema, StringVal> {

    public TestValGenerator() {
      addGoodGenerator(
          new SchemaValGenerator<StringSchema, StringVal>() {

            @Override
            public boolean supports(Schema schema) {
              return true;
            }

            @Override
            public StringVal generate(StringSchema schema) {
              return new StringVal("string");
            }
          }
      );
    }

    @Override
    protected SchemaVal createDifferentTypeSchemaVal() {
      return new IntegerVal(0);
    }

    @Override
    protected StringVal createNullSchemaVal() {
      return new StringVal(null);
    }

    @Override
    protected Class<StringSchema> getSchemaValClass() {
      return StringSchema.class;
    }

    @Override
    public boolean supports(Schema schema) {
      return StringSchema.class.equals(schema.getClass());
    }
  }
}
