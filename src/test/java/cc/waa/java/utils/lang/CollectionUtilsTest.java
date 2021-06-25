/*
 * Project:    Waa Java Utilities Package
 *
 * FileName:   CollectionUtilsTest.java
 * CreateTime: 2021-06-25 10:32:16
 */
package cc.waa.java.utils.lang;

import static cc.waa.java.utils.lang.CollectionUtils.toMap;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.Map;

import org.junit.Test;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class CollectionUtilsTest {

   @Test
   public void toMapTest() {
      Object val1 = "a", val2 = new Date();

      Map<String, Object> e = toMap(asList("key1", "key2"), val1, val2);

      assertNotNull(e);
      assertEquals(2, e.size());
      assertEquals(val1, e.get("key1"));
      assertEquals(val2, e.get("key2"));
   }
}
