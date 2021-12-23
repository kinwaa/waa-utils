/*
 * Project:    Waa Java Utilities Package
 *
 * FileName:   Signator.java
 * CreateTime: 2021-12-21 15:38:58
 */
package cc.waa.java.utils.cryptology;

import static org.apache.commons.codec.binary.Base64.decodeBase64;
import static org.apache.commons.codec.binary.Base64.encodeBase64String;

import java.security.GeneralSecurityException;

/**
 * 签名器接口.
 *
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public interface Signator {

   /**
    * 签名.
    *
    * @param data
    * @return 签名结果
    * @throws GeneralSecurityException
    */
   byte[] sign(byte[] data) throws GeneralSecurityException;

   /**
    * 验证签名.
    *
    * @param data
    * @param sign
    * @return 是否验证成功
    * @throws GeneralSecurityException
    */
   boolean verify(byte[] data, byte[] sign) throws GeneralSecurityException;

   /**
    * 签名成base64字符串.
    *
    * @param data
    * @return base64的签名字符串
    * @throws GeneralSecurityException
    */
   default String signAsBase64(byte[] data) throws GeneralSecurityException {
      return encodeBase64String(sign(data));
   }

   /**
    * 验证base64的签名字符串.
    *
    * @param data
    * @param sign
    * @return 是否验证成功
    * @throws GeneralSecurityException
    */
   default boolean verifyAsBase64(byte[] data, String sign)
         throws GeneralSecurityException {
      return verify(data, decodeBase64(sign));
   }
}
