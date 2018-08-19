package me.chanjar.oas.server.validator.core.value.parameter;

import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerationService;
import me.chanjar.oas.server.validator.core.valuegen.schema.array.ArrayValGenerationService;
import me.chanjar.oas.server.validator.core.valuegen.schema.binary.BinaryValGenerationService;
import me.chanjar.oas.server.validator.core.valuegen.schema.bool.BooleanValGenerationService;
import me.chanjar.oas.server.validator.core.valuegen.schema.bytearray.ByteArrayValGenerationService;
import me.chanjar.oas.server.validator.core.valuegen.schema.date.DateValGenerationService;
import me.chanjar.oas.server.validator.core.valuegen.schema.datetime.DateTimeValGenerationService;
import me.chanjar.oas.server.validator.core.valuegen.schema.email.EmailValGenerationService;
import me.chanjar.oas.server.validator.core.valuegen.schema.integer.IntegerValGenerationService;
import me.chanjar.oas.server.validator.core.valuegen.schema.number.NumberValGenerationService;
import me.chanjar.oas.server.validator.core.valuegen.schema.object.ObjectValGenerationService;
import me.chanjar.oas.server.validator.core.valuegen.schema.password.PasswordValGenerationService;
import me.chanjar.oas.server.validator.core.valuegen.schema.string.StringValGenerationService;
import me.chanjar.oas.server.validator.core.valuegen.schema.uuid.UUIDValGenerationService;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static me.chanjar.oas.server.validator.core.value.parameter.ParameterValGeneratorFactory.badParameter;
import static me.chanjar.oas.server.validator.core.value.parameter.ParameterValGeneratorFactory.goodParameter;
import static org.assertj.core.api.Assertions.assertThat;

public class ParameterValGeneratorFactoryTest {

  @Test
  public void testGoodParameter() throws IllegalAccessException {

    ParameterValGenerator generator = goodParameter();
    assertSchemaValGenerationServices((DefaultParameterValGenerator) generator);

  }

  @Test
  public void testBadParameter() throws IllegalAccessException {

    ParameterValGenerator generator = badParameter();
    assertSchemaValGenerationServices((DefaultParameterValGenerator) generator);

  }

  private void assertSchemaValGenerationServices(DefaultParameterValGenerator defaultParameterValGenerator)
      throws IllegalAccessException {

    List<SchemaValGenerationService> schemaValGenerationServices = (List<SchemaValGenerationService>) FieldUtils
        .readField(defaultParameterValGenerator, "schemaValGenerationServices", true);

    Set<Class> schemaValGenerationServiceClasses = schemaValGenerationServiceClasses();

    for (SchemaValGenerationService schemaValGenerationService : schemaValGenerationServices) {

      assertThat(schemaValGenerationServiceClasses)
          .anyMatch(cls -> cls.isAssignableFrom(schemaValGenerationService.getClass()));

    }

  }

  private Set<Class> schemaValGenerationServiceClasses() {

    Set<Class> objects = new HashSet<>();
    objects.add(ArrayValGenerationService.class);
    objects.add(StringValGenerationService.class);
    objects.add(BinaryValGenerationService.class);
    objects.add(BooleanValGenerationService.class);
    objects.add(ByteArrayValGenerationService.class);
    objects.add(DateValGenerationService.class);
    objects.add(DateTimeValGenerationService.class);
    objects.add(EmailValGenerationService.class);
    objects.add(IntegerValGenerationService.class);
    objects.add(NumberValGenerationService.class);
    objects.add(ObjectValGenerationService.class);
    objects.add(PasswordValGenerationService.class);
    objects.add(StringValGenerationService.class);
    objects.add(UUIDValGenerationService.class);
    return objects;

  }

}
