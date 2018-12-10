package me.chanjar.oas.server.validator.core.value.schema;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PasswordValTest {

  @Test
  public void testGetValueString() {
    assertEquals(new PasswordVal("*(IU7y%4ThJi%%$").getValueString(), "*(IU7y%4ThJi%%$");
  }

}
