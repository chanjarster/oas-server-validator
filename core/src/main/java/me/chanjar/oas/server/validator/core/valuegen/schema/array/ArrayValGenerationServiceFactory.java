package me.chanjar.oas.server.validator.core.valuegen.schema.array;

import me.chanjar.oas.server.validator.core.value.schema.ArrayVal;
import me.chanjar.oas.server.validator.core.value.schema.SchemaVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerationService;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerator;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGeneratorHolderHelper;
import me.chanjar.oas.server.validator.core.valuegen.schema.special.IgnoredValGenerator;
import me.chanjar.oas.server.validator.core.valuegen.schema.special.NullValGenerator;

import static me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGeneratorHolderHelper.addGeneratorsFor;
import static me.chanjar.oas.server.validator.core.valuegen.schema.binary.BinaryValGenerationServiceFactory.badBinary;
import static me.chanjar.oas.server.validator.core.valuegen.schema.binary.BinaryValGenerationServiceFactory.goodBinary;
import static me.chanjar.oas.server.validator.core.valuegen.schema.bool.BooleanValGenerationServiceFactory.badBool;
import static me.chanjar.oas.server.validator.core.valuegen.schema.bool.BooleanValGenerationServiceFactory.goodBool;
import static me.chanjar.oas.server.validator.core.valuegen.schema.bytearray.ByteArrayValGenerationServiceFactory.badByteArray;
import static me.chanjar.oas.server.validator.core.valuegen.schema.bytearray.ByteArrayValGenerationServiceFactory.goodByteArray;
import static me.chanjar.oas.server.validator.core.valuegen.schema.date.DateValGenerationServiceFactory.badDate;
import static me.chanjar.oas.server.validator.core.valuegen.schema.date.DateValGenerationServiceFactory.goodDate;
import static me.chanjar.oas.server.validator.core.valuegen.schema.datetime.DateTimeValGenerationServiceFactory.badDateTime;
import static me.chanjar.oas.server.validator.core.valuegen.schema.datetime.DateTimeValGenerationServiceFactory.goodDateTime;
import static me.chanjar.oas.server.validator.core.valuegen.schema.email.EmailValGenerationServiceFactory.badEmail;
import static me.chanjar.oas.server.validator.core.valuegen.schema.email.EmailValGenerationServiceFactory.goodEmail;
import static me.chanjar.oas.server.validator.core.valuegen.schema.integer.IntegerValGenerationServiceFactory.badInteger;
import static me.chanjar.oas.server.validator.core.valuegen.schema.integer.IntegerValGenerationServiceFactory.goodInteger;
import static me.chanjar.oas.server.validator.core.valuegen.schema.number.NumberValGenerationServiceFactory.badNumber;
import static me.chanjar.oas.server.validator.core.valuegen.schema.number.NumberValGenerationServiceFactory.goodNumber;
import static me.chanjar.oas.server.validator.core.valuegen.schema.password.PasswordValGenerationServiceFactory.badPassword;
import static me.chanjar.oas.server.validator.core.valuegen.schema.password.PasswordValGenerationServiceFactory.goodPassword;
import static me.chanjar.oas.server.validator.core.valuegen.schema.string.StringValGenerationServiceFactory.badString;
import static me.chanjar.oas.server.validator.core.valuegen.schema.string.StringValGenerationServiceFactory.goodString;
import static me.chanjar.oas.server.validator.core.valuegen.schema.uuid.UUIDValGenerationServiceFactory.badUUID;
import static me.chanjar.oas.server.validator.core.valuegen.schema.uuid.UUIDValGenerationServiceFactory.goodUUID;

public abstract class ArrayValGenerationServiceFactory {

  private ArrayValGenerationServiceFactory() {
    // singleton
  }

  /**
   * Create a {@link ArrayValGenerationService} with {@link SchemaValGenerator}s
   *
   * @param generator
   * @param generators
   * @return
   */
  public static ArrayValGenerationService array(SchemaValGenerator generator,
      SchemaValGenerator... generators) {
    ArrayValGenerationService service = new ArrayValGenerationService();
    addGeneratorsFor(service, generator, generators);
    return service;
  }

