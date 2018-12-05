package me.chanjar.oas.server.validator.core.valuegen.mediatype;

import io.swagger.v3.oas.models.media.MediaType;
import me.chanjar.oas.server.validator.core.value.mediatype.MediaTypeVal;

import java.util.List;

public interface MediaTypeValGenerator {

  /**
   * Generate one {@link MediaTypeVal}, which satisfies spec
   *
   * @param mediaType
   * @return
   */
  MediaTypeVal generateOne(MediaType mediaType);

  /**
   * Generate all {@link MediaTypeVal}s, which satisfy spec, including all edge cases
   *
   * @param mediaType
   * @return
   */
  List<MediaTypeVal> generateAll(MediaType mediaType);

}
