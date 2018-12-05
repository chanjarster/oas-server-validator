package me.chanjar.oas.server.validator.core.valuegen.mediatype;

import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.mediatype.MediaTypeVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValCons;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerationService;
import org.apache.commons.lang3.ArrayUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class DefaultMediaTypeValGenerator implements MediaTypeValGenerator {

  private final List<SchemaValGenerationService> schemaValGenerationServices = new ArrayList<>();

  @Override
  public MediaTypeVal generateOne(MediaType mediaType) {

    Schema schema = mediaType.getSchema();

    SchemaValGenerationService schemaValGenerationService = findSchemaValGenerationService(schema);
    if (schemaValGenerationService == null) {
      throw new MediaTypeValGenerationException(
          MessageFormat.format("Could not find SchemaValGenerationService. MediaType:[{0}]", mediaType)
      );
    }

    SchemaValCons schemaValCons = new SchemaValCons(true, false);

    SchemaVal schemaVal = schemaValGenerationService.generateOne(schema, schemaValCons);

    return new MediaTypeVal(schemaVal, mediaType.getEncoding());

  }

  @Override
  public List<MediaTypeVal> generateAll(MediaType mediaType) {

    Schema schema = mediaType.getSchema();

    SchemaValGenerationService schemaValGenerationService = findSchemaValGenerationService(schema);
    if (schemaValGenerationService == null) {
      throw new MediaTypeValGenerationException(
          MessageFormat.format("Could not find SchemaValGenerationService. MediaType:[{0}]", mediaType)
      );
    }

    SchemaValCons schemaValCons = new SchemaValCons(true, false);

    List<SchemaVal> schemaVals = schemaValGenerationService.generateAll(schema, schemaValCons);

    return schemaVals.stream()
        .map(schemaVal -> new MediaTypeVal(schemaVal, mediaType.getEncoding()))
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
