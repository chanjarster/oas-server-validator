package me.chanjar.oas.server.validator.core.valuegen.schema.object;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.StringSchema;
import me.chanjar.oas.server.validator.core.loader.OpenAPIV3Loader;
import me.chanjar.oas.server.validator.core.value.schema.BooleanVal;
import me.chanjar.oas.server.validator.core.value.schema.ObjectVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValCons;
import org.apache.commons.lang3.ArrayUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static me.chanjar.oas.server.validator.core.valuegen.schema.object.ObjectValGenerationServiceFactory.fixedObject;
import static org.testng.Assert.assertEquals;

public class FixedObjectValGenerationServiceTest {

  @Test
  public void testSupports() {
    FixedObjectValGenerationService service = new FixedObjectValGenerationService();
    assertEquals(service.supports(new ObjectSchema()), true);
    assertEquals(service.supports(new StringSchema()), false);
  }

  @DataProvider
  public Object[][] schemaValConsData() {
    return new Object[][] {
        new Object[] { new SchemaValCons(true, true) },
        new Object[] { new SchemaValCons(true, false) },
        new Object[] { new SchemaValCons(false, true) },
        new Object[] { new SchemaValCons(false, false) },
    };
  }

  @Test(dataProvider = "schemaValConsData")
  public void testGenerateOne(SchemaValCons schemaCons) {

    ObjectSchema objectSchema = loadObjectSchema("case-one-property-bool.yaml");

    ObjectValGenerationService service = fixedObject(
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
    );

    SchemaVal schemaVal = service.generateOne(objectSchema, schemaCons);
    assertEquals(schemaVal,
        new ObjectVal(
            (Map) ArrayUtils.toMap(new Object[][] {
                new Object[] { "prop", new BooleanVal(true) },
            })
        )
    );
  }

  @Test(dataProvider = "schemaValConsData")
  public void testGenerateAll(SchemaValCons schemaCons) {

    ObjectSchema objectSchema = loadObjectSchema("case-one-property-bool.yaml");

    ObjectValGenerationService service = fixedObject(
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
    );

    List<SchemaVal> schemaVals = service.generateAll(objectSchema, schemaCons);

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

  private ObjectSchema loadObjectSchema(String schemaFile) {
    OpenAPI openAPI = OpenAPIV3Loader
        .loadFromClasspath("me/chanjar/oas/server/validator/core/valuegen/schema/object/" + schemaFile);
    return (ObjectSchema) openAPI.getComponents().getSchemas().get("TestObject");
  }

}
