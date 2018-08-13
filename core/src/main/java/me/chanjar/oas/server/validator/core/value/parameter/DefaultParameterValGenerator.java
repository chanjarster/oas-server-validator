package me.chanjar.oas.server.validator.core.value.parameter;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValCons;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerationService;
import org.apache.commons.lang3.ArrayUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class DefaultParameterValGenerator implements ParameterValGenerator {

  private final List<SchemaValGenerationService> schemaValGenerationServices = new ArrayList<>();

  @Override
  public ParameterVal generateOne(Parameter parameter) {

    Schema schema = parameter.getSchema();

    SchemaValGenerationService schemaValGenerationService = findSchemaValGenerationService(schema);
    if (schemaValGenerationService == null) {
      throw new ParameterValGenerationException(
          MessageFormat.format("Could not find SchemaValGenerationService. Parameter:[{0}]", parameter)
      );
    }

    // according to https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#parameter-object
    // required default value is false, allowEmptyValue default value is false
    SchemaValCons schemaValCons = new SchemaValCons(
        Boolean.TRUE.equals(parameter.getRequired()),
        Boolean.TRUE.equals(parameter.getAllowEmptyValue())
    );

    SchemaVal schemaVal = schemaValGenerationService.generateOne(schema, schemaValCons);

    return new ParameterVal(parameter, schemaVal);

  }

  @Override
  public List<ParameterVal> generateAll(Parameter parameter) {

    Schema schema = parameter.getSchema();

    SchemaValGenerationService schemaValGenerationService = findSchemaValGenerationService(schema);
    if (schemaValGenerationService == null) {
      throw new ParameterValGenerationException(
          MessageFormat.format("Could not find SchemaValGenerationService. Parameter:[{0}]", parameter)
      );
    }

    SchemaValCons schemaValCons = new SchemaValCons(
        Boolean.TRUE.equals(parameter.getRequired()),
        Boolean.TRUE.equals(parameter.getAllowEmptyValue())
    );

    List<SchemaVal> schemaVals = schemaValGenerationService.generateAll(schema, schemaValCons);

    return schemaVals.stream()
        .map(schemaVal -> new ParameterVal(parameter, schemaVal))
        .collect(toList());
  }


  private SchemaValGenerationService findSchemaValGenerationService(Schema schema) {
    return schemaValGenerationServices.stream()
        .filter(s -> s.supports(schema))
        .findFirst()
        .orElse(null);
  }

  public void addSchemaValGenerationServices(SchemaValGenerationService schemaValGenerationService,
      SchemaValGenerationService... schemaValGenerationServices) {

    this.schemaValGenerationServices.add(schemaValGenerationService);
    if (ArrayUtils.isNotEmpty(schemaValGenerationServices)) {
      this.schemaValGenerationServices.addAll(Arrays.asList(schemaValGenerationServices));
    }

  }

}
