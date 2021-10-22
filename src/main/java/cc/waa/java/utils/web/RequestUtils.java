/*
 * Project:    Waa Java Utilities Package
 *
 * FileName:   RequestUtils.java
 * CreateTime: 2021-10-19 11:45:07
 */
package cc.waa.java.utils.web;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Optional.of;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@Slf4j
public final class RequestUtils {

   /** 暂存reqestData的key. */
   private static final String REQUEST_DATA_BUFFER
         = "cc.waa.request.data.buffer";

   /** 分析json的工具. */
   private static final Gson GSON = new GsonBuilder().create();

   /**
    * 从requestData中分析出参数对象.
    *
    * @param <T> 返回的类型
    * @param request
    * @param type
    * @return 指定类型的对象
    */
   public static <T> T getRequestJsonObject(final HttpServletRequest request,
                                            final Class<T> type) {
      Optional<?> buffer
            = (Optional<?>) request.getAttribute(REQUEST_DATA_BUFFER);

      if (buffer == null) {
         try (Reader in = new InputStreamReader(request.getInputStream(),
                                                UTF_8)) {
            String bufData = IOUtils.toString(in);
            buffer = of(bufData);

            request.setAttribute(REQUEST_DATA_BUFFER, buffer);
         } catch (IOException e) {
            log.error("获取RequestData时出错", e);
         }
      }

      return GSON.fromJson((String) buffer.get(), type);
   }



   private RequestUtils() {
   }
}
