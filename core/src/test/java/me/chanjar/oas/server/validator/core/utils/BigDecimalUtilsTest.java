package me.chanjar.oas.server.validator.core.utils;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.assertEquals;

public class BigDecimalUtilsTest {

  @DataProvider
  public Object[][] testFindIntegerMultipleOfData() {
    return new Object[][] {
        new Object[] { new BigDecimal(1), 1, true, 1L },
        new Object[] { new BigDecimal(1), 1, false, 1L },

        new Object[] { new BigDecimal(1), 0, true, 0L },
        new Object[] { new BigDecimal(1), 0, false, 0L },

        new Object[] { BigDecimal.valueOf(2.5), 1, true, 5L },
        new Object[] { BigDecimal.valueOf(2.5), 1, false, 0L },

        new Object[] { BigDecimal.valueOf(2.5), 5, true, 15L },
        new Object[] { BigDecimal.valueOf(2.5), 5, false, 10L },

    };
  }

  @Test(dataProvider = "testFindIntegerMultipleOfData")
  public void testFindIntegerMultipleOf(BigDecimal multipleOf, int startMultiplier, boolean direction, long expected) {
    BigDecimal result = BigDecimalUtils.findIntegerMultipleOf(multipleOf, startMultiplier, direction);
    assertEquals(result.longValue(), expected);
  }

  @DataProvider
  public Object[][] testFindIntegerGtData() {
    return new Object[][] {
        new Object[] { new BigDecimal(1), 2 },
        new Object[] { BigDecimal.valueOf(1.1D), 2 },
        new Object[] { BigDecimal.valueOf(1.0D), 2 },
    };
  }

  @Test(dataProvider = "testFindIntegerGtData")
  public void testFindIntegerGt(BigDecimal start, long expected) {
    assertEquals(BigDecimalUtils.findIntegerGt(start).longValue(), expected);
  }

  @DataProvider
  public Object[][] testFindIntegerGteData() {
    return new Object[][] {
        new Object[] { new BigDecimal(1), 1 },
        new Object[] { BigDecimal.valueOf(1.1D), 2 },
        new Object[] { BigDecimal.valueOf(1.0D), 1 },
    };
  }

  @Test(dataProvider = "testFindIntegerGteData")
  public void testFindIntegerGte(BigDecimal start, long expected) {
    assertEquals(BigDecimalUtils.findIntegerGte(start).longValue(), expected);
  }

  @DataProvider
  public Object[][] testFindIntegerLtData() {
    return new Object[][] {
        new Object[] { new BigDecimal(1), 0 },
        new Object[] { BigDecimal.valueOf(1.1D), 1 },
        new Object[] { BigDecimal.valueOf(1.0D), 0 },
    };
  }

  @Test(dataProvider = "testFindIntegerLtData")
  public void testFindIntegerLt(BigDecimal start, long expected) {
    assertEquals(BigDecimalUtils.findIntegerLt(start).longValue(), expected);
  }

  @DataProvider
  public Object[][] testFindIntegerLteData() {
    return new Object[][] {
        new Object[] { new BigDecimal(1), 1 },
        new Object[] { BigDecimal.valueOf(1.1D), 1 },
        new Object[] { BigDecimal.valueOf(1.0D), 1 },
    };
  }

  @Test(dataProvider = "testFindIntegerLteData")
  public void testFindIntegerLte(BigDecimal start, long expected) {
    assertEquals(BigDecimalUtils.findIntegerLte(start).longValue(), expected);
  }


  @DataProvider
  public Object[][] testIsIntegerData() {
    return new Object[][] {
        new Object[] { BigDecimal.valueOf(1.1D), false },
        new Object[] { BigDecimal.valueOf(1.00001D), false },
        new Object[] { BigDecimal.valueOf(1.0000D), true },
        new Object[] { BigDecimal.valueOf(1), true },
        new Object[] { new BigDecimal(1), true },
        new Object[] { new BigDecimal(1L), true },
    };
  }

  @Test(dataProvider = "testIsIntegerData")
  public void testIsInteger(BigDecimal bigDecimal, boolean expected) {
    assertEquals(BigDecimalUtils.isInteger(bigDecimal), expected);
  }

}
