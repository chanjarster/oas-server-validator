package me.chanjar.oas.server.validator.core.value.schema;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class BooleanValTest {

  @Test
  public void testGetValueString() {

    assertEquals(new BooleanVal(true).getValueString(), "true");
    assertEquals(new BooleanVal(false).getValueString(), "false");

  }
}
