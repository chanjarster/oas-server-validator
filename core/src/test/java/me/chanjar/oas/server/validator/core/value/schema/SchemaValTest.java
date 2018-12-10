package me.chanjar.oas.server.validator.core.value.schema;

import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;

import static org.testng.Assert.assertEquals;

public class SchemaValTest {

  private ArrayVal arrayVal = new ArrayVal(new SchemaVal[] { new StringVal("a") });
  private BinaryVal binaryVal = new BinaryVal("abc");
  private BooleanVal booleanVal = new BooleanVal(false);
  private ByteArrayVal byteArrayVal = new ByteArrayVal(new Byte[] { new Byte((byte) 1) });
  private DateTimeVal dateTimeVal = new DateTimeVal(new Date());
  private DateVal dateVal = new DateVal(new Date());
  private EmailVal emailVal = new EmailVal("abc");
  private IntegerVal integerVal = new IntegerVal(1);
  private NumberVal numberVal = new NumberVal(BigDecimal.ONE);
  private ObjectVal objectVal = new ObjectVal(Collections.emptyMap());
  private PasswordVal passwordVal = new PasswordVal("abc");
  private StringVal stringVal = new StringVal("a");
  private UUIDVal uuidVal = new UUIDVal("abc");

  @Test
  public void testIsNull() {

    assertEquals(SchemaVal.isNull(SchemaVal.NULL_VAL), true);

    assertEquals(SchemaVal.isNull(SchemaVal.IGNORED_VAL), false);
    assertEquals(SchemaVal.isNull(arrayVal), false);
    assertEquals(SchemaVal.isNull(binaryVal), false);
    assertEquals(SchemaVal.isNull(booleanVal), false);
    assertEquals(SchemaVal.isNull(byteArrayVal), false);
    assertEquals(SchemaVal.isNull(dateTimeVal), false);
    assertEquals(SchemaVal.isNull(dateVal), false);
    assertEquals(SchemaVal.isNull(emailVal), false);
    assertEquals(SchemaVal.isNull(integerVal), false);
    assertEquals(SchemaVal.isNull(numberVal), false);
    assertEquals(SchemaVal.isNull(objectVal), false);
    assertEquals(SchemaVal.isNull(passwordVal), false);
    assertEquals(SchemaVal.isNull(stringVal), false);
    assertEquals(SchemaVal.isNull(uuidVal), false);

  }

  @Test
  public void testIsIgnored() {

    assertEquals(SchemaVal.isIgnored(SchemaVal.NULL_VAL), false);

    assertEquals(SchemaVal.isIgnored(SchemaVal.IGNORED_VAL), true);

    assertEquals(SchemaVal.isIgnored(arrayVal), false);
    assertEquals(SchemaVal.isIgnored(binaryVal), false);
    assertEquals(SchemaVal.isIgnored(booleanVal), false);
    assertEquals(SchemaVal.isIgnored(byteArrayVal), false);
    assertEquals(SchemaVal.isIgnored(dateTimeVal), false);
    assertEquals(SchemaVal.isIgnored(dateVal), false);
    assertEquals(SchemaVal.isIgnored(emailVal), false);
    assertEquals(SchemaVal.isIgnored(integerVal), false);
    assertEquals(SchemaVal.isIgnored(numberVal), false);
    assertEquals(SchemaVal.isIgnored(objectVal), false);
    assertEquals(SchemaVal.isIgnored(passwordVal), false);
    assertEquals(SchemaVal.isIgnored(stringVal), false);
    assertEquals(SchemaVal.isIgnored(uuidVal), false);

  }

  @Test
  public void testIsPrimitive() {

    assertEquals(SchemaVal.isPrimitive(SchemaVal.NULL_VAL), false);
    assertEquals(SchemaVal.isPrimitive(SchemaVal.IGNORED_VAL), false);

    assertEquals(SchemaVal.isPrimitive(arrayVal), false);

    assertEquals(SchemaVal.isPrimitive(binaryVal), true);
    assertEquals(SchemaVal.isPrimitive(booleanVal), true);
    assertEquals(SchemaVal.isPrimitive(byteArrayVal), true);
    assertEquals(SchemaVal.isPrimitive(dateTimeVal), true);
    assertEquals(SchemaVal.isPrimitive(dateVal), true);
    assertEquals(SchemaVal.isPrimitive(emailVal), true);
    assertEquals(SchemaVal.isPrimitive(integerVal), true);
    assertEquals(SchemaVal.isPrimitive(numberVal), true);

    assertEquals(SchemaVal.isPrimitive(objectVal), false);

    assertEquals(SchemaVal.isPrimitive(passwordVal), true);
    assertEquals(SchemaVal.isPrimitive(stringVal), true);
    assertEquals(SchemaVal.isPrimitive(uuidVal), true);
  }

  @Test
  public void testIsArray() {

    assertEquals(SchemaVal.isArray(SchemaVal.NULL_VAL), false);
    assertEquals(SchemaVal.isArray(SchemaVal.IGNORED_VAL), false);

    assertEquals(SchemaVal.isArray(arrayVal), true);

    assertEquals(SchemaVal.isArray(binaryVal), false);
    assertEquals(SchemaVal.isArray(booleanVal), false);
    assertEquals(SchemaVal.isArray(byteArrayVal), false);
    assertEquals(SchemaVal.isArray(dateTimeVal), false);
    assertEquals(SchemaVal.isArray(dateVal), false);
    assertEquals(SchemaVal.isArray(emailVal), false);
    assertEquals(SchemaVal.isArray(integerVal), false);
    assertEquals(SchemaVal.isArray(numberVal), false);
    assertEquals(SchemaVal.isArray(objectVal), false);
    assertEquals(SchemaVal.isArray(passwordVal), false);
    assertEquals(SchemaVal.isArray(stringVal), false);
    assertEquals(SchemaVal.isArray(uuidVal), false);

  }

  @Test
  public void testIsObject() {


    assertEquals(SchemaVal.isObject(SchemaVal.NULL_VAL), false);
    assertEquals(SchemaVal.isObject(SchemaVal.IGNORED_VAL), false);
    assertEquals(SchemaVal.isObject(arrayVal), false);

    assertEquals(SchemaVal.isObject(binaryVal), false);
    assertEquals(SchemaVal.isObject(booleanVal), false);
    assertEquals(SchemaVal.isObject(byteArrayVal), false);
    assertEquals(SchemaVal.isObject(dateTimeVal), false);
    assertEquals(SchemaVal.isObject(dateVal), false);
    assertEquals(SchemaVal.isObject(emailVal), false);
    assertEquals(SchemaVal.isObject(integerVal), false);
    assertEquals(SchemaVal.isObject(numberVal), false);

    assertEquals(SchemaVal.isObject(objectVal), true);

    assertEquals(SchemaVal.isObject(passwordVal), false);
    assertEquals(SchemaVal.isObject(stringVal), false);
    assertEquals(SchemaVal.isObject(uuidVal), false);


  }

}
