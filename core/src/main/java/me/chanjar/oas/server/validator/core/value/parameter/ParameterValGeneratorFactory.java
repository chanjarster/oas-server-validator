package me.chanjar.oas.server.validator.core.value.parameter;

import static me.chanjar.oas.server.validator.core.valuegen.schema.array.ArrayValGenerationServiceFactory.badArray;
import static me.chanjar.oas.server.validator.core.valuegen.schema.array.ArrayValGenerationServiceFactory.goodArray;
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
import static me.chanjar.oas.server.validator.core.valuegen.schema.object.ObjectValGenerationServiceFactory.badObject;
import static me.chanjar.oas.server.validator.core.valuegen.schema.object.ObjectValGenerationServiceFactory.goodObject;
import static me.chanjar.oas.server.validator.core.valuegen.schema.password.PasswordValGenerationServiceFactory.badPassword;
import static me.chanjar.oas.server.validator.core.valuegen.schema.password.PasswordValGenerationServiceFactory.goodPassword;
import static me.chanjar.oas.server.validator.core.valuegen.schema.string.StringValGenerationServiceFactory.badString;
import static me.chanjar.oas.server.validator.core.valuegen.schema.string.StringValGenerationServiceFactory.goodString;
import static me.chanjar.oas.server.validator.core.valuegen.schema.uuid.UUIDValGenerationServiceFactory.badUUID;
import static me.chanjar.oas.server.validator.core.valuegen.schema.uuid.UUIDValGenerationServiceFactory.goodUUID;

public abstract class ParameterValGeneratorFactory {

  private ParameterValGeneratorFactory() {
    // singleton
  }

  public static ParameterValGenerator goodParameter() {

    DefaultParameterValGenerator service = new DefaultParameterValGenerator();
    service.addSchemaValGenerationServices(
        goodArray(),
        goodBinary(),
        goodBool(),
        goodByteArray(),
        goodDate(),
        goodDateTime(),
        goodEmail(),
        goodInteger(),
        goodNumber(),
        goodObject(),
        goodPassword(),
        goodString(),
        goodUUID()
    );
    return service;

  }

  public static ParameterValGenerator badParameter() {

    DefaultParameterValGenerator service = new DefaultParameterValGenerator();
    service.addSchemaValGenerationServices(
        badArray(),
        badBinary(),
        badBool(),
        badByteArray(),
        badDate(),
        badDateTime(),
        badEmail(),
        badInteger(),
        badNumber(),
        badObject(),
        badPassword(),
        badString(),
        badUUID()
    );
    return service;

  }

}
