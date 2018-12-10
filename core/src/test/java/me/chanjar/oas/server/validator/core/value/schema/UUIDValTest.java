package me.chanjar.oas.server.validator.core.value.schema;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UUIDValTest {

  @Test
  public void testGetValueString() {
    assertEquals(new UUIDVal("abc-xyz-ufwdeg").getValueString(), "abc-xyz-ufwdeg");
  }

}
