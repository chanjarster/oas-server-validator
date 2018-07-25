package me.chanjar.oas.server.validator.core.value.schema;

/**
 * A special SchemaVal which means it should be ignored.
 * <p>For example, if a QueryParameterVal contains IgnoredVal, it should not be used to build query parameter</p>
 * <p>If a ObjectVal contains a property of IgnoredVal, the property should be ignored before being serialized to json</p>
 */
public class IgnoredVal extends SchemaVal {

  public static final IgnoredVal INSTANCE = new IgnoredVal();

  private IgnoredVal() {
    super(IgnoredVal.class);
  }

}
