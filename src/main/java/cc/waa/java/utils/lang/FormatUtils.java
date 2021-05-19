/*
 * Project:    Waa Java Utilities Package
 *
 * FileName:   FormatUtils.java
 * CreateTime: 2021-05-20 00:00:16
 */
package cc.waa.java.utils.lang;

/**
 * 汇集多种格式化方法，以便同时import使用.
 *
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public final class FormatUtils {

   /**
    * 格式化字符串.
    *
    * @param format 格式字符串
    * @param args 参数
    * @return 已格式化的字符串
    */
   public static String format(final String format, final Object... args) {
      return String.format(format, args);
   }



   private FormatUtils() {
   }
}
