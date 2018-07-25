package me.chanjar.oas.server.validator.core.valuegen.schema.object;

import me.chanjar.oas.server.validator.core.value.schema.ObjectVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.special.FixedSchemaValGenerator;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

import static me.chanjar.oas.server.validator.core.valuegen.schema.array.ArrayValGenerationServiceFactory.badArray;
import static me.chanjar.oas.server.validator.core.valuegen.schema.array.ArrayValGenerationServiceFactory.goodArray;
import static me.chanjar.oas.server.validator.core.valuegen.schema.binary.BinaryValGenerationServiceFactory.goodBinary;
import static me.chanjar.oas.server.validator.core.valuegen.schema.bool.BooleanValGenerationServiceFactory.goodBool;
import static me.chanjar.oas.server.validator.core.valuegen.schema.bytearray.ByteArrayValGenerationServiceFactory.goodByteArray;
import static me.chanjar.oas.server.validator.core.valuegen.schema.date.DateValGenerationServiceFactory.goodDate;
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
import static me.chanjar.oas.server.validator.core.valuegen.schema.uuid.UUIDValGenerationServiceFactory.goodUUID;

public abstract class ObjectValGenerationServiceFactory {

  private ObjectValGenerationServiceFactory() {
    // singleton
  }

  /**
   * Create a default good {@link ObjectValGenerationService}.
   *
   * @return
   */
  public static ObjectValGenerationService goodObject() {
    DefaultObjectValGenerationService service =
        new DefaultObjectValGenerationService("GoodObjectValGenerationService");
    service.registerGenerationService(goodArray());
    service.registerGenerationService(goodBinary());
    service.registerGenerationService(goodBool());
    service.registerGenerationService(goodByteArray());
    service.registerGenerationService(goodDate());
    service.registerGenerationService(goodDateTime());
    service.registerGenerationService(goodEmail());
    service.registerGenerationService(goodInteger());
    service.registerGenerationService(goodNumber());
    service.registerGenerationService(goodPassword());
    service.registerGenerationService(goodString());
    service.registerGenerationService(goodUUID());
    // register self as ObjectVal generator
    service.registerGenerationService(service);
    return service;
  }

  /**
   * Create a default bad {@link ObjectValGenerationService}.
   *
   * @return
   */
  public static ObjectValGenerationService badObject() {
    DefaultObjectValGenerationService service =
        new DefaultObjectValGenerationService("BadObjectValGenerationService", goodObject());

    service.registerGenerationService(badArray());
    service.registerGenerationService(goodBinary());
    service.registerGenerationService(goodBool());
    service.registerGenerationService(goodByteArray());
    service.registerGenerationService(goodDate());
    service.registerGenerationService(goodDateTime());
    service.registerGenerationService(badEmail());
    service.registerGenerationService(badInteger());
    service.registerGenerationService(badNumber());
    service.registerGenerationService(badPassword());
    service.registerGenerationService(badString());
    service.registerGenerationService(goodUUID());
    // register self as ObjectVal generator
    service.registerGenerationService(service);

    return service;
  }

  /**
   * Create a {@link ObjectValGenerationService} with fixed values
   *
   * @param value
   * @param values
   * @return
   */
  public static ObjectValGenerationService fixedObject(ObjectVal value, ObjectVal... values) {

    SimpleObjectValGenerationService service = new SimpleObjectValGenerationService();
    service.addGenerator(new FixedSchemaValGenerator(value));
    if (ArrayUtils.isNotEmpty(values)) {
      Arrays.stream(values)
          .forEach(g -> service.addGenerator(new FixedSchemaValGenerator(g)));
    }
    return service;
  }

}
