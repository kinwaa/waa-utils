/*
 * Project:    Waa Java Utilities Package
 *
 * FileName:   CollectionUtils.java
 * CreateTime: 2021-06-25 10:01:31
 */
package cc.waa.java.utils.lang;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public final class CollectionUtils {

   /**
    * 快速创建一个Map对象.
    *
    * @param keys
    * @param values
    * @return a new Map
    */
   public static Map<String, Object> toMap(final List<String> keys,
                                           final Object... values) {
      Map<String, Object> result = new HashMap<>(keys.size());

      for (int i = 0; i < keys.size(); i++) {
         result.put(keys.get(i), values[i]);
      }

      return result;
   }



   private CollectionUtils() {
   }
}
