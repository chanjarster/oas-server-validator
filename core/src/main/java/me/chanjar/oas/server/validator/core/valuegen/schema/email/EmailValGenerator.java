package me.chanjar.oas.server.validator.core.valuegen.schema.email;

import io.swagger.v3.oas.models.media.EmailSchema;
import me.chanjar.oas.server.validator.core.value.schema.EmailVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerator;

public interface EmailValGenerator extends SchemaValGenerator<EmailSchema, EmailVal> {
}
