package me.chanjar.oas.server.validator.core.valuegen.mediatype;

import io.swagger.v3.oas.models.media.Encoding;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.StringSchema;
import me.chanjar.oas.server.validator.core.value.mediatype.MediaTypeVal;
import me.chanjar.oas.server.validator.core.value.schema.StringVal;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValCons;
import me.chanjar.oas.server.validator.core.valuegen.schema.SchemaValGenerationService;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

public class DefaultMediaTypeValGeneratorTest {

  @Test
  public void testGenerateOne() {

    MediaType mediaType = createMediaType();

    SchemaValGenerationService mockGenerationService = mock(SchemaValGenerationService.class);
    when(mockGenerationService.supports(any())).thenReturn(true);
    when(mockGenerationService.generateOne(any(), any())).thenReturn(new StringVal("abc"));

    DefaultMediaTypeValGenerator generator = new DefaultMediaTypeValGenerator();
    generator.addSchemaValGenerationServices(mockGenerationService);

    MediaTypeVal mediaTypeVal = generator.generateOne(mediaType);

    assertEquals(mediaTypeVal.getSchemaVal(), new StringVal("abc"));

    assertEquals(mediaTypeVal.getEncoding(), mediaType.getEncoding());

    verify(mockGenerationService).supports(new StringSchema());
    verify(mockGenerationService).generateOne(new StringSchema(), new SchemaValCons(true, false));

  }

  @Test
  public void testGenerateAll() {

    MediaType mediaType = createMediaType();

    SchemaValGenerationService mockGenerationService = mock(SchemaValGenerationService.class);
    when(mockGenerationService.supports(any())).thenReturn(true);
    when(mockGenerationService.generateAll(any(), any())).thenReturn(Collections.singletonList(new StringVal("abc")));

    DefaultMediaTypeValGenerator generator = new DefaultMediaTypeValGenerator();
    generator.addSchemaValGenerationServices(mockGenerationService);

    List<MediaTypeVal> parameterVals = generator.generateAll(mediaType);
    assertEquals(parameterVals.size(), 1);

    MediaTypeVal parameterVal = parameterVals.get(0);
    assertEquals(parameterVal.getSchemaVal(), new StringVal("abc"));

    verify(mockGenerationService).supports(new StringSchema());
    verify(mockGenerationService).generateAll(new StringSchema(), new SchemaValCons(true, false));

  }

  @Test(expectedExceptions = MediaTypeValGenerationException.class,
      expectedExceptionsMessageRegExp = "Could not find SchemaValGenerationService.*")
  public void testGenerateOneThrowException() {

    SchemaValGenerationService mockGenerationService = mock(SchemaValGenerationService.class);
    when(mockGenerationService.supports(any())).thenReturn(false);

    DefaultMediaTypeValGenerator generator = new DefaultMediaTypeValGenerator();
    generator.addSchemaValGenerationServices(mockGenerationService);

    generator.generateOne(createMediaType());

  }

  
  private MediaType createMediaType() {
    MediaType mediaType = new MediaType();

    Encoding encoding = new Encoding();
    mediaType.setEncoding(Collections.singletonMap("xyz", encoding));

    mediaType.setSchema(new StringSchema());
    return mediaType;
  }

}
