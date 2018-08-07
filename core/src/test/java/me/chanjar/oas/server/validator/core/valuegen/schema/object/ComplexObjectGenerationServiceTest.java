package me.chanjar.oas.server.validator.core.valuegen.schema.object;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import me.chanjar.oas.server.validator.core.loader.OpenAPIV3Loader;
import me.chanjar.oas.server.validator.core.value.schema.*;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValCons;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerationService;
import org.apache.commons.lang3.ArrayUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static me.chanjar.oas.server.validator.core.valuegen.schema.bool.BooleanValGenerationServiceFactory.fixedBool;
import static me.chanjar.oas.server.validator.core.valuegen.schema.integer.IntegerValGenerationServiceFactory.fixedInteger;
import static me.chanjar.oas.server.validator.core.valuegen.schema.object.ObjectValGenerationServiceFactory.goodObject;
import static me.chanjar.oas.server.validator.core.valuegen.schema.string.StringValGenerationServiceFactory.fixedString;
import static org.testng.Assert.*;

public class ComplexObjectGenerationServiceTest {

  @Test
  public void testSupports() {
    ComplexObjectValGenerationService service = new ComplexObjectValGenerationService(
        "DefaultObjectValGenerationService");
    assertEquals(service.supports(new ObjectSchema()), true);
    assertEquals(service.supports(new StringSchema()), false);
  }

  @Test
  public void testGenerateOne_case_empty_properties_required_not_nullable() {
    ObjectSchema objectSchema = loadObjectSchema("case-empty-properties.yaml");

    ObjectValGenerationService service = goodObject();
    SchemaVal schemaVal = service.generateOne(objectSchema, new SchemaValCons(true, false));
    assertNull(schemaVal);
  }

  @Test
  public void testGenerateAll_case_empty_properties_required_not_nullable() {
    ObjectSchema objectSchema = loadObjectSchema("case-empty-properties.yaml");

    ObjectValGenerationService service = goodObject();
    List<SchemaVal> schemaVals = service.generateAll(objectSchema, new SchemaValCons(true, false));
    assertEquals(schemaVals, Collections.emptyList());
  }

  @Test
  public void testGenerateOne_case_empty_properties_not_required_not_nullable() {
    ObjectSchema objectSchema = loadObjectSchema("case-empty-properties.yaml");

    ObjectValGenerationService service = goodObject();
    SchemaVal schemaVal = service.generateOne(objectSchema, new SchemaValCons(false, false));
    assertNotNull(schemaVal);
    assertEquals(schemaVal, SchemaVal.IGNORED_VAL);
  }

  @Test
  public void testGenerateAll_case_empty_properties_not_required_not_nullable() {
    ObjectSchema objectSchema = loadObjectSchema("case-empty-properties.yaml");

    ObjectValGenerationService service = goodObject();
    List<SchemaVal> schemaVals = service.generateAll(objectSchema, new SchemaValCons(false, false));
    assertEquals(schemaVals, Arrays.asList(SchemaVal.IGNORED_VAL));
  }

  @Test
  public void testGenerateOne_case_empty_properties_required_nullable() {
    ObjectSchema objectSchema = loadObjectSchema("case-empty-properties.yaml");

    ObjectValGenerationService service = goodObject();
    SchemaVal schemaVal = service.generateOne(objectSchema, new SchemaValCons(true, true));
    assertEquals(schemaVal, SchemaVal.NULL_VAL);
  }

  @Test
  public void testGenerateAll_case_empty_properties_required_nullable() {
    ObjectSchema objectSchema = loadObjectSchema("case-empty-properties.yaml");

    ObjectValGenerationService service = goodObject();
    List<SchemaVal> schemaVals = service.generateAll(objectSchema, new SchemaValCons(true, true));
    assertEquals(schemaVals, Arrays.asList(SchemaVal.NULL_VAL));
  }

