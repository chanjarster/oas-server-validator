package me.chanjar.oas.server.validator.core.valuegen.schema.object;

import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import me.chanjar.oas.server.validator.core.value.schema.ObjectVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValCons;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerationService;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.text.MessageFormat;
import java.util.*;

public class ComplexObjectValGenerationService implements ObjectValGenerationService {

  private final String name;

  private final List<SchemaValGenerationService> generationServices = new ArrayList<>();

  private final Map<String, SchemaValGenerationService> propertyGenerationService = new HashMap<>();

  private final List<SchemaValGenerator> schemaValGenerators = new ArrayList<>();

  /**
   * Used to get property default schema vals
   */
  private final ObjectValGenerationService defaultGenerationService;

  /**
   *
   * @param name
   */
  public ComplexObjectValGenerationService(String name) {
    this.name = name;
    defaultGenerationService = this;
    generationServices.add(this);
  }

  /**
   *
   * @param name
   * @param defaultGenerationService A {@link ObjectValGenerationService} used to generate
   *                                 default {@link ObjectVal}. It's used in {@link #generateAll(ObjectSchema, SchemaValCons)}
   */
  public ComplexObjectValGenerationService(String name,
      ObjectValGenerationService defaultGenerationService) {
    this.name = name;
    this.defaultGenerationService = defaultGenerationService;
    generationServices.add(this);
  }

  @Override
  public boolean supports(Schema schema) {
    return schema.getClass().equals(ObjectSchema.class);
  }

  @Override
  public SchemaVal generateOne(ObjectSchema schema, SchemaValCons cons) {

    Map<String, SchemaVal> resultMap = new HashMap<>();

    if (schema.getProperties() != null) {

      for (Map.Entry<String, Schema> entry : schema.getProperties().entrySet()) {
        String propertyName = entry.getKey();
        Schema propertySchema = entry.getValue();

        SchemaValGenerationService generationService = findGenerationService(propertyName, propertySchema);
        if (generationService == null) {
          throw new ObjectValGenerationException(
              errorNoPropertyGenerationService("ONE", schema, propertyName, propertySchema)
          );
        }
        SchemaValCons schemaValCons = new SchemaValCons(
            schema.getRequired() != null && schema.getRequired().contains(propertyName),
            Boolean.TRUE.equals(propertySchema.getNullable()));

        SchemaVal schemaVal;

        try {
          schemaVal = generationService.generateOne(propertySchema, schemaValCons);
        } catch (Exception ex) {
          throw new ObjectValGenerationException(
              errorPropertyExceptionRaised("ONE", schema, propertyName, propertySchema), ex);
        }

        if (schemaVal == null) {
          throw new ObjectValGenerationException(
              errorPropertyNoneGenerated("ONE", schema, propertyName, propertySchema));
        }
        resultMap.put(propertyName, schemaVal);
      }

    }

    if (!resultMap.isEmpty()) {
      return new ObjectVal(resultMap);
    }

    return schemaValGenerators.stream()
        .filter(g -> g.supports(schema, cons))
        .findFirst()
        .map(g -> g.generate(schema, cons))
        .orElse(null);

  }

  @Override
  public List<SchemaVal> generateAll(ObjectSchema schema, SchemaValCons cons) {

    Map<String, List<SchemaVal>> propertySchemaVals = new HashMap<>();

    Map<String, Schema> properties = schema.getProperties();

    List<SchemaVal> result = new ArrayList<>();

    if (schema.getProperties() != null) {

      for (Map.Entry<String, Schema> entry : properties.entrySet()) {
        String propertyName = entry.getKey();
        Schema propertySchema = entry.getValue();

        SchemaValGenerationService generationService = findGenerationService(propertyName, propertySchema);
        if (generationService == null) {
          throw new ObjectValGenerationException(
              errorNoPropertyGenerationService("ALL", schema, propertyName, propertySchema)
          );
        }

        SchemaValCons propertySchemaValCons = new SchemaValCons(
            schema.getRequired() != null && schema.getRequired().contains(propertyName),
            Boolean.TRUE.equals(propertySchema.getNullable()));
        List<SchemaVal> values;

        try {
          values = generationService.generateAll(propertySchema, propertySchemaValCons);
        } catch (Exception ex) {
          throw new ObjectValGenerationException(
              errorPropertyExceptionRaised("ALL", schema, propertyName, propertySchema), ex);
        }

        if (CollectionUtils.isEmpty(values)) {
          throw new ObjectValGenerationException(
              errorPropertyNoneGenerated("ALL", schema, propertyName, propertySchema));
        }
        propertySchemaVals.put(propertyName, values);

      }

      Map<String, SchemaVal> defaultPropertySchemaVal = getDefaultPropertySchemaVal(schema, cons);

      if (!defaultPropertySchemaVal.keySet().equals(properties.keySet())) {
        throw new ObjectValGenerationException(
            error("Default property SchemaVal doesn\'t contains all properties", "ALL", schema));
      }

      result.addAll(permutate(propertySchemaVals, defaultPropertySchemaVal));

    }


    schemaValGenerators.stream()
        .filter(g -> g.supports(schema, cons))
        .map(g -> g.generate(schema, cons))
        .forEach(v -> result.add(v));

    return result;
  }

