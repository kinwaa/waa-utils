/*
 * Project:    Waa Java Utilities Package
 *
 * FileName:   JpaUtils.java
 * CreateTime: 2021-05-26 12:52:24
 */
package cc.waa.java.utils.jpa;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.substringAfter;
import static org.apache.commons.lang3.StringUtils.substringBefore;
import static org.apache.commons.lang3.StringUtils.trim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public final class JpaUtils {

   private static void appendContent(final StringBuilder sql,
                                     final String content) {
      int length = sql.length();

      if (length > 0 && sql.charAt(length - 1) == ',') {
         sql.append(' ');
      }

      sql.append(content.replaceAll("[\\t\\s]+", " ")); // 压缩中间多余的空格
   }

   private static String[] splitSql(final InputStream is) throws IOException {
      String curLine;
      List<String> result = new ArrayList<>();
      boolean inMultiLineComment = false;

      try (Reader isr = new InputStreamReader(is, UTF_8);
           BufferedReader in = new BufferedReader(isr)) {
         StringBuilder sql = new StringBuilder();

         while ((curLine = in.readLine()) != null) {
            while (!isEmpty(curLine)) {
               if (inMultiLineComment) { // 找结束
                  if (curLine.indexOf("*/") >= 0) {
                     curLine = substringAfter(curLine, "*/");

                     inMultiLineComment = false;

                     continue;
                  } else {
                     curLine = null;

                     continue;
                  }
               }

               // 判断单行注释
               if (curLine.indexOf("--") >= 0) {
                  appendContent(sql, trim(substringBefore(curLine, "--")));
                  curLine = null;

                  continue;
               }

               // 判断多行注释
               if (curLine.indexOf("/*") >= 0) {
                  appendContent(sql, trim(substringBefore(curLine, "/*")));
                  curLine = substringAfter(curLine, "/*");

                  inMultiLineComment = true;

                  continue;
               }

               // 判断sql结束
               if (curLine.indexOf(';') >= 0) {
                  appendContent(sql, trim(substringBefore(curLine, ';')));
                  curLine = substringAfter(curLine, ';');

                  result.add(sql.toString());
                  sql.delete(0, sql.length());

                  continue;
               }

               appendContent(sql, trim(curLine));
               curLine = null;
            }
         }
      }

      return result.toArray(new String[result.size()]);
   }

   /**
    * 执行sql脚本文件.
    *
    * @param factory
    * @param resourceStream
    * @return 成功执行的语句数量
    * @throws IOException
    */
   public static int executeScript(final EntityManagerFactory factory,
                                   final InputStream resourceStream)
         throws IOException {
      EntityManager em = null;

      try {
         em = factory.createEntityManager();

         em.getTransaction().begin();
         for (String sql : splitSql(resourceStream)) {
            em.createNativeQuery(sql).executeUpdate();
         }
         em.getTransaction().commit();
      } finally {
         em.close();
      }

      return 0;
   }



   private JpaUtils() {
   }
}
