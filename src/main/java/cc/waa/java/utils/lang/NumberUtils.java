/*
 * Project:    Waa Java Utilities Package
 *
 * FileName:   NumberUtils.java
 * CreateTime: 2021-08-12 11:28:47
 */
package cc.waa.java.utils.lang;

import static java.lang.Integer.parseInt;
import static org.apache.commons.lang3.StringUtils.isNumeric;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public final class NumberUtils {

   /**
    * 将不同对象转化为int类型.
    *
    * @param value
    * @param defaultValue
    * @return int value
    */
   public static int intOf(final Object value, final int defaultValue) {
      if (value instanceof Number) {
         return ((Number) value).intValue();
      } else if (value instanceof CharSequence) {
         CharSequence cs = (CharSequence) value;

         if (isNumeric(cs)) {
            return parseInt(cs.toString());
         }
      }

      return defaultValue;
   }

   /**
    * @param value
    * @return int value
    */
   public static int intOf(final Object value) {
      return intOf(value, 0);
   }



   private NumberUtils() {
   }
}
