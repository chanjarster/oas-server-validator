package me.chanjar.oas.server.validator.core.http.generator;

import me.chanjar.oas.server.validator.core.http.OASRequestResponse;

import java.util.List;

public interface OASRequestResponseGenerator {

  List<OASRequestResponse> create(OASRequestGenParam requestGenerationParam,
      OASResponseGenParam responseGenerationParam);

}
