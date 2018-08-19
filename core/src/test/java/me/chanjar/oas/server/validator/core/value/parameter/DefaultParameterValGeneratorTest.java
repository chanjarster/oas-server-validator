package me.chanjar.oas.server.validator.core.value.parameter;

import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import me.chanjar.oas.server.validator.core.value.schema.StringVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValCons;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerationService;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

public class DefaultParameterValGeneratorTest {

  @DataProvider
  public Object[][] testParameters() {
    return new Object[][] {
        new Object[] {
            createParameter(null, null, Parameter.StyleEnum.FORM, null, null),
            new SerializeOption(Parameter.StyleEnum.FORM, false, false),
            new SchemaValCons(false, false) },
        new Object[] {
            createParameter(null, null, Parameter.StyleEnum.FORM, true, true),
            new SerializeOption(Parameter.StyleEnum.FORM, true, true),
            new SchemaValCons(false, false) },
        new Object[] {
            createParameter(null, null, Parameter.StyleEnum.FORM, false, false),
            new SerializeOption(Parameter.StyleEnum.FORM, false, false),
            new SchemaValCons(false, false) },

        new Object[] {
            createParameter(true, true, Parameter.StyleEnum.FORM, null, null),
            new SerializeOption(Parameter.StyleEnum.FORM, false, false),
            new SchemaValCons(true, true) },
        new Object[] {
            createParameter(false, false, Parameter.StyleEnum.FORM, null, null),
            new SerializeOption(Parameter.StyleEnum.FORM, false, false),
            new SchemaValCons(false, false) },
    };
  }

  @Test(dataProvider = "testParameters")
  public void testGenerateOne(Parameter parameter,
      SerializeOption expectedSerializeOption,
      SchemaValCons expectedSchemaValCons) {

    SchemaValGenerationService mockGenerationService = mock(SchemaValGenerationService.class);
    when(mockGenerationService.supports(any())).thenReturn(true);
    when(mockGenerationService.generateOne(any(), any())).thenReturn(new StringVal("abc"));

    DefaultParameterValGenerator generator = new DefaultParameterValGenerator();
    generator.addSchemaValGenerationServices(mockGenerationService);

    ParameterVal parameterVal = generator.generateOne(parameter);

    assertEquals(parameterVal.getSchemaVal(), new StringVal("abc"));
    assertEquals(parameterVal.getIn(), "query");
    assertEquals(parameterVal.getName(), "foo");

    assertEquals(parameterVal.getSerializeOption(), expectedSerializeOption);

    verify(mockGenerationService).supports(new StringSchema());
    verify(mockGenerationService).generateOne(new StringSchema(), expectedSchemaValCons);

  }

  @Test(expectedExceptions = ParameterValGenerationException.class,
      expectedExceptionsMessageRegExp = "Could not find SchemaValGenerationService.*")
  public void testGenerateOneThrowException() {

    SchemaValGenerationService mockGenerationService = mock(SchemaValGenerationService.class);
    when(mockGenerationService.supports(any())).thenReturn(false);

    DefaultParameterValGenerator generator = new DefaultParameterValGenerator();
    generator.addSchemaValGenerationServices(mockGenerationService);

    Parameter parameter = new Parameter();
    parameter.setSchema(new StringSchema());

    generator.generateOne(parameter);

  }

  @Test(dataProvider = "testParameters")
  public void testGenerateAll(Parameter parameter,
      SerializeOption expectedSerializeOption,
      SchemaValCons expectedSchemaValCons) {

    SchemaValGenerationService mockGenerationService = mock(SchemaValGenerationService.class);
    when(mockGenerationService.supports(any())).thenReturn(true);
    when(mockGenerationService.generateAll(any(), any())).thenReturn(Collections.singletonList(new StringVal("abc")));

    DefaultParameterValGenerator generator = new DefaultParameterValGenerator();
    generator.addSchemaValGenerationServices(mockGenerationService);

    List<ParameterVal> parameterVals = generator.generateAll(parameter);
    assertEquals(parameterVals.size(), 1);

    ParameterVal parameterVal = parameterVals.get(0);
    assertEquals(parameterVal.getSchemaVal(), new StringVal("abc"));
    assertEquals(parameterVal.getIn(), "query");
    assertEquals(parameterVal.getName(), "foo");

    assertEquals(parameterVal.getSerializeOption(), expectedSerializeOption);

    verify(mockGenerationService).supports(new StringSchema());
    verify(mockGenerationService).generateAll(new StringSchema(), expectedSchemaValCons);

  }

  @Test(expectedExceptions = ParameterValGenerationException.class,
      expectedExceptionsMessageRegExp = "Could not find SchemaValGenerationService.*")
  public void testGenerateAllThrowException() {

    SchemaValGenerationService mockGenerationService = mock(SchemaValGenerationService.class);
    when(mockGenerationService.supports(any())).thenReturn(false);

    DefaultParameterValGenerator generator = new DefaultParameterValGenerator();
    generator.addSchemaValGenerationServices(mockGenerationService);

    Parameter parameter = new Parameter();
    parameter.setSchema(new StringSchema());

    generator.generateAll(parameter);

  }

  private Parameter createParameter(Boolean required, Boolean allowEmptyValue,
      Parameter.StyleEnum styleEnum, Boolean explode, Boolean allowReserved) {

    Parameter parameter = new Parameter();
    parameter.setIn("query");
    parameter.setName("foo");

    parameter.setRequired(required);
    parameter.setAllowEmptyValue(allowEmptyValue);

    parameter.setStyle(styleEnum);
    parameter.setExplode(explode);
    parameter.setAllowReserved(allowReserved);

    parameter.setSchema(new StringSchema());

    return parameter;
  }

}
