/*
 * Project:    Waa Java Utilities Package
 *
 * FileName:   NumberUtilsTest.java
 * CreateTime: 2021-08-12 11:40:44
 */
package cc.waa.java.utils.lang;

import static cc.waa.java.utils.lang.NumberUtils.intOf;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import lombok.RequiredArgsConstructor;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@RequiredArgsConstructor
@RunWith(Parameterized.class)
public class NumberUtilsTest {

   @Parameters
   public static Collection<Object[]> prepareData() {
      List<Object[]> params = new ArrayList<>();

      params.add(new Object[] { 1, "123", 123 });
      params.add(new Object[] { 1, 456, 456 });
      params.add(new Object[] { 1, new BigDecimal("9876"), 9876 });
      params.add(new Object[] { 1, "avc", 0 });

      return params;
   }

   private final int control;

   private final Object value;

   private final int expected;

   @Test
   public void intOfTest() {
      if ((this.control & 1) == 1) {
         assertEquals(expected, intOf(value));
      }
   }
}
