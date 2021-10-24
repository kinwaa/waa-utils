/*
 * Project:    Waa Java Utilities Package
 *
 * FileName:   Cipherer.java
 * CreateTime: 2021-10-24 00:40:17
 */
package cc.waa.java.utils.cryptology;

import static org.apache.commons.codec.binary.Base64.decodeBase64;
import static org.apache.commons.codec.binary.Base64.encodeBase64String;

import java.security.GeneralSecurityException;

/**
 * 通用密码器接口.
 *
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public interface Cipherer {

   /**
    * 加密.
    *
    * @param data
    * @return 密文
    * @throws GeneralSecurityException
    */
   byte[] encrypt(byte[] data) throws GeneralSecurityException;

   /**
    * 解密.
    *
    * @param data
    * @return 明文
    * @throws GeneralSecurityException
    */
   byte[] decrypt(byte[] data) throws GeneralSecurityException;

   /**
    * 加密后直接返回Base64字符串.
    *
    * @param data
    * @return 密文
    * @throws GeneralSecurityException
    */
   default String encryptToBase64(byte[] data) throws GeneralSecurityException {
      return encodeBase64String(encrypt(data));
   }

   /**
    * 直接从Base64字符串解密.
    *
    * @param data
    * @return 明文
    * @throws GeneralSecurityException
    */
   default byte[] decryptFromBase64(String data)
         throws GeneralSecurityException {
      return decrypt(decodeBase64(data));
   }
}
