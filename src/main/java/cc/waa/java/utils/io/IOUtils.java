/*
 * Project:    Waa Java Utilities Package
 *
 * FileName:   IOUtils.java
 * CreateTime: 2021-05-19 23:04:43
 */
package cc.waa.java.utils.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public final class IOUtils {

   /** 默认的缓冲大小（8KB）. */
   private static final int BUFFER_SIZE = 8192;

   /**
    * 将一个InputStream的数据同时复制到多个OutputStream去.
    *
    * @param is 用于读取数据的InputStram
    * @param oss 可以写入到多个OutputStream。没有传入任何OutputStream也不会抛出异常，只是返回0L
    * @return 总共复制了的字节数（并非多个OutputStream实例的总数，只按InputStream的读取数量）
    * @throws IOException
    */
   public static long copyMulti(final InputStream is, final OutputStream... oss)
         throws IOException {
      if (oss == null || oss.length <= 0) {
         return 0L;
      }

      long count = 0;
      byte[] buffer = new byte[BUFFER_SIZE];
      int readed = 0;

      while ((readed = is.read(buffer)) > 0) {
         for (OutputStream out : oss) {
            out.write(buffer, 0, readed);
         }

         count += readed;
      }

      return count;
   }



   private IOUtils() {
   }
}
