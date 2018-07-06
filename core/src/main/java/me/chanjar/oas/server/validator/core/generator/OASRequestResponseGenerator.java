package me.chanjar.oas.server.validator.core.generator;

import me.chanjar.oas.server.validator.core.interaction.OASRequestResponse;

import java.util.List;

public interface OASRequestResponseGenerator {

  List<OASRequestResponse> create(OASRequestGenParam requestGenerationParam,
      OASResponseGenParam responseGenerationParam);

}
