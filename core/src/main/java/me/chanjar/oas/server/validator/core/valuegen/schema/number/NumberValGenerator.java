package me.chanjar.oas.server.validator.core.valuegen.schema.number;

import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.NumberSchema;
import me.chanjar.oas.server.validator.core.value.schema.IntegerVal;
import me.chanjar.oas.server.validator.core.value.schema.NumberVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerator;

public interface NumberValGenerator extends SchemaValGenerator<NumberSchema, NumberVal> {

}
