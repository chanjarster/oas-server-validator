package me.chanjar.oas.server.validator.core.value.schema;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ByteArrayValTest {

  @Test
  public void testGetValueString() {
    assertEquals(new ByteArrayVal(new Byte[] { 2, 4, 6, 8 }).getValueString(), "AgQGCA==");
  }
}
