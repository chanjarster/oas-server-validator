package me.chanjar.oas.server.validator.core.valuegen.schema.object;

import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValCons;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerator;
import org.apache.commons.lang3.ArrayUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleObjectValGenerationService implements ObjectValGenerationService {

  private final List<SchemaValGenerator> generators = new ArrayList<>();

  private final String name = "FixedObjectValGenerationService";

  @Override
  public boolean supports(Schema schema) {
    return schema.getClass().equals(ObjectSchema.class);
  }

  @Override
  public SchemaVal generateOne(ObjectSchema schema, SchemaValCons cons) {

    return generators.stream()
        .filter(g -> g.supports(schema, cons))
        .findFirst()
        .map(g -> g.generate(schema, cons))
        .orElse(null);

  }

  @Override
  public List<SchemaVal> generateAll(ObjectSchema schema, SchemaValCons cons) {

    List<SchemaVal> result = new ArrayList<>();

    generators.stream()
        .filter(g -> g.supports(schema, cons))
        .map(g -> g.generate(schema, cons))
        .forEach(v -> result.add(v));

    if (result.isEmpty()) {
      throw new ObjectValGenerationException(errorNoneGenerated("ALL", schema));
    }

    return result;
  }

  private String errorNoneGenerated(String mode, ObjectSchema objectSchema) {
    return MessageFormat
        .format(
            "Nothing generated for ObjectSchema. Service:[{0}], Mode:[{1}], ObjectSchema:[{2}]",
            name, mode, objectSchema.toString());
  }

  /**
   * @param generator
   */
  public void addGenerator(SchemaValGenerator generator) {
    generators.add(generator);
  }

  /**
   * @param generator
   * @param generators
   */
  public void addGenerators(SchemaValGenerator generator, SchemaValGenerator... generators) {
    this.generators.add(generator);
    if (ArrayUtils.isNotEmpty(generators)) {
      this.generators.addAll(Arrays.asList(generator));
    }
  }

}
