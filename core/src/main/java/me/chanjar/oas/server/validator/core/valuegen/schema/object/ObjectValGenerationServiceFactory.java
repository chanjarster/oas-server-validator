package me.chanjar.oas.server.validator.core.valuegen.schema.object;

import me.chanjar.oas.server.validator.core.value.schema.ObjectVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.array.ArrayValGenerationServiceFactory;
import me.chanjar.oas.server.validator.core.valuegen.schema.binary.BinaryValGenerationServiceFactory;
import me.chanjar.oas.server.validator.core.valuegen.schema.bool.BooleanValGenerationServiceFactory;
import me.chanjar.oas.server.validator.core.valuegen.schema.bytearray.ByteArrayValGenerationServiceFactory;
import me.chanjar.oas.server.validator.core.valuegen.schema.date.DateValGenerationServiceFactory;
import me.chanjar.oas.server.validator.core.valuegen.schema.datetime.DateTimeValGenerationServiceFactory;
import me.chanjar.oas.server.validator.core.valuegen.schema.email.EmailValGenerationServiceFactory;
import me.chanjar.oas.server.validator.core.valuegen.schema.integer.IntegerValGenerationServiceFactory;
import me.chanjar.oas.server.validator.core.valuegen.schema.number.NumberValGenerationServiceFactory;
import me.chanjar.oas.server.validator.core.valuegen.schema.password.PasswordValGenerationServiceFactory;
import me.chanjar.oas.server.validator.core.valuegen.schema.special.FixedSchemaValGenerator;
import me.chanjar.oas.server.validator.core.valuegen.schema.special.IgnoredValGenerator;
import me.chanjar.oas.server.validator.core.valuegen.schema.special.NullValGenerator;
import me.chanjar.oas.server.validator.core.valuegen.schema.string.StringValGenerationServiceFactory;
import me.chanjar.oas.server.validator.core.valuegen.schema.uuid.UUIDValGenerationServiceFactory;
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
   * Create a default good {@link ObjectValGenerationService} with other primitive schema val generation services:
   * <ol>
   * <li>{@link ArrayValGenerationServiceFactory#goodArray}</li>
   * <li>{@link BinaryValGenerationServiceFactory#goodBinary}</li>
   * <li>{@link BooleanValGenerationServiceFactory#goodBool}</li>
   * <li>{@link ByteArrayValGenerationServiceFactory#goodByteArray}</li>
   * <li>{@link DateValGenerationServiceFactory#goodDate}</li>
   * <li>{@link DateTimeValGenerationServiceFactory#goodDateTime}</li>
   * <li>{@link EmailValGenerationServiceFactory#goodEmail}</li>
   * <li>{@link IntegerValGenerationServiceFactory#goodInteger}</li>
   * <li>{@link NumberValGenerationServiceFactory#goodNumber}</li>
   * <li>{@link PasswordValGenerationServiceFactory#goodPassword}</li>
   * <li>{@link StringValGenerationServiceFactory#goodString}</li>
   * <li>{@link UUIDValGenerationServiceFactory#goodUUID}</li>
   * <li>register self as object val generation service</li>
   * </ol>
   *
   * @return
   */
  public static ObjectValGenerationService goodObject() {
    DefaultObjectValGenerationService service =
        new DefaultObjectValGenerationService("GoodObjectValGenerationService");
    service.addPropertyGenerationService(goodArray());
    service.addPropertyGenerationService(goodBinary());
    service.addPropertyGenerationService(goodBool());
    service.addPropertyGenerationService(goodByteArray());
    service.addPropertyGenerationService(goodDate());
    service.addPropertyGenerationService(goodDateTime());
    service.addPropertyGenerationService(goodEmail());
    service.addPropertyGenerationService(goodInteger());
    service.addPropertyGenerationService(goodNumber());
    service.addPropertyGenerationService(goodPassword());
    service.addPropertyGenerationService(goodString());
    service.addPropertyGenerationService(goodUUID());
    // register self as ObjectVal generator
    service.addPropertyGenerationService(service);

    service.addSchemaValGenerators(new IgnoredValGenerator(true), new NullValGenerator(true));
    return service;
  }

  /**
   * Create a default bad {@link ObjectValGenerationService} with other primitive schema val generation services:
   * <ol>
   * <li>{@link ArrayValGenerationServiceFactory#badArray}</li>
   * <li>{@link BinaryValGenerationServiceFactory#goodBinary}, because of no bad generation service</li>
   * <li>{@link BooleanValGenerationServiceFactory#goodBool}, because of no bad generation service</li>
   * <li>{@link ByteArrayValGenerationServiceFactory#goodByteArray}, because of no bad generation service</li>
   * <li>{@link DateValGenerationServiceFactory#goodDate}, because of no bad generation service</li>
   * <li>{@link DateTimeValGenerationServiceFactory#goodDateTime}, because of no bad generation service</li>
   * <li>{@link EmailValGenerationServiceFactory#badEmail}</li>
   * <li>{@link IntegerValGenerationServiceFactory#badInteger}</li>
   * <li>{@link NumberValGenerationServiceFactory#badNumber}</li>
   * <li>{@link PasswordValGenerationServiceFactory#badPassword}</li>
   * <li>{@link StringValGenerationServiceFactory#badString}</li>
   * <li>{@link UUIDValGenerationServiceFactory#goodUUID}, because of no bad generation service</li>
   * <li>register self as object val generation service</li>
   * </ol>
   *
   * @return
   */
  public static ObjectValGenerationService badObject() {
    DefaultObjectValGenerationService service =
        new DefaultObjectValGenerationService("BadObjectValGenerationService", goodObject());

    service.addPropertyGenerationService(badArray());
    service.addPropertyGenerationService(goodBinary());
    service.addPropertyGenerationService(goodBool());
    service.addPropertyGenerationService(goodByteArray());
    service.addPropertyGenerationService(goodDate());
    service.addPropertyGenerationService(goodDateTime());
    service.addPropertyGenerationService(badEmail());
    service.addPropertyGenerationService(badInteger());
    service.addPropertyGenerationService(badNumber());
    service.addPropertyGenerationService(badPassword());
    service.addPropertyGenerationService(badString());
    service.addPropertyGenerationService(goodUUID());
    // register self as ObjectVal generator
    service.addPropertyGenerationService(service);

    service.addSchemaValGenerators(new IgnoredValGenerator(false), new NullValGenerator(false));
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

    FixedObjectValGenerationService service = new FixedObjectValGenerationService();
    service.addGenerator(new FixedSchemaValGenerator(value));
    if (ArrayUtils.isNotEmpty(values)) {
      Arrays.stream(values)
          .forEach(g -> service.addGenerator(new FixedSchemaValGenerator(g)));
    }
    return service;
  }

}
