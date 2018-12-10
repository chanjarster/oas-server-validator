package me.chanjar.oas.server.validator.core.value.schema;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class IntegerValTest {

  @Test
  public void testGetValueString() {
    assertEquals(new IntegerVal(2).getValueString(), "2");
  }
}
