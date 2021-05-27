/*
 * Project:    Waa Java Utilities Package
 *
 * FileName:   VariableNameUtils.java
 * CreateTime: 2021-05-27 13:07:23
 */
package cc.waa.java.utils.lang;

import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.join;
import static org.apache.commons.lang3.StringUtils.splitByCharacterType;
import static org.apache.commons.lang3.StringUtils.splitByCharacterTypeCamelCase;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isAlphanumeric;

/**
 * 处理变量名的工具类.
 *
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public final class VariableNameUtils {

   /**
    * @param name
    * @param fullCamel
    * @return name in camelCase
    */
   public static String toCamelCase(final String name,
                                    final boolean fullCamel) {
      List<String> parts = new ArrayList<>();

      for (String part : splitByCharacterType(name)) {
         if (!isAlphanumeric(part)) {
            continue;
         }

         part = part.toLowerCase();

         if (!parts.isEmpty() || fullCamel) {
            part = capitalize(part);
         }

         parts.add(part);
      }

      return join(parts, "");
   }

   /**
    * @param name
    * @param separator
    * @param upperCase
    * @return name in snakeCase
    */
   public static String toSnakeCase(final String name, final String separator,
                                    final boolean upperCase) {
      String[] parts = splitByCharacterTypeCamelCase(name);

      for (int i = 0; i < parts.length; i++) {
         parts[i] = upperCase ? parts[i].toUpperCase() : parts[i].toLowerCase();
      }

      return join(parts, separator);
   }



   private VariableNameUtils() {
   }
}
