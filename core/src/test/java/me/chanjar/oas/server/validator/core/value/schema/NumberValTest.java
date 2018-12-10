package me.chanjar.oas.server.validator.core.value.schema;

import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.*;

public class NumberValTest {

  @Test
  public void testGetValueString() {

    assertEquals(new NumberVal(BigDecimal.valueOf(1.1D)).getValueString(), "1.1");
    assertEquals(new NumberVal(BigDecimal.ONE).getValueString(), "1");

  }


}
