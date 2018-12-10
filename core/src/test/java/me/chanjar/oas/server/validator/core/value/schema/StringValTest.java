package me.chanjar.oas.server.validator.core.value.schema;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class StringValTest {

  @Test
  public void testGetValueString() {
    assertEquals(new StringVal("abc").getValueString(), "abc");
  }

}
