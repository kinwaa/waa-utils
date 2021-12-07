/*
 * Project:    Waa Java Utilities Package
 *
 * FileName:   CollectionUtils.java
 * CreateTime: 2021-06-25 10:01:31
 */
package cc.waa.java.utils.lang;

import static org.apache.commons.beanutils.PropertyUtils.getProperty;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@Slf4j
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

   /**
    * @param <K>
    * @param <V>
    * @param col
    * @param keyName
    * @param keyType
    * @return 保存原集合对象的Map
    */
   public static <K, V> Map<K, V> toMap(final Collection<V> col,
                                        final String keyName,
                                        final Class<K> keyType) {
      if (col == null) {
         return null;
      }

      Map<K, V> result = new HashMap<K, V>(col.size());

      try {
         for (V value : col) {
            K key = keyType.cast(getProperty(value, keyName));

            result.put(key, value);
         }
      } catch (ReflectiveOperationException e) {
         log.error("pick key fail", e);

         return null;
      }

      return result;
   }

   /**
    * @param <I>
    * @param <C>
    * @param col
    * @param propertyName
    * @param itemType
    * @param colType
    * @return 提取的属性的集合
    */
   public static <I, C extends Collection<I>> C
   pickProperty(final Collection<?> col, final String propertyName,
                final Class<I> itemType, final Class<C> colType) {
      if (col == null) {
         return null;
      }

      try {
         C result = colType.getDeclaredConstructor().newInstance();

         for (Object obj : col) {
            result.add(itemType.cast(getProperty(obj, propertyName)));
         }

         return result;
      } catch (ReflectiveOperationException e) {
         log.error("pick property fail", e);

         return null;
      }
   }



   private CollectionUtils() {
   }
}
