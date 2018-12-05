package me.chanjar.oas.server.validator.core.valuegen.requestbody;

import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.parameters.RequestBody;
import me.chanjar.oas.server.validator.core.value.mediatype.MediaTypeVal;
import me.chanjar.oas.server.validator.core.value.requestbody.RequestBodyVal;
import me.chanjar.oas.server.validator.core.valuegen.mediatype.MediaTypeValGenerator;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class DefaultRequestBodyValGenerator implements RequestBodyValGenerator {

  private final boolean goodMode;

  private final MediaTypeValGenerator mediaTypeValGenerator;

  public DefaultRequestBodyValGenerator(boolean goodMode,
      MediaTypeValGenerator mediaTypeValGenerator) {
    this.goodMode = goodMode;
    this.mediaTypeValGenerator = mediaTypeValGenerator;
  }

  @Override
  public RequestBodyVal generateOne(String contentType, RequestBody requestBody) {

    MediaType mediaType = requestBody.getContent().get(contentType);
    if (mediaType == null) {
      throw new RequestBodyValGenerationException(
          MessageFormat.format("Could not find mediaType. ContentType: [{0}]", contentType)
      );
    }

    return new RequestBodyVal(contentType, mediaTypeValGenerator.generateOne(mediaType));

  }

  @Override
  public List<RequestBodyVal> generateAll(String contentType, RequestBody requestBody) {

    MediaType mediaType = requestBody.getContent().get(contentType);
    if (mediaType == null) {
      throw new RequestBodyValGenerationException(
          MessageFormat.format("Could not find mediaType. ContentType: [{0}]", contentType)
      );
    }

    List<RequestBodyVal> result = new ArrayList<>();

    List<MediaTypeVal> mediaTypeVals = mediaTypeValGenerator.generateAll(mediaType);

    mediaTypeVals.stream()
        .map(m -> new RequestBodyVal(contentType, m))
        .forEach(r -> result.add(r));

    if (needGenIgnoredVal(requestBody)) {
      result.add(RequestBodyVal.IGNORED_VAL);
    }
    return result;
  }

  private boolean needGenIgnoredVal(RequestBody requestBody) {
    if (goodMode) {
      return !Boolean.TRUE.equals(requestBody.getRequired());
    }
    return Boolean.TRUE.equals(requestBody.getRequired());
  }

}