  @Test
  public void testGenerateOne_case_empty_properties_not_required_nullable() {
    ObjectSchema objectSchema = loadObjectSchema("case-empty-properties.yaml");

    ObjectValGenerationService service = goodObject();
    SchemaVal schemaVal = service.generateOne(objectSchema, new SchemaValCons(false, true));
    assertNotNull(schemaVal);
    assertEquals(schemaVal, SchemaVal.IGNORED_VAL);
  }

  @Test
  public void testGenerateAll_case_empty_properties_not_required_nullable() {
    ObjectSchema objectSchema = loadObjectSchema("case-empty-properties.yaml");

    ObjectValGenerationService service = goodObject();
    List<SchemaVal> schemaVals = service.generateAll(objectSchema, new SchemaValCons(false, true));
    assertEquals(schemaVals, Arrays.asList(SchemaVal.IGNORED_VAL, SchemaVal.NULL_VAL));

  }

  @DataProvider
  public Object[][] case_one_property_data() {
    return new Object[][] {
        new Object[] { "case-one-property-binary.yaml", BinaryVal.class },
        new Object[] { "case-one-property-bool.yaml", BooleanVal.class },
        new Object[] { "case-one-property-bytearray.yaml", ByteArrayVal.class },
        new Object[] { "case-one-property-date.yaml", DateVal.class },
        new Object[] { "case-one-property-date-time.yaml", DateTimeVal.class },
        new Object[] { "case-one-property-email.yaml", EmailVal.class },
        new Object[] { "case-one-property-integer.yaml", IntegerVal.class },
        new Object[] { "case-one-property-number.yaml", NumberVal.class },
        new Object[] { "case-one-property-password.yaml", PasswordVal.class },
        new Object[] { "case-one-property-string.yaml", StringVal.class },
        new Object[] { "case-one-property-uuid.yaml", UUIDVal.class },
        new Object[] { "case-one-property-object.yaml", ObjectVal.class },
        new Object[] { "case-one-property-array.yaml", ArrayVal.class },
    };
  }

  @Test(dataProvider = "case_one_property_data")
  public void testGenerateOne_case_one_property(String filename, Class<? extends SchemaVal> propertyValClass) {
    ObjectSchema objectSchema = loadObjectSchema(filename);

    ObjectValGenerationService service = goodObject();
    ObjectVal objectVal = (ObjectVal) service.generateOne(objectSchema, new SchemaValCons(true, false));
    assertNotNull(objectVal);
    SchemaVal propertyVal = objectVal.getValue().get("prop");
    assertEquals(propertyVal.getClass(), propertyValClass);
  }

  @Test(dataProvider = "case_one_property_data")
  public void testGenerateAll_case_one_property(String filename, Class<? extends SchemaVal> propertyValClass) {
    ObjectSchema objectSchema = loadObjectSchema(filename);

    ObjectValGenerationService service = goodObject();
    List<SchemaVal> schemaVals = service.generateAll(objectSchema, new SchemaValCons(true, false));
    assertEquals(schemaVals.isEmpty(), false);
    for (SchemaVal schemaVal : schemaVals) {
      ObjectVal objectVal = (ObjectVal) schemaVal;
      SchemaVal propertyVal = objectVal.getValue().get("prop");
      assertEquals(propertyVal.getClass(), propertyValClass);
    }
  }

  @Test
  public void testGenerateOne_case_multiple_property() {
    ObjectSchema objectSchema = loadObjectSchema("case-multiple-property.yaml");

    ObjectValGenerationService service = goodObject();
    ObjectVal objectVal = (ObjectVal) service.generateOne(objectSchema, new SchemaValCons(true, false));

    assertEquals(objectVal.getValue().get("binaryVal").getClass(), BinaryVal.class);
    assertEquals(objectVal.getValue().get("booleanVal").getClass(), BooleanVal.class);
    assertEquals(objectVal.getValue().get("byteArrayVal").getClass(), ByteArrayVal.class);
    assertEquals(objectVal.getValue().get("dateVal").getClass(), DateVal.class);
    assertEquals(objectVal.getValue().get("dateTimeVal").getClass(), DateTimeVal.class);
    assertEquals(objectVal.getValue().get("emailVal").getClass(), EmailVal.class);
    assertEquals(objectVal.getValue().get("integerVal").getClass(), IntegerVal.class);
    assertEquals(objectVal.getValue().get("numberVal").getClass(), NumberVal.class);
    assertEquals(objectVal.getValue().get("passwordVal").getClass(), PasswordVal.class);
    assertEquals(objectVal.getValue().get("stringVal").getClass(), StringVal.class);
    assertEquals(objectVal.getValue().get("uuidVal").getClass(), UUIDVal.class);
    assertEquals(objectVal.getValue().get("arrayVal").getClass(), ArrayVal.class);
    assertEquals(objectVal.getValue().get("objectVal").getClass(), ObjectVal.class);
    assertEquals(((ObjectVal) objectVal.getValue().get("objectVal")).getValue().get("integerVal").getClass(),
        IntegerVal.class);

  }

