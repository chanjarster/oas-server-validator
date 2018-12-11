package me.chanjar.oas.server.validator.core.encoder;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PercentEncoderTest {


  @DataProvider
  public Object[][] testDontAllowReservedData() {
    return new Object[][] {
        new String[] {"ABCDEFGHIJKLMNOPQRSTUVWXYZ", "ABCDEFGHIJKLMNOPQRSTUVWXYZ"},
        new String[] {"abcdefghijklmnopqrstuvwxyz", "abcdefghijklmnopqrstuvwxyz"},
        new String[] {"0123456789", "0123456789"},
        new String[] {"-_.~", "-_.~"},
        new String[] {":/?#[]@!$&'()*+,;=", "%3A%2F%3F%23%5B%5D%40%21%24%26%27%28%29%2A%2B%2C%3B%3D"},
        new String[] {" %", "%20%25"},
    };
  }

  @Test(dataProvider = "testDontAllowReservedData")
  public void testDontAllowReserved(String string, String expected) {
    assertEquals(PercentEncoder.DONT_ALLOW_RESERVED.encode(string), expected);
  }

  @DataProvider
  public Object[][] testAllowReservedData() {
    return new Object[][] {
        new String[] {"ABCDEFGHIJKLMNOPQRSTUVWXYZ", "ABCDEFGHIJKLMNOPQRSTUVWXYZ"},
        new String[] {"abcdefghijklmnopqrstuvwxyz", "abcdefghijklmnopqrstuvwxyz"},
        new String[] {"0123456789", "0123456789"},
        new String[] {"-_.~", "-_.~"},
        new String[] {":/?#[]@!$&'()*+,;=", ":/?#[]@!$&'()*+,;="},
        new String[] {" ", "%20"},
    };
  }

  @Test(dataProvider = "testAllowReservedData")
  public void testEncode2(String string, String expected) {
    assertEquals(PercentEncoder.ALLOW_RESERVED.encode(string), expected);

  }
}
