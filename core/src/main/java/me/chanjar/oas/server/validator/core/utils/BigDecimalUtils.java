package me.chanjar.oas.server.validator.core.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class BigDecimalUtils {

  private BigDecimalUtils() {
    // singleton
  }

  /**
   * Find a integer number which is multiple of <code>multipleOf</code>
   *
   * @param multipleOf      the result is only valid if division by this keyword's value results in an integer.
   * @param startMultiplier will start with multipleOf * startMultiplier
   * @param direction       if true, startMultiplier will add 1 for each try;
   *                        if false, startMultiplier will be minus 1 for each try
   * @return
   */
  public static BigDecimal findIntegerMultipleOf(BigDecimal multipleOf, int startMultiplier, boolean direction) {

    int start = startMultiplier;
    BigDecimal result;

    do {
      result = multipleOf.multiply(new BigDecimal(start));
      start = direction ? start + 1 : start - 1;
    } while (!isInteger(result));
    return result;
  }

  /**
   * Find a integer number which is greater than <code>start</code>
   *
   * @param start
   * @return
   */
  public static BigDecimal findIntegerGt(BigDecimal start) {
    return start.add(BigDecimal.ONE).setScale(0, RoundingMode.FLOOR);
  }

  /**
   * Find a integer number which is greater than or equal to <code>start</code>
   *
   * @param start
   * @return
   */
  public static BigDecimal findIntegerGte(BigDecimal start) {
    BigDecimal result = start;
    if (!isInteger(result)) {
      result = findIntegerGt(result);
    }
    return result;
  }

  /**
   * Find a integer number which is less than <code>start</code>
   *
   * @param start
   * @return
   */
  public static BigDecimal findIntegerLt(BigDecimal start) {
    return start.subtract(BigDecimal.ONE).setScale(0, RoundingMode.CEILING);
  }

  /**
   * Find a integer number which is less than or equal to <code>start</code>
   *
   * @param start
   * @return
   */
  public static BigDecimal findIntegerLte(BigDecimal start) {
    BigDecimal result = start;
    if (!isInteger(result)) {
      result = findIntegerLt(result);
    }
    return result;
  }

  /**
   * Tell whether a BigDecimal is an integer.
   *
   * @param bigDecimal
   * @return
   */
  public static boolean isInteger(BigDecimal bigDecimal) {
    return new BigDecimal(bigDecimal.longValue()).compareTo(bigDecimal) == 0;
  }
}
