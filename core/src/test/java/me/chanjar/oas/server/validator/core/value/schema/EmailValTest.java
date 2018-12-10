package me.chanjar.oas.server.validator.core.value.schema;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class EmailValTest {

  @Test
  public void testGetValueString() {
    assertEquals(new EmailVal("abc@xyz.com").getValueString(), "abc@xyz.com");
  }

}
