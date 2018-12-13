package me.chanjar.oas.server.validator.core.engine;

import me.chanjar.oas.server.validator.core.http.OASRequest;

/**
 *
 * @param <RES> Response type
 */
public interface OASRequestRunner<RES> {


  RES doRequest(OASRequest oasRequest) throws Exception;

}