  @Test
  public void testGenerateAll_case_multiple_property() {
    ObjectSchema objectSchema = loadObjectSchema("case-multiple-property.yaml");

    ObjectValGenerationService service = goodObject();
    List<SchemaVal> schemaVals = service.generateAll(objectSchema, new SchemaValCons(true, false));

    assertEquals(schemaVals.isEmpty(), false);

    for (SchemaVal schemaVal : schemaVals) {
      ObjectVal objectVal = (ObjectVal) schemaVal;

      assertEquals(objectVal.getValue().get("binaryVal").getClass(), BinaryVal.class);
      assertEquals(objectVal.getValue().get("booleanVal").getClass(), BooleanVal.class);
      assertEquals(objectVal.getValue().get("byteArrayVal").getClass(), ByteArrayVal.class);
      assertEquals(objectVal.getValue().get("dateVal").getClass(), DateVal.class);
      assertEquals(objectVal.getValue().get("dateTimeVal").getClass(), DateTimeVal.class);
      assertEquals(objectVal.getValue().get("emailVal").getClass(), EmailVal.class);
      assertEquals(objectVal.getValue().get("integerVal").getClass(), IntegerVal.class);
      assertEquals(objectVal.getValue().get("numberVal").getClass(), NumberVal.class);
      assertEquals(objectVal.getValue().get("passwordVal").getClass(), PasswordVal.class);
      assertEquals(objectVal.getValue().get("stringVal").getClass(), StringVal.class);
      assertEquals(objectVal.getValue().get("uuidVal").getClass(), UUIDVal.class);
      assertEquals(objectVal.getValue().get("arrayVal").getClass(), ArrayVal.class);
      assertEquals(objectVal.getValue().get("objectVal").getClass(), ObjectVal.class);
      assertEquals(((ObjectVal) objectVal.getValue().get("objectVal")).getValue().get("integerVal").getClass(),
          IntegerVal.class);

    }

  }

  @Test
  public void testGenerateOne_case_multiple_same_schema_property() {
    ObjectSchema objectSchema = loadObjectSchema("case-multiple-same-schema-property.yaml");

    ObjectValGenerationService service = goodObject();
    ObjectVal objectVal = (ObjectVal) service.generateOne(objectSchema, new SchemaValCons(true, false));

    assertEquals(objectVal.getValue().get("stringVal1").getClass(), StringVal.class);
    assertEquals(objectVal.getValue().get("stringVal2").getClass(), StringVal.class);
    assertEquals(objectVal.getValue().get("stringVal3").getClass(), StringVal.class);

  }

  @Test
  public void testGenerateAll_case_multiple_same_schema_property() {
    ObjectSchema objectSchema = loadObjectSchema("case-multiple-same-schema-property.yaml");

    ObjectValGenerationService service = goodObject();
    List<SchemaVal> schemaVals = service.generateAll(objectSchema, new SchemaValCons(true, false));

    assertEquals(schemaVals.isEmpty(), false);

    for (SchemaVal schemaVal : schemaVals) {
      ObjectVal objectVal = (ObjectVal) schemaVal;

      assertEquals(objectVal.getValue().get("stringVal1").getClass(), StringVal.class);
      assertEquals(objectVal.getValue().get("stringVal2").getClass(), StringVal.class);
      assertEquals(objectVal.getValue().get("stringVal3").getClass(), StringVal.class);

    }

  }

