package me.chanjar.oas.server.validator.core.valuegen;

public enum GenerationStrategy {

  /**
   * generate one mock data, which satisfies the spec
   */
  ONE_GOOD,

  /**
   * generate several mock datum, which satisfy the spec, including all edge case
   */
  ALL_GOOD,

  /**
   * generate one mock data which doesn't satisfy the spec
   */
  ONE_BAD,

  /**
   * generate several mock datum which don't satisfy the spec, including all possible cases
   */
  ALL_BAD
}
