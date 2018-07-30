package me.chanjar.oas.server.validator.core.valuegen.schema.array;

import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.ArrayVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValCons;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerationService;
import org.apache.commons.lang3.ArrayUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ArrayValGeneratorTemplate implements ArrayValGenerator {

  private final List<SchemaValGenerationService> generationServices = new ArrayList<>();

  @Override
  public ArrayVal generate(ArraySchema schema, SchemaValCons cons) {

    Schema itemSchema = schema.getItems();

    SchemaValGenerationService generationService = findGenerationService(itemSchema);
    if (generationService == null) {
      throw new ArrayValGenerationException(
          MessageFormat.format("No suitable SchemaValGenerationService for items. ArraySchema:[{0}]", schema)
      );
    }

    int itemSize = calcItemSize(schema);
    SchemaVal[] items = new SchemaVal[itemSize];

    for (int i = 0; i < itemSize; i++) {
      items[i] = generationService.generateOne(itemSchema, new SchemaValCons(true, false));
      if (items[i] == null) {
        throw new ArrayValGenerationException(
            MessageFormat.format("Generated a null item. ArraySchema:[{0}]", schema)
        );
      }
    }
    return new ArrayVal(items);
  }

  private SchemaValGenerationService findGenerationService(Schema itemSchema) {

    return generationServices.stream()
        .filter(service -> service.supports(itemSchema))
        .findFirst()
        .orElse(null);

  }

  abstract protected int calcItemSize(ArraySchema schema);

  /**
   * Register a {@link SchemaValGenerationService}
   *
   * @param schemaValGenerationService
   */
  public void addItemGenerationService(SchemaValGenerationService schemaValGenerationService) {
    this.generationServices.add(schemaValGenerationService);
  }

  /**
   * Register multiple {@link SchemaValGenerationService}
   *
   * @param schemaValGenerationService
   * @param schemaValGenerationServices
   */
  public void addItemGenerationServices(SchemaValGenerationService schemaValGenerationService,
      SchemaValGenerationService... schemaValGenerationServices) {
    addItemGenerationService(schemaValGenerationService);
    if (ArrayUtils.isNotEmpty(schemaValGenerationServices)) {
      Arrays.stream(schemaValGenerationServices).forEach(s -> addItemGenerationService(s));
    }
  }

}