  /**
   * Input:
   * <pre class="code">
   * options vals: "stringVal": ["uvw", "xyz"], "integerVal": [2]
   * defaultVal: "stringVal": "abc", "integerVal": 1
   * </pre>
   * Output:
   * <pre class="code">
   * { "stringVal": "uvw", "integerVal": 1}
   * { "stringVal": "xyz", "integerVal": 1}
   * { "stringVal": "abc", "integerVal": 2}
   * </pre>
   */
  @Test
  public void testGenerateAll_algorithm_1() {

    ComplexObjectValGenerationService defaultService = new ComplexObjectValGenerationService("algo-test-default");
    defaultService.addPropertyGenerationServices(
        fixedString("abc", "def"),
        fixedInteger(1),
        defaultService
    );

    ComplexObjectValGenerationService testedService = new ComplexObjectValGenerationService("algo-test",
        defaultService);

    testedService.addPropertyGenerationServices(
        fixedString("uvw", "xyz"),
        fixedInteger(2),
        testedService
    );

    ObjectSchema objectSchema = loadObjectSchema("case-algorithm.yaml");
    List<SchemaVal> schemaVals = testedService.generateAll(objectSchema, new SchemaValCons(true, false));

    assertEquals(schemaVals.size(), 3);
    assertEquals(new HashSet<>(schemaVals), new HashSet<>(Arrays.asList(
        new ObjectVal(
            (Map) ArrayUtils.toMap(new Object[][] {
                new Object[] { "stringVal", new StringVal("uvw") },
                new Object[] { "integerVal", new IntegerVal(1) }
            })
        ),
        new ObjectVal(
            (Map) ArrayUtils.toMap(new Object[][] {
                new Object[] { "stringVal", new StringVal("xyz") },
                new Object[] { "integerVal", new IntegerVal(1) }
            })
        ),
        new ObjectVal(
            (Map) ArrayUtils.toMap(new Object[][] {
                new Object[] { "stringVal", new StringVal("abc") },
                new Object[] { "integerVal", new IntegerVal(2) }
            })
        )
    )));

  }

  /**
   * Input:
   * <pre class="code">
   * options vals: "stringVal": ["abc", "def"], "integerVal": [1]
   * defaultVal: "stringVal": "abc", "integerVal": 1
   * </pre>
   * Output:
   * <pre class="code">
   * { "stringVal": "abc", "integerVal": 1}
   * { "stringVal": "def", "integerVal": 1}
   * </pre>
   */
  @Test
  public void testGenerateAll_algorithm_2() {

    ComplexObjectValGenerationService testedService = new ComplexObjectValGenerationService("algo-test");

    testedService.addPropertyGenerationServices(
        fixedString("abc", "def"),
        fixedInteger(1),
        testedService
    );

    ObjectSchema objectSchema = loadObjectSchema("case-algorithm.yaml");
    List<SchemaVal> schemaVals = testedService.generateAll(objectSchema, new SchemaValCons(true, false));

    assertEquals(schemaVals.size(), 2);
    assertEquals(new HashSet<>(schemaVals), new HashSet<>(Arrays.asList(
        new ObjectVal(
            (Map) ArrayUtils.toMap(new Object[][] {
                new Object[] { "stringVal", new StringVal("abc") },
                new Object[] { "integerVal", new IntegerVal(1) }
            })
        ),
        new ObjectVal(
            (Map) ArrayUtils.toMap(new Object[][] {
                new Object[] { "stringVal", new StringVal("def") },
                new Object[] { "integerVal", new IntegerVal(1) }
            })
        )
    )));

  }

  @Test
  public void testGenerateOne_property_name_generation_service() {

    ObjectSchema objectSchema = loadObjectSchema("case-one-property-bool.yaml");

    ComplexObjectValGenerationService service = new ComplexObjectValGenerationService("default");
    service.addPropertyGenerationService(fixedBool(false));
    service.addPropertyGenerationService("prop", fixedBool(true));

    ObjectVal objectVal = (ObjectVal) service.generateOne(objectSchema, new SchemaValCons(true, false));
    assertEquals(objectVal.getValue().get("prop"), new BooleanVal(true));

  }

