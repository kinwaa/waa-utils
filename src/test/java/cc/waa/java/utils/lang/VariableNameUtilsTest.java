/*
 * Project:    Waa Java Utilities Package
 *
 * FileName:   VariableNameUtilsTest.java
 * CreateTime: 2021-05-27 14:39:03
 */
package cc.waa.java.utils.lang;

import static cc.waa.java.utils.lang.VariableNameUtils.toCamelCase;
import static cc.waa.java.utils.lang.VariableNameUtils.toSnakeCase;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@RunWith(Parameterized.class)
public class VariableNameUtilsTest {

   @Parameters
   public static Collection<Object[]> prepareData() {
      List<Object[]> params = new ArrayList<>();

      params.add(new Object[] { 3, "testSomeCase", "test_some_case" });
      params.add(new Object[] { 1, "ASFRules", "asf_rules" });
      params.add(new Object[] { 3, "foo200Bar", "foo_200_bar" });

      return params;
   }

   private int control;

   private String camelCase;

   private String snakeCase;

   public VariableNameUtilsTest(int control, String camelCase, String snakeCase) {
      this.control = control;
      this.camelCase = camelCase;
      this.snakeCase = snakeCase;
   }

   @Test
   public void toSnakeCaseTest() {
      if ((this.control & 1) == 1) {
         assertEquals(this.snakeCase, toSnakeCase(this.camelCase, "_", false));
      }
   }

   @Test
   public void toCamelCaseTest() {
      if ((this.control & 2) == 2) {
         assertEquals(this.camelCase, toCamelCase(this.snakeCase, false));
      }
   }
}