  /**
   * Create a default good {@link ArrayValGenerationService}.
   * <p>
   * Generators:
   * <ol>
   * <li>{@link RightSizeArrayValGenerator1}</li>
   * <li>{@link RightSizeArrayValGenerator2}</li>
   * <li>{@link RightSizeArrayValGenerator3}</li>
   * <li>{@link IgnoredValGenerator} in good mode</li>
   * <li>{@link NullValGenerator} in good mode</li>
   * </ol>
   * </p>
   *
   * @return
   */
  public static ArrayValGenerationService goodArray() {

    ArrayValGenerationService arrayValGenerationService = new ArrayValGenerationService();

    arrayValGenerationService.addGenerators(
        // right array size & right array item schema
        registerGoodGenerationServices(new RightSizeArrayValGenerator1(), arrayValGenerationService),
        registerGoodGenerationServices(new RightSizeArrayValGenerator2(), arrayValGenerationService),
        registerGoodGenerationServices(new RightSizeArrayValGenerator3(), arrayValGenerationService)
    );
    addGeneratorsFor(arrayValGenerationService, new IgnoredValGenerator(true), new NullValGenerator(true));
    return arrayValGenerationService;

  }

  /**
   * Create a default bad {@link ArrayValGenerationService}.
   * <p>
   * Generators:
   * <ol>
   * <li>{@link WrongSizeArrayValGenerator1}, with good {@link SchemaValGenerationService}s</li>
   * <li>{@link WrongSizeArrayValGenerator2}, with good {@link SchemaValGenerationService}s</li>
   * <li>{@link RightSizeArrayValGenerator1}, with bad {@link SchemaValGenerationService}s</li>
   * <li>{@link RightSizeArrayValGenerator2}, with bad {@link SchemaValGenerationService}s</li>
   * <li>{@link RightSizeArrayValGenerator3}, with bad {@link SchemaValGenerationService}s</li>
   * <li>{@link IgnoredValGenerator} in bad mode</li>
   * <li>{@link NullValGenerator} in bad mode</li>
   * </ol>
   * </p>
   *
   * @return
   */
  public static ArrayValGenerationService badArray() {
    ArrayValGenerationService arrayValGenerationService = new ArrayValGenerationService();
    arrayValGenerationService.addGenerators(
        // wrong array size & right array item schema
        registerGoodGenerationServices(new WrongSizeArrayValGenerator1(), arrayValGenerationService),
        registerGoodGenerationServices(new WrongSizeArrayValGenerator2(), arrayValGenerationService),
        // right array size & wrong array item schema
        registerBadGenerationServices(new RightSizeArrayValGenerator1(), arrayValGenerationService),
        registerBadGenerationServices(new RightSizeArrayValGenerator2(), arrayValGenerationService),
        registerBadGenerationServices(new RightSizeArrayValGenerator3(), arrayValGenerationService)
    );

    addGeneratorsFor(arrayValGenerationService, new IgnoredValGenerator(false), new NullValGenerator(false));
    return arrayValGenerationService;
  }

  /**
   * Create a {@link ArrayValGenerationService} with fixed values
   *
   * @param value
   * @param values
   * @return
   */
  public static ArrayValGenerationService fixedArray(SchemaVal[] value, SchemaVal[]... values) {
    ArrayValGenerationService service = new ArrayValGenerationService();
    addGeneratorsFor(service, v -> new ArrayVal(v), value, values);
    return service;
  }

  /**
   * Register all good generation service
   *
   * @param generatorTemplate
   * @param arrayValGenerationService
   * @return
   */
  private static ArrayValGenerator registerGoodGenerationServices(ArrayValGeneratorTemplate generatorTemplate,
      ArrayValGenerationService arrayValGenerationService) {
    generatorTemplate.addItemGenerationServices(
        goodBinary(),
        goodBool(),
        goodByteArray(),
        goodDate(),
        goodDateTime(),
        goodEmail(),
        goodInteger(),
        goodNumber(),
        goodPassword(),
        goodString(),
        goodUUID(),
        // support nested array
        arrayValGenerationService
    );
    return generatorTemplate;
  }

  /**
   * Register all good generation service
   *
   * @param generatorTemplate
   * @param arrayValGenerationService
   * @return
   */
  private static ArrayValGenerator registerBadGenerationServices(ArrayValGeneratorTemplate generatorTemplate,
      ArrayValGenerationService arrayValGenerationService) {
    generatorTemplate.addItemGenerationServices(
        badBinary(),
        badBool(),
        badByteArray(),
        badDate(),
        badDateTime(),
        badEmail(),
        badInteger(),
        badNumber(),
        badPassword(),
        badString(),
        badUUID(),
        // support nested array
        arrayValGenerationService
    );
    return generatorTemplate;
  }

}
