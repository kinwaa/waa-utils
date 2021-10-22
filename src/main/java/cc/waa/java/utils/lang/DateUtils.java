/*
 * Project:    Waa Java Utilities Package
 *
 * FileName:   DateUtils.java
 * CreateTime: 2021-10-19 10:44:35
 */
package cc.waa.java.utils.lang;

import java.util.Date;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public final class DateUtils {

   /**
    * @return 当前时间
    */
   public static Date now() {
      return new Date();
   }

   private DateUtils() {
   }
}