  @Test
  public void testGenerateAll_property_name_generation_service() {

    ObjectSchema objectSchema = loadObjectSchema("case-one-property-bool.yaml");

    ComplexObjectValGenerationService service = new ComplexObjectValGenerationService("default");
    service.addPropertyGenerationService(fixedBool(false));
    service.addPropertyGenerationService("prop", fixedBool(true, false));

    List<SchemaVal> schemaVals = service.generateAll(objectSchema, new SchemaValCons(true, false));

    assertEquals(new HashSet<>(schemaVals), new HashSet<>(Arrays.asList(
        new ObjectVal(
            (Map) ArrayUtils.toMap(new Object[][] {
                new Object[] { "prop", new BooleanVal(true) },
            })
        ),
        new ObjectVal(
            (Map) ArrayUtils.toMap(new Object[][] {
                new Object[] { "prop", new BooleanVal(false) }
            })
        )
    )));

  }

  @Test(expectedExceptions = ObjectValGenerationException.class, expectedExceptionsMessageRegExp = "No suitable SchemaValGenerationService for PropertySchema.*")
  public void testGenerateOne_no_suitable_generation_service_for_property() {

    ObjectSchema objectSchema = loadObjectSchema("case-one-property-bool.yaml");

    ObjectValGenerationService service = new ComplexObjectValGenerationService("exception-test");
    service.generateOne(objectSchema, new SchemaValCons(true, false));

  }

  @Test(expectedExceptions = ObjectValGenerationException.class, expectedExceptionsMessageRegExp = "No suitable SchemaValGenerationService for PropertySchema.*")
  public void testGenerateAll_no_suitable_generation_service_for_property() {

    ObjectSchema objectSchema = loadObjectSchema("case-one-property-bool.yaml");

    ObjectValGenerationService service = new ComplexObjectValGenerationService("exception-test");
    service.generateAll(objectSchema, new SchemaValCons(true, false));

  }

  @Test(expectedExceptions = ObjectValGenerationException.class, expectedExceptionsMessageRegExp = "Exception happens when generating property SchemaVal.*")
  public void testGenerateOne_property_generation_exception_raised() {

    ObjectSchema objectSchema = loadObjectSchema("case-one-property-bool.yaml");

    ComplexObjectValGenerationService service = new ComplexObjectValGenerationService("exception-test");
    service.addPropertyGenerationService(new ExceptionSchemaValGenerationService());
    service.generateOne(objectSchema, new SchemaValCons(true, false));

  }

  @Test(expectedExceptions = ObjectValGenerationException.class, expectedExceptionsMessageRegExp = "Exception happens when generating property SchemaVal.*")
  public void testGenerateAll_property_generation_exception_raised() {

    ObjectSchema objectSchema = loadObjectSchema("case-one-property-bool.yaml");

    ComplexObjectValGenerationService service = new ComplexObjectValGenerationService("exception-test");
    service.addPropertyGenerationService(new ExceptionSchemaValGenerationService());
    service.generateAll(objectSchema, new SchemaValCons(true, false));

  }

  private ObjectSchema loadObjectSchema(String schemaFile) {
    OpenAPI openAPI = OpenAPIV3Loader
        .loadFromClasspath("me/chanjar/oas/server/validator/core/valuegen/schema/object/" + schemaFile);
    return (ObjectSchema) openAPI.getComponents().getSchemas().get("TestObject");
  }

  private static class ExceptionSchemaValGenerationService implements SchemaValGenerationService {
    @Override
    public boolean supports(Schema schema) {
      return true;
    }

    @Override
    public SchemaVal generateOne(Schema schema, SchemaValCons cons) {
      throw new RuntimeException("exception from generateOne");
    }

    @Override
    public List<SchemaVal> generateAll(Schema schema, SchemaValCons cons) {
      throw new RuntimeException("exception from generateAll");
    }
  }

}