  protected Map<String, SchemaVal> getDefaultPropertySchemaVal(ObjectSchema schema, SchemaValCons cons) {
    SchemaVal schemaVal = defaultGenerationService.generateOne(schema, cons);
    if (ObjectVal.class.equals(schemaVal.getClass())) {
      return new HashMap<>(((ObjectVal) schemaVal).getValue());
    }
    return Collections.emptyMap();
  }

  /**
   * Input:
   * <pre class="code">
   * propertySchemaVals: "foo": [1, 2], "bar": ["a", "b"]
   * defaultPropertySchemaVal: "foo": 3, "bar": "c"
   * </pre>
   * Output:
   * <pre class="code">
   * { "foo": 1, "bar": "c"}
   * { "foo": 2, "bar": "c"}
   * { "foo": 3, "bar": "a"}
   * { "foo": 3, "bar": "b"}
   * </pre>
   *
   * @param propertySchemaVals
   * @param defaultPropertySchemaVal
   * @return
   */
  private List<ObjectVal> permutate(Map<String, List<SchemaVal>> propertySchemaVals,
      Map<String, SchemaVal> defaultPropertySchemaVal) {

    Set<ObjectVal> result = new HashSet<>(propertySchemaVals.size());

    for (Map.Entry<String, List<SchemaVal>> entry : propertySchemaVals.entrySet()) {

      String currentPropertyName = entry.getKey();
      List<SchemaVal> currentPropertySchemaVals = entry.getValue();

      for (SchemaVal currentPropertySchemaVal : currentPropertySchemaVals) {
        Map<String, SchemaVal> map = new HashMap<>(defaultPropertySchemaVal);
        map.put(currentPropertyName, currentPropertySchemaVal);
        result.add(new ObjectVal(map));
      }
    }

    return new ArrayList<>(result);

  }

  private String error(String message, String mode, ObjectSchema objectSchema) {
    return MessageFormat
        .format(
            "{0}. Service:[{1}], Mode:[{2}], ObjectSchema:[{3}]",
            message, name, mode, objectSchema.toString());
  }

  private String errorPropertyNoneGenerated(String mode, ObjectSchema objectSchema, String propertyName,
      Schema propertySchema) {
    return MessageFormat
        .format(
            "Nothing generated for property. Service:[{0}], Mode:[{1}], ObjectSchema:[{2}], Property:[{3}], PropertySchema:[{4}]",
            name, mode, objectSchema.toString(), propertyName, propertySchema.toString());
  }

  private String errorNoPropertyGenerationService(String mode, ObjectSchema objectSchema, String propertyName,
      Schema propertySchema) {
    return MessageFormat
        .format(
            "No suitable SchemaValGenerationService for PropertySchema. Service:[{0}], Mode:[{1}], ObjectSchema:[{2}], Property:[{3}], PropertySchema:[{4}]",
            name, mode, objectSchema.toString(), propertyName, propertySchema.toString());
  }

  private String errorPropertyExceptionRaised(String mode, ObjectSchema objectSchema, String propertyName,
      Schema propertySchema) {
    return MessageFormat
        .format(
            "Exception happens when generating property SchemaVal. Service:[{0}], Mode:[{1}], ObjectSchema:[{2}], Property:[{3}], PropertySchema:[{4}]",
            name, mode, objectSchema.toString(), propertyName, propertySchema.toString());
  }

  private SchemaValGenerationService findGenerationService(String propertyName, Schema schema) {

    SchemaValGenerationService generationService = propertyGenerationService.get(propertyName);
    if (generationService != null) {
      return generationService;
    }

    return generationServices.stream()
        .filter(service -> service.supports(schema))
        .findFirst()
        .orElse(null);

  }

  /**
   * Register a {@link SchemaValGenerationService}
   *
   * @param schemaValGenerationService
   */
  public void addPropertyGenerationService(SchemaValGenerationService schemaValGenerationService) {
    this.generationServices.add(schemaValGenerationService);
  }

  /**
   * Register multiple {@link SchemaValGenerationService}
   *
   * @param schemaValGenerationService
   * @param schemaValGenerationServices
   */
  public void addPropertyGenerationServices(SchemaValGenerationService schemaValGenerationService,
      SchemaValGenerationService... schemaValGenerationServices) {
    addPropertyGenerationService(schemaValGenerationService);
    if (ArrayUtils.isNotEmpty(schemaValGenerationServices)) {
      Arrays.stream(schemaValGenerationServices).forEach(s -> addPropertyGenerationService(s));
    }
  }

  /**
   * Register a {@link SchemaValGenerationService} for a specific property name
   *
   * @param propertyName
   * @param schemaValGenerationService
   */
  public void addPropertyGenerationService(String propertyName, SchemaValGenerationService schemaValGenerationService) {
    this.propertyGenerationService.putIfAbsent(propertyName, schemaValGenerationService);
  }

  /**
   * @param generator
   */
  public void addSchemaValGenerator(SchemaValGenerator generator) {
    schemaValGenerators.add(generator);
  }

  /**
   * @param generator
   * @param generators
   */
  public void addSchemaValGenerators(SchemaValGenerator generator, SchemaValGenerator... generators) {
    this.schemaValGenerators.add(generator);
    if (ArrayUtils.isNotEmpty(generators)) {
      this.schemaValGenerators.addAll(Arrays.asList(generators));
    }
  }

}
