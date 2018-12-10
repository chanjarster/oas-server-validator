package me.chanjar.oas.server.validator.core.value.schema;

import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.testng.Assert.assertEquals;

public class DateTimeValTest {

  @Test
  public void testGetValueString() throws ParseException {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
    Date date = dateFormat.parse("2018-12-10 16:24:15 +0800");

    DateTimeVal dateTimeVal = new DateTimeVal(date);
    assertEquals(dateTimeVal.getValueString(), "2018-12-10T16:24:15.000+08:00");
  }
}
