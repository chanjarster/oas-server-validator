package me.chanjar.oas.server.validator.core.encoder.schema;

import me.chanjar.oas.server.validator.core.value.schema.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * see: https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#style-examples
 */
public abstract class StyleExampleData {

  private StyleExampleData() {
    // singleton
  }

  /**
   * array -> ["blue","black","brown"]
   *
   * @return
   */
  public static ArrayVal arrayVal() {
    return new ArrayVal(
        new SchemaVal[] { new StringVal("blue"), new StringVal("black"), new StringVal("brown") });
  }

  /**
   * <pre>
   *  array -> [
   *  "blue",
   *  "black",
   *  "brown",
   *  array -> ["blue","black","brown"],
   *  object -> { "R": 100, "G": 200, "B": 150 }
   * ]
   * </pre>
   *
   * @return
   */
  public static ArrayVal arrayValComplex() {

    return new ArrayVal(
        new SchemaVal[] { new StringVal("blue"), new StringVal("black"), new StringVal("brown"), arrayVal(),
            objectVal() });

  }

  /**
   * binary -> "blue"
   *
   * @return
   */
  public static BinaryVal binaryVal() {
    return new BinaryVal("blue");
  }

  /**
   * boolean -> true
   *
   * @return
   */
  public static BooleanVal booleanVal() {
    return new BooleanVal(true);
  }

  /**
   * byteArray -> [2, 4, 6, 8] <br>
   * string value is "AgQGCA=="
   *
   * @return
   */
  public static ByteArrayVal byteArrayVal() {
    return new ByteArrayVal(new Byte[] { 2, 4, 6, 8 });
  }

  /**
   * dateTime -> 2018-12-10 16:24:15 +08:00 <br>
   * string value is "2018-12-10T16:24:15.000+08:00"
   *
   * @return
   */
  public static DateTimeVal dateTimeVal() {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
    Date date = null;
    try {
      date = dateFormat.parse("2018-12-10 16:24:15 +0800");
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }

    return new DateTimeVal(date);
  }

  /**
   * date -> 2018-12-10 <br>
   * string value is "2018-12-10"
   *
   * @return
   */
  public static DateVal dateVal() {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = null;
    try {
      date = dateFormat.parse("2018-12-10");
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }

    return new DateVal(date);
  }

  /**
   * email -> "blue"
   *
   * @return
   */
  public static EmailVal emailVal() {
    return new EmailVal("blue");
  }

  /**
   * integer -> 100
   *
   * @return
   */
  public static IntegerVal integerVal() {
    return new IntegerVal(100);
  }

  /**
   * number -> 1.1
   *
   * @return
   */
  public static NumberVal numberVal() {
    return new NumberVal(BigDecimal.valueOf(1.1D));
  }

  /**
   * object -> { "R": 100, "G": 200, "B": 150 }
   *
   * @return
   */
  public static ObjectVal objectVal() {

    Map<String, SchemaVal> map = new LinkedHashMap<>();
    map.put("R", new IntegerVal(100));
    map.put("G", new IntegerVal(200));
    map.put("B", new IntegerVal(150));

    return new ObjectVal(map);

  }

  /**
   * <pre>
   * object -> {
   *   "R": 100,
   *   "G": 200,
   *   "B": 150,
   *   "array": ["blue", "black", "brown"],
   *   "object": { "R": 100, "G": 200, "B": 150 }
   * }
   * </pre>
   *
   * @return
   */
  public static ObjectVal objectValComplex() {

    Map<String, SchemaVal> map = new LinkedHashMap<>();
    map.put("R", new IntegerVal(100));
    map.put("G", new IntegerVal(200));
    map.put("B", new IntegerVal(150));
    map.put("array", arrayVal());
    map.put("object", objectVal());

    return new ObjectVal(map);

  }

  /**
   * password -> blue
   *
   * @return
   */
  public static PasswordVal passwordVal() {
    return new PasswordVal("blue");
  }

  /**
   * string -> "blue"
   */
  public static StringVal stringVal() {
    return new StringVal("blue");
  }

  /**
   * uuid -> "blue"
   *
   * @return
   */
  public static UUIDVal uuidVal() {
    return new UUIDVal("blue");
  }

}
