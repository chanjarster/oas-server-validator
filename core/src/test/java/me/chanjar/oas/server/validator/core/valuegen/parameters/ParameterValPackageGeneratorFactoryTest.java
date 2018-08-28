package me.chanjar.oas.server.validator.core.valuegen.parameters;

import me.chanjar.oas.server.validator.core.valuegen.parameters.ParameterValPackageGenerator;
import org.testng.annotations.Test;

import static me.chanjar.oas.server.validator.core.valuegen.parameters.ParameterValPackageGeneratorFactory.badParameters;
import static me.chanjar.oas.server.validator.core.valuegen.parameters.ParameterValPackageGeneratorFactory.goodParameters;
import static org.apache.commons.lang3.reflect.FieldUtils.readField;
import static org.assertj.core.api.Assertions.assertThat;

public class ParameterValPackageGeneratorFactoryTest {

  @Test
  public void testGoodParameters() throws IllegalAccessException {
    ParameterValPackageGenerator generator = goodParameters();
    assertThat(readField(generator, "defaultParameterValPackageGenerator", true)).isEqualTo(generator);
  }

  @Test
  public void testBadParameters() throws IllegalAccessException {
    ParameterValPackageGenerator generator = badParameters();
    assertThat(readField(generator, "defaultParameterValPackageGenerator", true)).isNotEqualTo(generator);
  }

}
